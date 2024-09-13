package service;

import entities.Category;
import entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        Warehouse.resetProducts();
        Warehouse.addProductForTest();
    }


    @Test
    @DisplayName("Test to get all products")
    void testToGetAllProductsInList() {
        var result = Warehouse.getAllProducts();

        assertEquals(7, result.size());
        assertNotNull(result, "ProductList should not be null");
        assertFalse(result.isEmpty(), "ProductList should not be empty");
    }


    @Test
    @DisplayName("Test to get a product by id")
    void getProductById() {
        var result = Warehouse.getProductById(1);

        assertNotNull(result, "Product should not be null.");
        assertEquals(1, result.id(), "Wrong id");
    }

    @Test
    @DisplayName("Test for getting product by invalid ID throws exception")
    void testForGettingProductByInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> Warehouse.getProductById(999), "Product not found");
    }

    @Test
    @DisplayName("Test to get products by category and sorted by name")
    void getProductByCategorySortedByName() {
        var result = Warehouse.getProductByCategorySortedByName(Category.DRINK);

        assertFalse(result.isEmpty(), "List with drinks should not be empty.");
        assertEquals("Zola", result.getLast().name());

        for (Product product : result) {
            assertEquals(Category.DRINK, product.category());
        }
    }


    @Test
    @DisplayName("Test to modify a product")
    void testModifyProductSuccessfully() {

        Warehouse.modifyProduct(1, "Pepsi", Category.DRINK, 9);
        var modifiedProduct = Warehouse.getProductById(1);

        assertEquals(1, modifiedProduct.id());
        assertEquals("Pepsi", modifiedProduct.name());
        assertEquals(Category.DRINK, modifiedProduct.category());
        assertEquals(9, modifiedProduct.rating());

    }

    @Test
    @DisplayName("Test for adding invalid modified product")
    void testModifyNonExistentProductThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> Warehouse.modifyProduct(999, "Pepsi", Category.DRINK, 9), "Product found");
    }


    @Test
    @DisplayName("Test to get a product after given date")
    void testGetProductAfterGivenDate() {
        var dateTest = LocalDate.of(2024, 11, 21);
        var result = Warehouse.getProductsAfterGivenDate(dateTest);

        assertEquals(2, result.size());

        for (Product product : result) {
            assertTrue(product.createdAt().isAfter(dateTest));
        }
    }

    @Test
    @DisplayName("Test for adding new product")
    void testForAddingNewProduct() {
        var initialSize = Warehouse.getAllProducts().size();
        var newTestProduct = new Product(11, "Biff", Category.MEAT, 10, LocalDate.now(), LocalDate.now());

        Warehouse.addNewProduct(newTestProduct);
        assertEquals(initialSize + 1, Warehouse.getAllProducts().size());
        assertTrue(Warehouse.getAllProducts().contains(newTestProduct));
    }

    @Test
    @DisplayName("Test for adding invalid name when adding new product")
    void testForAddingProductWithEmptyName() {
        var invalidProduct = new Product(12, "", Category.MEAT, 10, LocalDate.now(), LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> Warehouse.addNewProduct(invalidProduct));
    }

    @Test
    @DisplayName("Test for getting modified products")
    void testForGettingModifiedProducts() {
        var firstProduct = new Product(13, "Korv", Category.MEAT, 5, LocalDate.now(), null);
        var secondProduct = new Product(12, "Anka", Category.MEAT, 10, LocalDate.now(), LocalDate.of(2040, 12, 24));

        Warehouse.addNewProduct(secondProduct);
        Warehouse.addNewProduct(firstProduct);

        var result = Warehouse.getProductsThatHasBeenModified();

        assertTrue(result.contains(secondProduct), "Second product should be considered modified.");
        assertFalse(result.contains(firstProduct), "First product should not be considered modified.");

    }


}
