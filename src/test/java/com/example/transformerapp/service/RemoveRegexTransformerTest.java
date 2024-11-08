package com.example.transformerapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.transformerapp.web.TransformerParameter;

class RemoveRegexTransformerTest {

    private RemoveRegexTransformer removeRegexTransformer;

    @BeforeEach
    void setUp() {
        removeRegexTransformer = new RemoveRegexTransformer();
    }

    @Test
    void testTransformValidRegex() {
        // given
        String input = "This is a test string with some numbers: 12345";
        Map<String, String> parameters = new HashMap<>();
        parameters.put(TransformerParameter.REGEX.getValue(), "\\d+");
        // when
        String result = removeRegexTransformer.transform(input, parameters);
        // then
        assertEquals("This is a test string with some numbers: ", result);
    }

    @Test
    void testTransformNoRegexMatches() {
        // given
        String input = "No numbers here!";
        Map<String, String> parameters = new HashMap<>();
        parameters.put(TransformerParameter.REGEX.getValue(), "\\d+");
        // when
        String result = removeRegexTransformer.transform(input, parameters);
        // then
        assertEquals("No numbers here!", result);
    }

    @Test
    void testTransformEmptyInput() {
        // given
        String input = "";
        Map<String, String> parameters = new HashMap<>();
        parameters.put(TransformerParameter.REGEX.getValue(), "\\d+");
        // when
        String result = removeRegexTransformer.transform(input, parameters);
        // then
        assertEquals("", result);
    }

    @Test
    void testTransformNullParameters() {
        // given
        String input = "Text without regex";
        Map<String, String> parameters = null;
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> removeRegexTransformer.transform(input, parameters));
        // then
        assertEquals("Parameters cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testTransformMissingRegexKey() {
        // given
        String input = "Text without regex";
        Map<String, String> parameters = new HashMap<>();
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> removeRegexTransformer.transform(input, parameters));
        // then
        assertEquals("Parameters cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testInvalidRegexPattern() {
        // given
        String input = "Some text with invalid regex!";
        Map<String, String> parameters = new HashMap<>();
        parameters.put(TransformerParameter.REGEX.getValue(), "[");
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> removeRegexTransformer.transform(input, parameters));

        // then
        assertTrue(exception.getMessage().contains("Invalid regex pattern"));
    }

    @Test
    void testRegexWithSpecialCharacters() {
        // given
        String input = "Hello, World! #@&*";
        Map<String, String> parameters = new HashMap<>();
        // Match any non-alphanumeric and non-space character
        parameters.put(TransformerParameter.REGEX.getValue(), "[^a-zA-Z0-9 ]");
        // when
        String result = removeRegexTransformer.transform(input, parameters);
        // then
        assertEquals("Hello World ", result);
    }
}