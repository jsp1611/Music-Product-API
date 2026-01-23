package com.sample.music.model;

/**
 * A filter for a Product field, comprising a field name, the type of filter
 * and a String value.
 *
 * @param field the name of the field
 * @param filterType the type of filter
 * @param value the String value
 */
public record StringFieldFilter(ProductField field, FilterType filterType, String value)
        implements FieldFilter<String> {

}
