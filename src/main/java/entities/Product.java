package entities;

import java.time.LocalDate;

public record Product(int id, String name, Category category, int rating, LocalDate createdAt, LocalDate modifiedAt) {


}


