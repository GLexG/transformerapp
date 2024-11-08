package com.example.transformerapp.facade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.transformerapp.dto.DataTransformerDto;
import com.example.transformerapp.dto.DataTransformerDtoResponse;
import com.example.transformerapp.service.DataTransformerDtoMapper;
import com.example.transformerapp.service.DataTransformerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.transformerapp.web.DataTransformerRequest;

// The Facade pattern is added here to illustrate clean architecture: it provides Simplification with a single interface,
// Decoupling for subsystem independence, and Manageability to improve readability and maintainability,
// though in simple application like this one it's an overhead.
@RequiredArgsConstructor
@Service
@Slf4j
public class DataTransformerFacade {

    private final DataTransformerService dataTransformerService;
    private final DataTransformerDtoMapper dataTransformerDtoMapper;

    public List<DataTransformerDtoResponse> transform(@Valid List<DataTransformerRequest> requests) {
        List<DataTransformerDto> dataTransformerDtos = dataTransformerDtoMapper.dataTransformerRequestListToDtoList(requests);
        return dataTransformerService.transform(dataTransformerDtos);
    }
}