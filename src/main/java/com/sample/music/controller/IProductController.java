package com.sample.music.controller;

import com.sample.music.dto.CreateProductRequest;
import com.sample.music.dto.ErrorDetailResponse;
import com.sample.music.dto.ProductResponse;
import com.sample.music.dto.UpdateProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for actions related to <code>Product</code>, containing Swagger annotations
 * used to generate the UI.
 */
@Tag(name = "Products API")
public interface IProductController {

    @Operation(summary = "Create a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetailResponse.class)
                    ))
            }
    )
    ResponseEntity<ProductResponse> create(@RequestBody(required = true) CreateProductRequest request);

    @Operation(summary = "Fetch a Product")
    @Parameters(value = {
            @Parameter(name = "id", description = "Product id", example = "123")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetailResponse.class)))
            }
    )
    ResponseEntity<ProductResponse> fetch(@Parameter(required = true) long id);

    @Operation(summary = "Update a Product")
    @Parameters(value = {
            @Parameter(name = "id", description = "Product id", example = "123")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetailResponse.class)))
            }
    )
    ResponseEntity<ProductResponse> update(@Parameter(required = true) long id,
                                           @RequestBody(required = true) UpdateProductRequest request);

    @Operation(summary = "Delete a Product")
    @Parameters(value = {
            @Parameter(name = "id", description = "Product id", example = "123")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetailResponse.class)))
            }
    )
    ResponseEntity<ProductResponse> delete(@Parameter(required = true) long id);

    @Operation(summary = "Find products based upon supplied filters")
    @Parameters(value = {
            @Parameter(name = "title", description = "Product title", example = "Some CD Name"),
            @Parameter(name = "artist", description = "Product artist", example = "Aural Damage"),
            @Parameter(name = "label", description = "Product label", example = "Warp Records"),
            @Parameter(name = "store", description = "Product store", example = "Core Store"),
            @Parameter(name = "startRelease", description = "Start release date", example = "2020-12-15"),
            @Parameter(name = "endRelease", description = "End release date", example = "2021-01-17"),
            @Parameter(name = "pageSize", description = "Page size for results", example = "10"),
            @Parameter(name = "pageNumber", description = "Page number for results", example = "0"),
            @Parameter(name = "sort", description = "Name of field to sort on", example = "title"),
            @Parameter(name = "asc", description = "Sort order ascending", example = "true")
    })
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
