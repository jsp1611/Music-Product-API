package com.sample.music.controller;

import com.sample.music.dto.CreateProductRequest;
import com.sample.music.dto.ProductResponse;
import com.sample.music.dto.UpdateProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for actions related to <code>Product</code>, containing Swagger annotations
 * used to generate the UI.
 */
public interface IProductController {

    @Operation(summary = "Create a Product")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)))
    )
    ResponseEntity<ProductResponse> create(@RequestBody(required = true) CreateProductRequest request);

    @Operation(summary = "Fetch a Product")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json"))
    )
    ResponseEntity<ProductResponse> fetch(@Parameter(required = true) long id);

    @Operation(summary = "Update a Product")
    ResponseEntity<ProductResponse> update(@Parameter(required = true) long id,
                                           @RequestBody(required = true) UpdateProductRequest request);

    @Operation(summary = "Delete a Product")
    ResponseEntity<ProductResponse> delete(@Parameter(required = true) long id);

    @Operation(summary = "Find products based upon supplied filters")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(mediaType = "application/json"))
    )
    ResponseEntity<List<ProductResponse>> find(@Parameter String title,
                                               @Parameter String artist,
                                               @Parameter String label,
                                               @Parameter String store,
                                               @Parameter LocalDate startRelease,
                                               @Parameter LocalDate endRelease,
                                               @Parameter Integer pageSize,
                                               @Parameter Integer pageNumber,
                                               @Parameter String sort,
                                               @Parameter Boolean asc);

}
