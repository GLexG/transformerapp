package com.example.transformerapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.transformerapp.dto.DataTransformerDto;
import com.example.transformerapp.dto.DataTransformerDtoResponse;
import com.example.transformerapp.dto.TransformerDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataTransformerService {

    private final TransformerFactory transformerFactory;

    public List<DataTransformerDtoResponse> transform(List<DataTransformerDto> dataTransformerDtos) {

        return dataTransformerDtos.parallelStream()
            .map(this::applyTransformers)
            .toList();
    }

    private DataTransformerDtoResponse applyTransformers(DataTransformerDto dataTransformerDto) {

        validateDataTransformerDto(dataTransformerDto);

        String originalValue = dataTransformerDto.getInput();
        String transformedValue = originalValue;

        for (TransformerDto transformerDto : dataTransformerDto.getTransformers()) {
            StringTransformer transformer = getTransformer(transformerDto);
            transformedValue = transformer.transform(transformedValue, transformerDto.getParameters());
        }

        return buildResponse(originalValue, transformedValue);
    }

    private void validateDataTransformerDto(DataTransformerDto dataTransformerDto) {
        if (dataTransformerDto == null) {
            throw new IllegalArgumentException("DataTransformerDto cannot be null.");
        }

        if (dataTransformerDto.getInput() == null || dataTransformerDto.getInput().isEmpty()) {
            throw new IllegalArgumentException("Original input value cannot be null or empty.");
        }

        if (dataTransformerDto.getTransformers() == null || dataTransformerDto.getTransformers().isEmpty()) {
            throw new IllegalArgumentException("Transformers list cannot be null or empty.");
        }

        for (TransformerDto transformerDto : dataTransformerDto.getTransformers()) {
            if (transformerDto.getTransformerId() == null || transformerDto.getTransformerId().isEmpty()) {
                throw new IllegalArgumentException("Transformer Id cannot be null or empty.");
            }
            if (transformerDto.getGroupId() == null || transformerDto.getGroupId().isEmpty()) {
                throw new IllegalArgumentException("Transformer Group Id cannot be null or empty.");
            }

        }
    }

    private DataTransformerDtoResponse buildResponse(String originalValue, String transformedValue) {
        return DataTransformerDtoResponse.builder()
            .input(originalValue)
            .transformedInput(transformedValue)
            .build();
    }

    private StringTransformer getTransformer(TransformerDto transformerDto) {
        return transformerFactory.getTransformer(transformerDto.getTransformerId(), transformerDto.getGroupId())
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("Transformer not found: for group id: %s, and transformer id: %s", transformerDto.getGroupId(), transformerDto.getTransformerId())));
    }

}