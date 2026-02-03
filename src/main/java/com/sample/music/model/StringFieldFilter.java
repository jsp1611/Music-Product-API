package com.sample.music.model;

/**
 * A filter for a <code>Product</code> field, comprising a field name, the type of filter
 * and a <code>String</code> value.
 *
 * @param field the name of the field
 * @param filterType the type of filter
 * @param value the <code>String</code> value
 */
public record StringFieldFilter(ProductField field, FilterType filterType, String value)
        implements FieldFilter<String> {

}
