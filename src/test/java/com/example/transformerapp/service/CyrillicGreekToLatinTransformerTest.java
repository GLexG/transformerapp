package com.example.transformerapp.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CyrillicGreekToLatinTransformerTest {

    private CyrillicGreekToLatinTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new CyrillicGreekToLatinTransformer();
    }

    @Test
    void testTransformWithCyrillicAndGreekCharacters() {
        // Given
        String input = "Привет κόσμος"; // "Hello world" in Cyrillic and Greek
        // When
        String result = transformer.transform(input, Map.of());
        // Then
        assertEquals("Privet kosmos", result);
    }

    @Test
    void testTransformWithLatinOnly() {
        // Given
        String input = "Hello, world! 123"; // Contains only Latin and other characters
        // When
        String result = transformer.transform(input, Map.of());
        // Then
        assertEquals("Hello, world! 123", result); // No transformation should occur
    }

    @Test
    void testTransformEmptyString() {
        // Given
        String input = "";
        // When
        String result = transformer.transform(input, Map.of());
        // Then
        assertEquals("", result); // Empty string should remain empty
    }

    @Test
    void testTransformWithSpecialCharacters() {
        // Given
        String input = "¡Hola, мир!"; // Spanish greeting and "world" in Cyrillic
        // When
        String result = transformer.transform(input, Map.of());
        // Then
        assertEquals("¡Hola, mir!", result); // Only "мир" should be transformed
    }

    @Test
    void testTransformWithEdgeCharacters() {
        // Given
        String input = "Солнце ήλιος";
        // When
        String result = transformer.transform(input, Map.of());
        // Then
        assertEquals("Solntse ilios", result); // "Солнце" converted and Greek part "ήλιος"
    }
}