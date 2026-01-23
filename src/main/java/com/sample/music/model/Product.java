package com.sample.music.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * A Product available within a store.
 */
@Entity
@Table(name = "PRODUCTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "PRICE_GBP")
    private String priceGbp;

    @Column(name = "PRICE_USD")
    private String priceUsd;

    @Column(name = "PRICE_EUR")
    private String priceEur;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @Column(name = "STORE")
    private String store;

}
