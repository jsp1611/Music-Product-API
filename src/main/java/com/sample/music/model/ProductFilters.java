package com.sample.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A series of filters for a Product, and associated paging instructions
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilters {

    private int pageNumber;

    private int pageSize;

    private boolean ascending;

    private ProductField sortField;

    private List<FieldFilter<?>> fieldFilters;

    // Builder class providing defaults for fields that are required but may not be specified
    // in the request
    public static class Builder {

        // Default if not supplied is 10
        private int pageSize = 10;

        // Default is 0 if not supplied
        private int pageNumber = 0;

        // Default is DESC for sort field
        private boolean ascending = false;

        // Default sort on product
        private ProductField sortField = ProductField.RELEASE_DATE;

        // Field filters, empty by default
        private final List<FieldFilter<?>> fieldFilters = new ArrayList<>();

        public Builder pageSize(final Integer pageSize) {
            if (pageSize != null) {
                this.pageSize = pageSize;
            }
            return this;
        }

        public Builder pageNumber(final Integer pageNumber) {
            if (pageNumber != null) {
                this.pageNumber = pageNumber;
            }
            return this;
        }

        public Builder asc(final Boolean ascending) {
            if (ascending != null) {
                this.ascending = ascending;
            }
            return this;
        }

        public Builder sort(final String sort) {
            final ProductField field = ProductField.from(sort);
            if (field != null) {
                this.sortField = field;
            }
            return this;
        }

        public Builder title(final String title) {
            if (title != null) {
                this.fieldFilters.add(new StringFieldFilter(ProductField.TITLE, FilterType.EQUAL_TO, title));
            }
            return this;
        }

        public Builder artist(final String artist) {
            if (artist != null) {
                this.fieldFilters.add(new StringFieldFilter(ProductField.ARTIST, FilterType.EQUAL_TO, artist));
            }
            return this;
        }

        public Builder label(final String label) {
            if (label != null) {
                this.fieldFilters.add(new StringFieldFilter(ProductField.LABEL, FilterType.EQUAL_TO, label));
            }
            return this;
        }

        public Builder releaseDateAfter(final LocalDate date) {
            if (date != null) {
                this.fieldFilters.add(new LocalDateFieldFilter(ProductField.RELEASE_DATE,
                        FilterType.GREATER_THAN_OR_EQUAL_TO, date));
            }
            return this;
        }

        public Builder releaseDateBefore(final LocalDate date) {
            if (date != null) {
                this.fieldFilters.add(new LocalDateFieldFilter(ProductField.RELEASE_DATE,
                        FilterType.LESS_THAN_OR_EQUAL_TO, date));
            }
            return this;
        }

        public Builder store(final String store) {
            if (store != null) {
                this.fieldFilters.add(new StringFieldFilter(ProductField.STORE, FilterType.EQUAL_TO, store));
            }
            return this;
        }

        public ProductFilters build() {
            return new ProductFilters(pageNumber, pageSize, ascending, sortField, fieldFilters);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
