package com.example.transformerapp.service;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.transformerapp.util.RegexUtils;
import com.example.transformerapp.web.TransformerParameter;

@Transformer(groupId = "1", transformerId = "removeRegex")
@Service
public class RemoveRegexTransformer implements StringTransformer {

    @Override
    public String transform(String value, Map<String, String> parameters) {
        validateParameters(parameters);

        String regex = parameters.get(TransformerParameter.REGEX.getValue());

        Pattern pattern = RegexUtils.compileRegexPattern(regex);

        return pattern.matcher(value).replaceAll("");
    }

    private void validateParameters(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            throw new IllegalArgumentException("Parameters cannot be null or empty.");
        }

        if (!parameters.containsKey(TransformerParameter.REGEX.getValue())) {
            throw new IllegalArgumentException("Parameters must contain a regex key.");
        }
    }

}
