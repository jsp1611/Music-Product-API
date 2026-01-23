package com.sample.music.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents a request to update a Product with *all* of
 * the supplied field values.
 */
@Data
@NoArgsConstructor
public class UpdateProductRequest {

    private String title;

    private String artist;

    private String label;

    private String priceGbp;

    private String priceUsd;

    private String priceEur;

    private LocalDate releaseDate;

    private String store;

}
