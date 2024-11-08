package com.example.transformerapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.transformerapp.web.TransformerParameter;

class ReplaceRegexTransformerTest {

    private ReplaceRegexTransformer replaceRegexTransformer;

    @BeforeEach
    void setUp() {
        replaceRegexTransformer = new ReplaceRegexTransformer();
    }

    @Test
    void testTransformValidInput() {
        // given
        String input = "Hello 123, this is a test for removing digits 456";
        String regex = "\\d+";
        String replacement = "#";

        Map<String, String> parameters = Map.of(TransformerParameter.REGEX.getValue(), regex, TransformerParameter.REPLACEMENT.getValue(),
            replacement);
        // when
        String result = replaceRegexTransformer.transform(input, parameters);
        // then
        assertEquals("Hello #, this is a test for removing digits #", result);
    }

    @Test
    void testTransformNoMatches() {
        // given
        String input = "No numbers here!";
        String regex = "\\d+";
        String replacement = "#";

        Map<String, String> parameters = Map.of(TransformerParameter.REGEX.getValue(), regex, TransformerParameter.REPLACEMENT.getValue(),
            replacement);
        // when
        String result = replaceRegexTransformer.transform(input, parameters);
        // then
        assertEquals("No numbers here!", result);
    }

    @Test
    void testTransformMissingRegex() {
        // given
        String input = "Hello 123, this is a test";
        String replacement = "#";

        Map<String, String> parameters = Map.of(TransformerParameter.REPLACEMENT.getValue(), replacement);
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> replaceRegexTransformer.transform(input, parameters));
        // then
        assertEquals("Parameters cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testTransformEmptyParameters() {
        //given
        String input = "Hello 123, this is a test";

        Map<String, String> parameters = Map.of();
        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> replaceRegexTransformer.transform(input, parameters));
        // then
        assertEquals("Parameters cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testTransformNullParameters() {
        // given
        String input = "Hello 123, this is a test";
        Map<String, String> parameters = null;
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> replaceRegexTransformer.transform(input, parameters));
        // then
        assertEquals("Parameters cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testTransformInsufficientParameters() {
        // given
        String input = "Hello 123, this is a test";
        String regex = "\\d+";
        Map<String, String> parameters = Map.of(TransformerParameter.REGEX.getValue(), regex);
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> replaceRegexTransformer.transform(input, parameters));
        // then
        assertEquals("Parameters cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testTransformEmptyInput() {
        //  given
        String input = "";
        String regex = "\\d+";
        String replacement = "#";

        Map<String, String> parameters = Map.of(TransformerParameter.REGEX.getValue(), regex, TransformerParameter.REPLACEMENT.getValue(),
            replacement);
        //  when
        String result = replaceRegexTransformer.transform(input, parameters);
        // then
        assertEquals("", result);
    }

    @Test
    void testTransformSpecialCharacters() {
        //  given
        String input = "abc$%123#@";
        String regex = "[^a-zA-Z0-9]";
        String replacement = "_";

        Map<String, String> parameters = Map.of(TransformerParameter.REGEX.getValue(), regex, TransformerParameter.REPLACEMENT.getValue(),
            replacement);
        //  when
        String result = replaceRegexTransformer.transform(input, parameters);
        // then
        assertEquals("abc__123__", result);
    }
}