package com.example.transformerapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transformerapp.facade.DataTransformerFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.transformerapp.dto.DataTransformerDtoResponse;
import com.example.transformerapp.web.DataTransformerRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DataTransformerController {

    private final DataTransformerFacade dataTransformerFacade;

    @PostMapping("/transformations")
    @Operation(
        summary = "Apply transformations to a list of elements",
        description = "Takes a list of elements, each with a value and a list of specified transformers. Applies each transformer to the element's value in the given order and returns the transformed values.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully applied transformations",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DataTransformerDtoResponse.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input, check the provided data",
                content = @Content(mediaType = "application/json")
            )
        }
    )
    public ResponseEntity<List<DataTransformerDtoResponse>> process(@Valid @RequestBody List<DataTransformerRequest> requests){

        return ResponseEntity.ok(dataTransformerFacade.transform(requests));
    }

}
