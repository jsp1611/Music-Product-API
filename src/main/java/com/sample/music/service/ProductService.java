package com.sample.music.service;

import com.sample.music.dto.CreateProductRequest;
import com.sample.music.dto.UpdateProductRequest;
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

    private ProductRepository productRepository;

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
        log.info("Deleting product with id: {}", id);
        final Optional<Product> productOpt = productRepository.findById(id);
        final Product toDelete = productOpt.orElseThrow(() -> new MissingProductException(id));
        productRepository.deleteById(id);
        return toDelete;
    }

    /**
     * Create a new product
     *
     * @param request the product creation request
     * @return the created product, with assigned id
     */
    public Product create(final CreateProductRequest request) {
        log.info("Creating product from request: {}", request);
        final Product product = new Product(null, request.getTitle(), request.getArtist(), request.getLabel(),
                request.getPriceGbp(), request.getPriceUsd(), request.getPriceEur(), request.getReleaseDate(),
                request.getStore());
        return productRepository.save(product);
    }

    /**
     * Fetch a product with the specified id
     *
     * @param id the product id
     * @return the Product, if found
     */
    public Product fetch(final long id) {
        log.info("Fetching product with id: {}", id);
        final Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.orElseThrow(() -> new MissingProductException(id));
    }

    /**
     * Update the product fields with the specified request data
     *
     * @param id the id
     * @param request the new field values for the product
     * @return the updated product
     */
    public Product update(final long id, final UpdateProductRequest request) {
        log.info("Updating product with id {} with request: {}", id, request);
        final Optional<Product> productOpt = productRepository.findById(id);
        final Product toUpdate = productOpt.orElseThrow(() -> new MissingProductException(id));
        toUpdate.setTitle(request.getTitle());
        toUpdate.setArtist(request.getArtist());
        toUpdate.setLabel(request.getLabel());
        toUpdate.setPriceGbp(request.getPriceGbp());
        toUpdate.setPriceUsd(request.getPriceUsd());
        toUpdate.setPriceEur(request.getPriceEur());
        toUpdate.setReleaseDate(request.getReleaseDate());
        toUpdate.setStore(request.getStore());
        return productRepository.save(toUpdate);
    }

    /**
     * Find a list of product based on the supplied filter information
     *
     * @param filters the filters, including paging information
     * @return the list of matching products
     */
    public List<Product> find(final ProductFilters filters) {
        log.info("Finding products matching filters: {}", filters);
        final Specification<Product> specs = ProductSpecification.from(filters);
        final Sort sort = Sort.by(
                filters.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC,
                filters.getSortField().getFieldName());
        final Pageable pageable = PageRequest.of(filters.getPageNumber(), filters.getPageSize(), sort);
        final Page<Product> page = productRepository.findAll(specs, pageable);
        return page.get().toList();
    }

}
