package service;
import entities.Category;
import entities.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Warehouse {
    private static final List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        addProduct();
        sortProduct();

    }

    public static void addProduct(){
        products.add(new Product(1, "Apelsin", Category.FRUITS,5, LocalDate.of(2024, 12, 30), LocalDate.of(2024, 1,28)));
        products.add(new Product(2, "Banan", Category.FRUITS, 1, LocalDate.of(2023, 8, 25), LocalDate.of(2024, 2,23)));
        products.add(new Product(3, "Kyckling", Category.MEAT, 9, LocalDate.of(2024, 11, 22), LocalDate.of(2024, 3,11)));
        products.add(new Product(4, "Cola", Category.DRINK, 4, LocalDate.of(2023, 6, 12), LocalDate.of(2024, 4,2)));
        products.add(new Product(5, "Fläsk", Category.MEAT,8, LocalDate.of(2023, 4, 4), LocalDate.of(2024, 4,4)));
    }


    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(products);
    }

    public static void sortProduct(){
        List<Product> sortedList = products.stream()
                .filter(item -> item.rating() > 1 ).peek(System.out::println)
                .collect(Collectors.toList());


    }

}
