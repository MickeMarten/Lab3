package service;

import entities.Category;
import entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        warehouse.addProduct();
    }


    @Test
    void testToGetAllProductsInList() {
        List<Product> result = Warehouse.getAllProducts();

        assertNotNull(result, "ProductList should not be empty.");

        assertTrue(result.size() > 0, "");
    }

    @Test
    void getProductById() {
        Product result = Warehouse.getProductById(1);
        assertEquals(1, result.id(), "Wrong id");
    }

    @Test
    void getProductByCategorySortedByName() {
        List<Product> result = Warehouse.getProductByCategorySortedByName(Category.DRINK);
        assertFalse(result.isEmpty(), "List with drinks should not be empty.");
        assertEquals("Zola", result.getLast().name());
        for (Product product : result) {
            assertEquals(Category.DRINK, product.category());
        }
    }

    @Test
    void testModifyProductSuccessfully() {

        warehouse.modifyProduct(1, "Pepsi", Category.DRINK, 9);

        Product modifiedProduct = warehouse.getProductById(1);
        assertEquals(1, modifiedProduct.id());
        assertEquals("Pepsi", modifiedProduct.name());
        assertEquals(Category.DRINK, modifiedProduct.category());
        assertEquals(9, modifiedProduct.rating());

//        assertEquals(LocalDate.of(2023, 6, 12), modifiedProduct.creationDate());
//        assertEquals(LocalDate.now(), modifiedProduct.modificationDate());
    }

    @Test
    void testModifyNonExistentProductThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> warehouse.modifyProduct(999, "Pepsi", Category.DRINK, 9), "Product found");
    }

}

