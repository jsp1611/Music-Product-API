package com.sample.music.repo;

import com.sample.music.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Converts our own filters into JPA Specification instances
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
     * Convert a set of product filters into a Specification, for use in JPA queries.
     *
     * @param filters the filters
     * @return the specification wrapping the conditions
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
