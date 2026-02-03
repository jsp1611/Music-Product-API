package com.sample.music.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Title of the Product", examples = {"My Band (CD)", "My Tour T-Shirt"})
    private String title;

    @Schema(description = "Artist name associated with the Product", examples = {"Aural Damage", "Pub Trouble"})
    private String artist;

    @Schema(description = "Label associated with Product", examples = {"My Label", "Other Label"})
    private String label;

    @Schema(description = "Price in GBP", examples = {"1.00", "12.50", "25.15"})
    private BigDecimal priceGbp;

    @Schema(description = "Price in USD", examples = {"1.00", "12.50", "25.15"})
    private BigDecimal priceUsd;

    @Schema(description = "Price in EUR", examples = {"1.00", "12.50", "25.15"})
    private BigDecimal priceEur;

    @Schema(description = "Release date of the product", examples = {"2026-01-15", "2024-08-12"})
    private LocalDate releaseDate;

    @Schema(description = "Store name", examples = {"Some Store", "My Store"})
    private String store;

}
