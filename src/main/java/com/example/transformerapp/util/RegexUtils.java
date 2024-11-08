package com.example.transformerapp.util;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexUtils {
    private RegexUtils() {}

    public static Pattern compileRegexPattern(String regex) {
        try {
            return Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid regex pattern: " + e.getMessage(), e);
        }
    }
}
