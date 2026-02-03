package com.sample.music.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A Product field identifier
 */
public enum ProductField {

    ID("id"),
    TITLE("title"),
    ARTIST("artist"),
    LABEL("label"),
    PRICE_GBP("priceGbp"),
    PRICE_USD("priceUsd"),
    PRICE_EUR("priceEur"),
    RELEASE_DATE("releaseDate"),
    STORE("store");

    private final String fieldName;

    ProductField(final String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    private static final Supplier<Map<String,ProductField>> LOOK_UP_SUPPLIER = () -> {
        final Map<String,ProductField> map = new HashMap<>();
        for (ProductField field : ProductField.values()) {
            map.put(field.name(), field);
        }
        return map;
    };

    private static final Map<String,ProductField> LOOK_UP = LOOK_UP_SUPPLIER.get();

    public static ProductField from(final String fieldName) {
        ProductField result = null;
        if (fieldName != null) {
            result = LOOK_UP.get(fieldName);
        }
        return result;
    }

}
