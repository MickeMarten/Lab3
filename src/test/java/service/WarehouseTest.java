package service;

import entities.Category;
import entities.Product;
import jdk.jfr.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;
    @BeforeEach
    void setUp() {
        Warehouse.resetProducts();
        System.out.println("Products before test: " + Warehouse.getAllProducts());
        warehouse = new Warehouse();
        warehouse.addProductForTest();
    }
    @AfterEach
    void tearDown() {
        System.out.println("Products after test: " + Warehouse.getAllProducts());
    }

    @Test
    @DisplayName("Test to get all products")
    void testToGetAllProductsInList() {
        List<Product> result = warehouse.getAllProducts();

        assertNotNull(result, "ProductList should not be empty.");

        assertTrue(result.size() > 0, "");
    }


    @Test
    @DisplayName("Test to get a product by id")
    void getProductById() {
        Product result = warehouse.getProductById(1);
        assertEquals(1, result.id(), "Wrong id");
    }


    @Test
    @DisplayName("Test to get products by category and sorted by name")
    void getProductByCategorySortedByName() {
        List<Product> result = warehouse.getProductByCategorySortedByName(Category.DRINK);
        assertFalse(result.isEmpty(), "List with drinks should not be empty.");
        assertEquals("Zola", result.getLast().name());
        for (Product product : result) {
            assertEquals(Category.DRINK, product.category());
        }
    }


    @Test
    @DisplayName("Test to modify a product")
    void testModifyProductSuccessfully() {

        warehouse.modifyProduct(1, "Pepsi", Category.DRINK, 9);

        Product modifiedProduct = warehouse.getProductById(1);
        assertEquals(1, modifiedProduct.id());
        assertEquals("Pepsi", modifiedProduct.name());
        assertEquals(Category.DRINK, modifiedProduct.category());
        assertEquals(9, modifiedProduct.rating());


    }


    @Test
    @DisplayName("??")
    void testModifyNonExistentProductThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> warehouse.modifyProduct(999, "Pepsi", Category.DRINK, 9), "Product found");
    }


    @Test
    @DisplayName("Test to get a product after given date")
    void testGetProductAfterGivenDate() {
        LocalDate dateTest = LocalDate.of(2024,11,21);
        List<Product> result = warehouse.getProductsAfterDate(dateTest);
        assertEquals(2, result.size());
        for(Product product : result){
            assertTrue(product.createdAt().isAfter(dateTest));
        }


    }

}
