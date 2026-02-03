package com.sample.music.repo;

import com.sample.music.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Utility class that converts our own <code>FieldFilter</code> instances wrapped by a
 * <code>ProductFilters</code> instance into JPA <code>Specification</code> instances, and then
 * combines those <code>Specification</code> instances into a single instance for use in repository
 * queries.
 */
public class ProductSpecification {

    private static Specification<Product> toSpec(final StringFieldFilter fieldFilter) {
        if (fieldFilter.filterType().equals(FilterType.EQUAL_TO)) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(fieldFilter.field().getFieldName()), fieldFilter.value());
        } else {
            throw new IllegalArgumentException("Unsupported FilterType for StringFieldFilter");
        }
    }

    private static Specification<Product> toSpec(final LocalDateFieldFilter fieldFilter) {
        if (fieldFilter.filterType().equals(FilterType.EQUAL_TO)) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(
                            fieldFilter.field().getFieldName()), fieldFilter.value());
        } else if (fieldFilter.filterType().equals(FilterType.LESS_THAN_OR_EQUAL_TO)) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get(
                            fieldFilter.field().getFieldName()), fieldFilter.value());
        } else if (fieldFilter.filterType().equals(FilterType.GREATER_THAN_OR_EQUAL_TO)) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get(
                            fieldFilter.field().getFieldName()), fieldFilter.value());
        } else {
            throw new IllegalArgumentException("Unsupported FilterType for LocalDateFilter");
        }
    }

    /**
     * Convert a <code>ProductFilters</code> instance into a <code>Specification</code>,
     * for use in JPA queries.
     *
     * @param filters the filters wrapped in a <code>ProductFilters</code> instance
     * @return the <code>Specification</code> wrapping the conditions
     */
    public static Specification<Product> from(final ProductFilters filters) {
        final List<Specification<Product>> specifications = new ArrayList<>();
        for (FieldFilter<?> fieldFilter : filters.getFieldFilters()) {
            if (fieldFilter instanceof StringFieldFilter) {
                specifications.add(toSpec((StringFieldFilter) fieldFilter));
            } else if (fieldFilter instanceof LocalDateFieldFilter) {
                specifications.add(toSpec((LocalDateFieldFilter) fieldFilter));
            } else {
                throw new IllegalArgumentException(
                        format("Unsupported FieldFilter type: %s", fieldFilter.getClass().getName()));
            }
        }
        return Specification.allOf(specifications);
    }

}
