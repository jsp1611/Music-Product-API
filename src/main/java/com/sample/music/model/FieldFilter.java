package com.sample.music.model;

/**
 * A marker interface for field filters.
 */
public interface FieldFilter<T> {

    /**
     * Field information on product
     *
     * @return the field information
     */
    ProductField field();

    /**
     * The type of filter
     *
     * @return the filter
     */
    FilterType filterType();

    /**
     * The value associated with the field and filter type
     *
     * @return the value
     */
    T value();

}
