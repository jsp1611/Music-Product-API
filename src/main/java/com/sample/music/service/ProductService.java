package com.sample.music.service;

import com.sample.music.exception.MissingProductException;
import com.sample.music.model.Product;
import com.sample.music.model.ProductFilters;
import com.sample.music.repo.ProductRepository;
import com.sample.music.repo.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer between data submitted via controllers and the repository
 * layer, which uses JPA.
 */
@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Delete a product with the specified id
     *
     * @param id the id of the Product
     * @return the product that was deleted
     */
    public Product delete(final long id) {
        log.info("Deleting Product with id: {}", id);
        final Optional<Product> productOpt = productRepository.findById(id);
        final Product toDelete = productOpt.orElseThrow(() -> new MissingProductException(id));
        productRepository.deleteById(id);
        return toDelete;
    }

    /**
     * Create a new Product, where the supplied Product instance
     * has no assigned id.
     *
     * @param product the product without an id
     * @return the created product, with assigned id
     */
    public Product create(final Product product) {
        log.info("Creating Product from request: {}", product);
        return productRepository.save(product);
    }

    /**
     * Fetch a product with the specified id
     *
     * @param id the product id
     * @return the Product, if found
     */
    public Product fetch(final long id) {
        log.info("Fetching Product with id: {}", id);
        final Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.orElseThrow(() -> new MissingProductException(id));
    }

    /**
     * Update the specified Product
     *
     * @param product the product, with new values supplied for all fields
     * @return the updated product
     */
    public Product update(final Product product) {
        log.info("Updating Product: {}", product);
        final Long id = product.getId();
        productRepository.findById(id).orElseThrow(() -> new MissingProductException(id));
        return productRepository.save(product);
    }

    /**
     * Find a List of Product based on the supplied filter information
     *
     * @param filters the filters, including paging information
     * @return the List of matching Products
     */
    public List<Product> find(final ProductFilters filters) {
        log.info("Finding Products matching filters: {}", filters);
        final Specification<Product> specs = ProductSpecification.from(filters);
        final Sort sort = Sort.by(
                filters.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC,
                filters.getSortField().getFieldName());
        final Pageable pageable = PageRequest.of(filters.getPageNumber(), filters.getPageSize(), sort);
        final Page<Product> page = productRepository.findAll(specs, pageable);
        return page.get().toList();
    }

}
