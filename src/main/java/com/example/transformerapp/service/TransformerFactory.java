package com.example.transformerapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransformerFactory {

    private static final String DELIMITER = "-";
    private final Map<String, StringTransformer> transformers = new HashMap<>();

    @Autowired
    public TransformerFactory(List<StringTransformer> transformerList) {
        transformerList.forEach(transformer -> {
            Transformer annotation = transformer.getClass().getAnnotation(Transformer.class);
            if (annotation != null) {
                String key = annotation.groupId() + DELIMITER + annotation.transformerId();
                transformers.put(key, transformer);
            }
        });
    }

    public Optional<StringTransformer> getTransformer(String transformerId, String groupId) {
        return Optional.ofNullable(transformers.get(groupId + DELIMITER + transformerId));
    }

}
