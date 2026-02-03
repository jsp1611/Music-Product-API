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
 * Service layer between <code>Product</code> data submitted via controllers
 * and the repository layer.
 */
@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Delete a <code>Product</code> with the specified id
     *
     * @param id the id of the <code>Product</code>
     * @return the <code>Product</code> that was deleted
     */
    public Product delete(final long id) {
        log.info("Deleting Product with id: {}", id);
        final Optional<Product> productOpt = productRepository.findById(id);
        final Product toDelete = productOpt.orElseThrow(() -> new MissingProductException(id));
        productRepository.deleteById(id);
        return toDelete;
    }

    /**
     * Create a new <code>Product</code>, where the supplied <code>Product</code> instance
     * has no assigned id.
     *
     * @param product the <code>Product</code> without an id
     * @return the created <code>Product</code>, with assigned id
     */
    public Product create(final Product product) {
        log.info("Creating Product from request: {}", product);
        return productRepository.save(product);
    }

    /**
     * Fetch a <code>Product<code> with the specified id
     *
     * @param id the <code>Product</code> id
     * @return the <code>Product</code>, if found
     */
    public Product fetch(final long id) {
        log.info("Fetching Product with id: {}", id);
        final Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.orElseThrow(() -> new MissingProductException(id));
    }

    /**
     * Update the specified <code>Product</code>
     *
     * @param product the <code>Product</code>, with new values supplied for all fields
     * @return the updated <code>Product</code>
     */
    public Product update(final Product product) {
        log.info("Updating Product: {}", product);
        final Long id = product.getId();
        productRepository.findById(id).orElseThrow(() -> new MissingProductException(id));
        return productRepository.save(product);
    }

    /**
     * Find a <code>List</code></> of <code>Product</code> based on the supplied filter information
     *
     * @param filters the filters, including paging information
     * @return the <code>List</code> of matching <code>Product</code>
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
