package com.sample.music.controller;

import com.sample.music.dto.CreateProductRequest;
import com.sample.music.dto.ProductResponse;
import com.sample.music.dto.UpdateProductRequest;
import com.sample.music.model.Product;
import com.sample.music.model.ProductFilters;
import com.sample.music.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <code>Product</code> controller for accessing and managing <code>Product</code> items
 */
@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController implements IProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Override
    public ResponseEntity<ProductResponse> create(@RequestBody final CreateProductRequest request) {
        log.info("Creating new product from: {}", request);
        final Product product = productService.create(new Product(null, request.getTitle(),
                request.getArtist(), request.getLabel(), request.getPriceGbp(),
                request.getPriceUsd(), request.getPriceEur(), request.getReleaseDate(),
                request.getStore()));
        return ResponseEntity.status(HttpStatus.CREATED).body(toProductResponse(product));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ProductResponse> fetch(@PathVariable final long id) {
        log.info("Getting product with id: {}", id);
        return ResponseEntity.ok(toProductResponse(productService.fetch(id)));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ProductResponse> update(@PathVariable final long id,
                                                  @RequestBody final UpdateProductRequest request) {
        log.info("Updating product with id {} with new field values: {}", id, request);
        final Product product = new Product();
        product.setId(id);
        product.setTitle(request.getTitle());
        product.setArtist(request.getArtist());
        product.setLabel(request.getLabel());
        product.setPriceGbp(request.getPriceGbp());
        product.setPriceUsd(request.getPriceUsd());
        product.setPriceEur(request.getPriceEur());
        product.setReleaseDate(request.getReleaseDate());
        product.setStore(request.getStore());
        return ResponseEntity.ok(toProductResponse(productService.update(product)));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ProductResponse> delete(@PathVariable final long id) {
        log.info("Deleting product with id {}", id);
        return ResponseEntity.ok(toProductResponse(productService.delete(id)));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ProductResponse>> find(@RequestParam(required = false) final String title,
                                                      @RequestParam(required = false) final String artist,
                                                      @RequestParam(required = false) final String label,
                                                      @RequestParam(required = false) final String store,
                                                      @RequestParam(required = false) final LocalDate startRelease,
                                                      @RequestParam(required = false) final LocalDate endRelease,
                                                      @RequestParam(required = false) final Integer pageSize,
                                                      @RequestParam(required = false) final Integer pageNumber,
                                                      @RequestParam(required = false) final String sort,
                                                      @RequestParam(required = false) final Boolean asc) {
        log.info("Finding products matching supplied filters");
        final ProductFilters filters = ProductFilters.builder()
                .title(title)
                .artist(artist)
                .label(label)
                .store(store)
                .releaseDateAfter(startRelease)
                .releaseDateBefore(endRelease)
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .sort(sort)
                .asc(asc)
                .build();
        return ResponseEntity.ok(toProductResponseList(productService.find(filters)));
    }

    private static ProductResponse toProductResponse(final Product product) {
        return new ProductResponse(product.getId(), product.getTitle(), product.getArtist(),
                product.getLabel(), product.getPriceGbp(), product.getPriceUsd(),
                product.getPriceEur(), product.getReleaseDate(), product.getStore());
    }

    private static List<ProductResponse> toProductResponseList(final List<Product> products)  {
        return products.stream().map(ProductController::toProductResponse).toList();
    }

}
