package com.sample.music.model;

import java.time.LocalDate;

/**
 * A filter for a <code>Product</code> field, comprising a field name, the type of filter
 * and a <code>LocalDate</code> value.
 *
 * @param field the name of the field
 * @param filterType the type of filter
 * @param value the LocalDate value
 */
public record LocalDateFieldFilter(ProductField field, FilterType filterType, LocalDate value)
        implements FieldFilter<LocalDate> {

}
