package service;

import entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertNotNull(result, "List should not be null");

       assertTrue(result.size() > 0, "List should not be empty");
    }

    @Test
    void getProductById() {
        Product result = Warehouse.getProductById(1);
        assertEquals(1, result.id(), "Wrong id");


    }
}