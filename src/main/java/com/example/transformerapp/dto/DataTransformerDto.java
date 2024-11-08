package com.example.transformerapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTransformerDto {

    private String input;
    private List<TransformerDto> transformers;
}