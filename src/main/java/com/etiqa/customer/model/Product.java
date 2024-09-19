package com.etiqa.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Book title cannot be blank")
    private String bookTitle;
    @Min(value = 0, message = "Book price must be greater than or equal to 0")
    private double bookPrice;
    @Min(value = 0, message = "Book quantity must be greater than or equal to 0")
    private int bookQuantity;
    @NotBlank(message = "Book author cannot be blank")
    private String bookAuthor;
    @NotBlank(message = "Language cannot be blank")
    private String language;
    @Min(value = 0, message = "Rating must be greater than or equal to 0")
    private double rating;
}
