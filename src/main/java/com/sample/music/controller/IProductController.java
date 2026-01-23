package com.sample.music.controller;

import com.sample.music.dto.CreateProductRequest;
import com.sample.music.dto.UpdateProductRequest;
import com.sample.music.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for actions related to Product, containing swagger annotations
 */
public interface IProductController {

    @Operation(summary = "Create a Product")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(mediaType = "application/json"))
    )
    ResponseEntity<Product> create(@RequestBody(required = true) CreateProductRequest request);

    @Operation(summary = "Fetch a Product")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json"))
    )
    ResponseEntity<Product> fetch(@Parameter(required = true) long id);

    @Operation(summary = "Update a Product")
    ResponseEntity<Product> update(@Parameter(required = true) long id,
                                   @RequestBody(required = true) UpdateProductRequest request);

    @Operation(summary = "Delete a Product")
    ResponseEntity<Product> delete(@Parameter(required = true) long id);

    @Operation(summary = "Find products based upon supplied filters")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(mediaType = "application/json"))
    )
    ResponseEntity<List<Product>> find(@Parameter(required = false) String title,
                                       @Parameter(required = false) String artist,
                                       @Parameter(required = false) String label,
                                       @Parameter(required = false) String store,
                                       @Parameter(required = false) LocalDate startRelease,
                                       @Parameter(required = false) LocalDate endRelease,
                                       @Parameter(required = false) Integer pageSize,
                                       @Parameter(required = false) Integer pageNumber,
                                       @Parameter(required = false) String sort,
                                       @Parameter(required = false) Boolean asc);

}
