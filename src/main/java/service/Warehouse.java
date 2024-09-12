package service;

import entities.Category;
import entities.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Warehouse {
    private static final List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        addProduct();
        modifyProduct(1, "Pepsi", Category.DRINK, 9);
    }

    public static void addProduct() {
        products.add(new Product(1, "Apelsin", Category.FRUITS, 5, LocalDate.of(2024, 12, 30), LocalDate.of(2024, 1, 28)));
        products.add(new Product(2, "Banan", Category.FRUITS, 1, LocalDate.of(2023, 8, 25), LocalDate.of(2024, 2, 23)));
        products.add(new Product(3, "Kyckling", Category.MEAT, 9, LocalDate.of(2024, 11, 22), LocalDate.of(2024, 3, 11)));
        products.add(new Product(5, "Pepsi", Category.DRINK, 6, LocalDate.of(2023, 6, 12), LocalDate.of(2024, 9, 2)));
        products.add(new Product(7, "Zola", Category.DRINK, 10, LocalDate.of(2023, 6, 12), LocalDate.of(2024, 6, 2)));
        products.add(new Product(6, "Fanta", Category.DRINK, 1, LocalDate.of(2023, 6, 12), LocalDate.of(2024, 7, 5)));
        products.add(new Product(8, "Fläsk", Category.MEAT, 8, LocalDate.of(2023, 4, 4), LocalDate.of(2024, 4, 4)));
    }


    public static List<Product> getAllProducts() {
        System.out.println(products);

        return Collections.unmodifiableList(products);
    }


    public static void sortProduct() {
        List<Product> sortedList = products.stream()
                .filter(item -> item.rating() > 1).peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println(sortedList);


    }

    public static Product getProductById(int id) {

        return products.stream().filter(product -> product.id() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Product not found"));

    }

    public static List<Product> getProductByCategorySortedByName(Category category) {
        return products.stream()
                .filter(product -> product.category() == category)
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toUnmodifiableList());

    }

    public static void addNewProduct(Product product) {
        if (product.name().isEmpty()) {
            throw new IllegalArgumentException("Product name can't be empty");
        }
        products.add(product);


    }

    public static void modifyProduct(int id, String newName, Category newCategory, int newRating) {
        Product product = products.stream()
                .filter(p -> p.id() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id is not available"));
        Product modifiedProduct = new Product(product.id(), newName, newCategory, newRating, product.createdAt(), LocalDate.now());

        products.set(products.indexOf(product), modifiedProduct);
        System.out.println(products);


    }

    //Todo: Hämta alla produkter skapde efter angivet Datum. (Nya produkter sist).
    //Todo: Hämta alla produkter  som modiferats sen de skapades (Datum ej samma).

}
