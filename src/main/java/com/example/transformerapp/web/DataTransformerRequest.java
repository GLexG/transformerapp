package com.example.transformerapp.web;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class DataTransformerRequest {

    @NotEmpty(message = "value cannot be empty")
    private String input;

    @NotEmpty(message = "transformers cannot be empty")
    private List<TransformerRequest> transformers;
}