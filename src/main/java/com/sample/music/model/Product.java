package com.sample.music.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A <code>Product</code> available within a store.
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

    @Column(name = "PRICE_GBP", precision = 10, scale = 2)
    private BigDecimal priceGbp;

    @Column(name = "PRICE_USD", precision = 10, scale = 2)
    private BigDecimal priceUsd;

    @Column(name = "PRICE_EUR", precision = 10, scale = 2)
    private BigDecimal priceEur;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @Column(name = "STORE")
    private String store;

}
