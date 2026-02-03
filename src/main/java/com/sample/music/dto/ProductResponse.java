package com.sample.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representation of Product suitable for display.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private String title;

    private String artist;

    private String label;

    private BigDecimal priceGbp;

    private BigDecimal priceUsd;

    private BigDecimal priceEur;

    private LocalDate releaseDate;

    private String store;

}
