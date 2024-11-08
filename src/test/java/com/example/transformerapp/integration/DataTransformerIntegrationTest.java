package com.example.transformerapp.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.transformerapp.web.DataTransformerRequest;
import com.example.transformerapp.web.TransformerRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataTransformerIntegrationTest {

    private static final String BASE_PATH = "/api/v1/transformations";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    void testTransformDataWithMultipleTransformers() {
        // Given
        TransformerRequest transformerRequestToLatin = TransformerRequest.builder()
            .transformerId("cyrillicGreekToLatin")
            .groupId("2")
            .parameters(Map.of("regex", "\\d+"))
            .build();

        TransformerRequest transformerRequestReplaceRegex = TransformerRequest.builder()
            .transformerId("replaceRegex")
            .groupId("1")
            .parameters(Map.of("regex", "\\d+", "replacement", "#"))
            .build();

        TransformerRequest transformerRequestRemoveRegex = TransformerRequest.builder()
            .transformerId("removeRegex")
            .groupId("1")
            .parameters(Map.of("regex", "#"))
            .build();

        List<DataTransformerRequest> dataTransformerRequest = Collections.singletonList(DataTransformerRequest.builder()
            .input("Привет κόσμος123, this is a test999 for replacing digits with # and then removing the #")
            .transformers(Arrays.asList(transformerRequestToLatin, transformerRequestReplaceRegex, transformerRequestRemoveRegex))
            .build());

        // When/Then: Sending the POST request to the transformation endpoint
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dataTransformerRequest)
            .when()
            .post(BASE_PATH)
            .then()
            .statusCode(200)
            .body("size()", equalTo(1))
            .body("[0].input", equalTo("Привет κόσμος123, this is a test999 for replacing digits with # and then removing the #"))
            .body("[0].transformedInput", equalTo("Privet kosmos, this is a test for replacing digits with  and then removing the "));
    }

    @Test
    void testTransformDataWithInvalidRegex() {
        // Given: Invalid regex pattern in replaceRegex transformer
        TransformerRequest transformerRequestReplaceRegex = TransformerRequest.builder()
            .transformerId("replaceRegex")
            .groupId("1")
            .parameters(Map.of("regex", "[", "replacement", "#"))  // Invalid regex pattern
            .build();

        List<DataTransformerRequest> dataTransformerRequest = Collections.singletonList(DataTransformerRequest.builder()
            .input("Invalid regex test")
            .transformers(Collections.singletonList(transformerRequestReplaceRegex))
            .build());

        // When/Then
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dataTransformerRequest)
            .when()
            .post(BASE_PATH)
            .then()
            .statusCode(400)  // Expecting a Bad Request due to invalid regex
            .body("error_description", containsString("Invalid arguments"));
    }

    @Test
    void testTransformDataWithInvalidTransformerId() {
        // Given: Invalid transformer ID
        TransformerRequest transformerRequestInvalid = TransformerRequest.builder()
            .transformerId("invalidTransformer")
            .groupId("1")
            .parameters(Map.of("regex", "\\d+", "replacement", "#"))
            .build();

        List<DataTransformerRequest> dataTransformerRequest = Collections.singletonList(DataTransformerRequest.builder()
            .input("Test123")
            .transformers(Collections.singletonList(transformerRequestInvalid))
            .build());

        // When/Then: Sending the POST request to the transformation endpoint
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dataTransformerRequest)
            .when()
            .post(BASE_PATH)
            .then()
            .statusCode(400)
            .body("error_description", containsString("Transformer not found"));
    }

    @Test
    void testTransformDataWithEmptyInput() {
        // Given: Empty input string
        TransformerRequest transformerRequestToLatin = TransformerRequest.builder()
            .transformerId("cyrillicGreekToLatin")
            .groupId("2")
            .parameters(Map.of())
            .build();

        List<DataTransformerRequest> dataTransformerRequest = Collections.singletonList(DataTransformerRequest.builder()
            .input("")
            .transformers(Collections.singletonList(transformerRequestToLatin))
            .build());

        // When/Then: Sending the POST request to the transformation endpoint
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dataTransformerRequest)
            .when()
            .post(BASE_PATH)
            .then()
            .statusCode(400)
            .body("error", containsString("Bad Request"));
    }

    @Disabled("takes long time to run")
    @Test
    void testTransformDataWithLargeInputs() {
        // Given
        List<DataTransformerRequest> dataTransformerRequests = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            List<TransformerRequest> transformers = new ArrayList<>();
            transformers.add(TransformerRequest.builder()
                .transformerId("replaceRegex")
                .groupId("1")
                .parameters(Map.of("regex", "\\d+", "replacement", "#"))
                .build());

            transformers.add(TransformerRequest.builder()
                .transformerId("cyrillicGreekToLatin")
                .groupId("2")
                .parameters(Map.of())
                .build());

            dataTransformerRequests.add(DataTransformerRequest.builder()
                .input("Test123 for regex replace " + i)
                .transformers(transformers)
                .build());
        }

        // When/Then: Sending the POST request to the transformation endpoint
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dataTransformerRequests)
            .when()
            .post(BASE_PATH)
            .then()
            .statusCode(200)
            .body("size()", equalTo(100000))
            .body("[0].input", equalTo("Test123 for regex replace 0"))
            .body("[0].transformedInput", equalTo("Test# for regex replace #"))
            .body("[99999].input", equalTo("Test123 for regex replace 99999"))
            .body("[99999].transformedInput", equalTo("Test# for regex replace #"));
    }
}