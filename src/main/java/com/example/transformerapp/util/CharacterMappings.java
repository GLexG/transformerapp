package com.example.transformerapp.util;

import java.util.Map;

public class CharacterMappings {

    private CharacterMappings(){}

    public static final Map<Character, String> cyrillicToLatin = Map.<Character, String>ofEntries(
        // Basic Cyrillic letters
        Map.entry('А', "A"), Map.entry('Б', "B"), Map.entry('В', "V"), Map.entry('Г', "G"),
        Map.entry('Д', "D"), Map.entry('Е', "E"), Map.entry('Ё', "E"), Map.entry('Ж', "Zh"),
        Map.entry('З', "Z"), Map.entry('И', "I"), Map.entry('Й', "I"), Map.entry('К', "K"),
        Map.entry('Л', "L"), Map.entry('М', "M"), Map.entry('Н', "N"), Map.entry('О', "O"),
        Map.entry('П', "P"), Map.entry('Р', "R"), Map.entry('С', "S"), Map.entry('Т', "T"),
        Map.entry('У', "U"), Map.entry('Ф', "F"), Map.entry('Х', "Kh"), Map.entry('Ц', "Ts"),
        Map.entry('Ч', "Ch"), Map.entry('Ш', "Sh"), Map.entry('Щ', "Shch"), Map.entry('Ы', "Y"),
        Map.entry('Э', "E"), Map.entry('Ю', "Yu"), Map.entry('Я', "Ya"), Map.entry('а', "a"),
        Map.entry('б', "b"), Map.entry('в', "v"), Map.entry('г', "g"), Map.entry('д', "d"),
        Map.entry('е', "e"), Map.entry('ё', "e"), Map.entry('ж', "zh"), Map.entry('з', "z"),
        Map.entry('и', "i"), Map.entry('й', "i"), Map.entry('к', "k"), Map.entry('л', "l"),
        Map.entry('м', "m"), Map.entry('н', "n"), Map.entry('о', "o"), Map.entry('п', "p"),
        Map.entry('р', "r"), Map.entry('с', "s"), Map.entry('т', "t"), Map.entry('у', "u"),
        Map.entry('ф', "f"), Map.entry('х', "kh"), Map.entry('ц', "ts"), Map.entry('ч', "ch"),
        Map.entry('ш', "sh"), Map.entry('щ', "shch"), Map.entry('ы', "y"), Map.entry('э', "e"),
        Map.entry('ю', "yu"), Map.entry('я', "ya"),

        // Serbian Cyrillic (specific to Serbian language)
        Map.entry('Љ', "Lj"), Map.entry('љ', "lj"),
        Map.entry('Њ', "Nj"), Map.entry('њ', "nj"),
        Map.entry('Ђ', "Đ"), Map.entry('ђ', "đ"),
        Map.entry('Ј', "J"), Map.entry('ј', "j"),

        // Additional Cyrillic characters for Ukrainian, Kazakh, etc. (already included above)
        Map.entry('Ґ', "G"), Map.entry('ґ', "g"),
        Map.entry('Є', "Ye"), Map.entry('є', "ye"),
        Map.entry('І', "I"), Map.entry('і', "i"),
        Map.entry('Ї', "Yi"), Map.entry('ї', "yi"),
        Map.entry('Ұ', "U"), Map.entry('ұ', "u"),
        Map.entry('Ү', "Ü"), Map.entry('ү', "ü"),
        Map.entry('Ә', "Ä"), Map.entry('ә', "ä"),
        Map.entry('Ө', "Ö"), Map.entry('ө', "ö"),
        Map.entry('Қ', "Q"), Map.entry('қ', "q"),
        Map.entry('Ғ', "Ğ"), Map.entry('ғ', "ğ"),

        // Additional ancient and special Cyrillic letters
        Map.entry('Ѳ', "F"), Map.entry('ѳ', "f"),
        Map.entry('Ѵ', "Y"), Map.entry('ѵ', "y")
    );

    public static final Map<Character, String> greekToLatin = Map.<Character, String>ofEntries(
        // Lowercase Greek letters
        Map.entry('α', "a"), Map.entry('β', "b"), Map.entry('γ', "g"), Map.entry('δ', "d"),
        Map.entry('ε', "e"), Map.entry('ζ', "z"), Map.entry('η', "i"), Map.entry('θ', "th"),
        Map.entry('ι', "i"), Map.entry('κ', "k"), Map.entry('λ', "l"), Map.entry('μ', "m"),
        Map.entry('ν', "n"), Map.entry('ξ', "x"), Map.entry('ο', "o"), Map.entry('π', "p"),
        Map.entry('ρ', "r"), Map.entry('σ', "s"), Map.entry('τ', "t"), Map.entry('υ', "y"),
        Map.entry('φ', "f"), Map.entry('χ', "ch"), Map.entry('ψ', "ps"), Map.entry('ω', "o"),

        // Uppercase Greek letters
        Map.entry('Α', "A"), Map.entry('Β', "B"), Map.entry('Γ', "G"), Map.entry('Δ', "D"),
        Map.entry('Ε', "E"), Map.entry('Ζ', "Z"), Map.entry('Η', "I"), Map.entry('Θ', "TH"),
        Map.entry('Ι', "I"), Map.entry('Κ', "K"), Map.entry('Λ', "L"), Map.entry('Μ', "M"),
        Map.entry('Ν', "N"), Map.entry('Ξ', "X"), Map.entry('Ο', "O"), Map.entry('Π', "P"),
        Map.entry('Ρ', "R"), Map.entry('Σ', "S"), Map.entry('Τ', "T"), Map.entry('Υ', "Y"),
        Map.entry('Φ', "F"), Map.entry('Χ', "CH"), Map.entry('Ψ', "PS"), Map.entry('Ω', "O"),

        // Final Sigma (used at the end of words)
        Map.entry('ς', "s"),

        // Greek letters with tonal accents (Tonos)
        Map.entry('ά', "a"), Map.entry('έ', "e"), Map.entry('ή', "i"), Map.entry('ί', "i"),
        Map.entry('ό', "o"), Map.entry('ύ', "y"), Map.entry('ώ', "o"),

        // Greek letters with Dialytika (¨) - shows separate vowels
        Map.entry('ϊ', "i"), Map.entry('ϋ', "y"), Map.entry('ΰ', "y"),

        // Greek letters with Breathing Marks (used in Ancient Greek)
        Map.entry('ἀ', "a"), Map.entry('ἁ', "a"), Map.entry('ἄ', "a"), Map.entry('ἅ', "a"),
        Map.entry('ἂ', "a"), Map.entry('ἃ', "a"), Map.entry('ἆ', "a"), Map.entry('ἇ', "a"),
        Map.entry('Ἀ', "A"), Map.entry('Ἁ', "A"), Map.entry('Ἄ', "A"), Map.entry('Ἅ', "A"),

        // Greek question mark (used like a semicolon in English)
        Map.entry(';', "?")
    );
}
