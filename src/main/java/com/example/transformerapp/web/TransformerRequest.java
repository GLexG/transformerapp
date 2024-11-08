package com.example.transformerapp.web;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformerRequest {

    @NotNull(message = "groupId cannot be null")
    private String groupId;

    @NotNull(message = "transformerId cannot be null")
    private String transformerId;

    private Map<String, String> parameters;

}