package com.sample.music.controller;

import com.sample.music.dto.CreateProductRequest;
import com.sample.music.dto.UpdateProductRequest;
import com.sample.music.model.Product;
import com.sample.music.model.ProductFilters;
import com.sample.music.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Product controller for accessing and managing Product items
 */
@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController implements IProductController {

    private ProductService productService;

    @Autowired
    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Product> create(@RequestBody CreateProductRequest request) {
        log.info("Creating new product from: {}", request);
        return ResponseEntity.ok(productService.create(request));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Product> fetch(@PathVariable long id) {
        log.info("Getting product with id: {}", id);
        return ResponseEntity.ok(productService.fetch(id));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Product> update(@PathVariable long id, @RequestBody UpdateProductRequest request) {
        log.info("Updating product with id {} with new field values: {}", id, request);
        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Product> delete(@PathVariable long id) {
        log.info("Deleting product with id {}", id);
        return ResponseEntity.ok(productService.delete(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Product>> find(@RequestParam(required = false) String title,
                                            @RequestParam(required = false) String artist,
                                            @RequestParam(required = false) String label,
                                            @RequestParam(required = false) String store,
                                            @RequestParam(required = false) LocalDate startRelease,
                                            @RequestParam(required = false) LocalDate endRelease,
                                            @RequestParam(required = false) Integer pageSize,
                                            @RequestParam(required = false) Integer pageNumber,
                                            @RequestParam(required = false) String sort,
                                            @RequestParam(required = false) Boolean asc) {
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
        return ResponseEntity.ok(productService.find(filters));
    }

}
