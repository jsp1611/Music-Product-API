package com.sample.music.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an error in processing a request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error response with details")
public class ErrorDetailResponse {

    @Schema(description = "The URL in the request")
    private String url;

    @Schema(description = "The error message from the server")
    private String message;

}
