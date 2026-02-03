package com.sample.music.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a request to create a new <code>Product</code>, using the
 * specified field values.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request for creating a new Product")
public class CreateProductRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    @Schema(description = "Title of the Product", examples = {"My Band (CD)", "My Tour T-Shirt"})
    private String title;

    @NotBlank
    @Size(min = 1, max = 200)
    @Schema(description = "Artist name associated with the Product", examples = {"Aural Damage", "Pub Trouble"})
    private String artist;

    @NotBlank
    @Size(min = 1, max = 200)
    @Schema(description = "Label associated with Product", examples = {"My Label", "Other Label"})
    private String label;

    @NotNull
    @DecimalMax("1000000")
    @Positive
    @Schema(description = "Price in GBP", examples = {"1.00", "12.50", "25.15"})
    private BigDecimal priceGbp;

    @NotNull
    @DecimalMax("1000000")
    @Positive
    @Schema(description = "Price in USD", examples = {"1.00", "12.50", "25.15"})
    private BigDecimal priceUsd;

    @NotNull
    @DecimalMax("1000000")
    @Positive
    @Schema(description = "Price in EUR", examples = {"1.00", "12.50", "25.15"})
    private BigDecimal priceEur;

    @NotNull
    @Schema(description = "Release date of the product", examples = {"2026-01-15", "2024-08-12"})
    private LocalDate releaseDate;

    @NotBlank
    @Size(min = 1, max = 200)
    @Schema(description = "Store name", examples = {"Some Store", "My Store"})
    private String store;

}
