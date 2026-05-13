package com.alvinskylers.products.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SlugAndSkuGenerator {

    public String generateSlug(String name) {
        return name.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }

    public String generateSKU(String name) {
        String clean = name.replaceAll("\\s+", "").toUpperCase();
        String prefix = clean.substring(0, 3);
        String suffix = clean.substring(clean.length() -3);
        String unique = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0,6).toUpperCase();

        return prefix + "-" + unique + "-" + suffix;
    }
}
