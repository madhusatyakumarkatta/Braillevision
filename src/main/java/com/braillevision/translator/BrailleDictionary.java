package com.braillevision.translator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BrailleDictionary {
    private final Map<String, String> dictionary;

    public BrailleDictionary() {
        dictionary = new HashMap<>();
        loadDictionary();
    }

    private void loadDictionary() {
        try (InputStream is = getClass().getResourceAsStream("/braille.json")) {
            if (is != null) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> map = mapper.readValue(is, new TypeReference<Map<String, String>>() {});
                dictionary.putAll(map);
            } else {
                System.err.println("Could not find braille.json in resources");
            }
        } catch (Exception e) {
            System.err.println("Failed to load dictionary: " + e.getMessage());
        }
    }

    public String translatePattern(String binaryPattern) {
        return dictionary.getOrDefault(binaryPattern, "?");
    }
}
