package com.sample.music.exception;

import static java.lang.String.format;

/**
 * Indicates that a Product was accessed by an id and was
 * not present in the data store.
 */
public class MissingProductException extends RuntimeException {

    public MissingProductException(final long id) {
        super(format("Product with id %d was not found", id));
    }

}
