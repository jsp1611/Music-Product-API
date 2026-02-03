package com.sample.music.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a request to create a new Product, using the
 * specified field values.
 */
@Data
@NoArgsConstructor
public class CreateProductRequest {

    private String title;

    private String artist;

    private String label;

    private BigDecimal priceGbp;

    private BigDecimal priceUsd;

    private BigDecimal priceEur;

    private LocalDate releaseDate;

    private String store;

}
