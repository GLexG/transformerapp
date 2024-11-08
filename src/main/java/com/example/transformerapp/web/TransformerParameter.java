package com.example.transformerapp.web;

import lombok.Getter;

@Getter
public enum TransformerParameter {

    REGEX("regex"),

    REPLACEMENT("replacement");

    private final String value;

    TransformerParameter(String value) {
        this.value = value;
    }
}