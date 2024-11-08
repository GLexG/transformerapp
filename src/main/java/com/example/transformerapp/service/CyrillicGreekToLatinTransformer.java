package com.example.transformerapp.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.transformerapp.util.CharacterMappings;

@Transformer(groupId = "2", transformerId = "cyrillicGreekToLatin")
@Service
public class CyrillicGreekToLatinTransformer implements StringTransformer {

    @Override
    public String transform(String value, Map<String, String> parameters) {

        return transformCyrillicGreekToLatin(value);
    }

    private String transformCyrillicGreekToLatin(String value) {

        StringBuilder result = new StringBuilder(value.length());

        for (char c : value.toCharArray()) {
            String replacement = CharacterMappings.cyrillicToLatin.get(c);
            if (replacement == null) {
                replacement = CharacterMappings.greekToLatin.get(c);
            }
            result.append(replacement != null ? replacement : c);
        }
        return result.toString();
    }
}
