package com.example.transformerapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataTransformerDtoResponse {

    private String input;
    private String transformedInput;
}