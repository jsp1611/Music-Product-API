package com.sample.music.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Interface for system controller, containing Swagger annotations for use in
 * generating the UI.
 */
@Tag(name = "System API")
public interface ISystemController {

    @Operation(summary = "Ping the server")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Pong response",
                    content = @Content(mediaType = "text/plain"))
    )
    String ping();

}