package com.example.transformerapp.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransformerDto {

    private String groupId;
    private String transformerId;
    private Map<String, String> parameters;
}
