package com.example.transformerapp.service;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.example.transformerapp.dto.DataTransformerDto;
import com.example.transformerapp.web.DataTransformerRequest;
import com.example.transformerapp.dto.TransformerDto;
import com.example.transformerapp.web.TransformerRequest;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DataTransformerDtoMapper {

    DataTransformerDto dataTransformerRequestToDto(DataTransformerRequest dataTransformerRequest);

    List<DataTransformerDto> dataTransformerRequestListToDtoList(List<DataTransformerRequest> requests);

    TransformerDto transformerRequestToDto(TransformerRequest transformerRequest);

}