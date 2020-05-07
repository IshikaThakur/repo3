package com.ecommerceApp.ecommerceApp.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, String>, String> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> productVariationInfo) {

        String productVariationInfoJson = null;
        try {
            productVariationInfoJson = objectMapper.writeValueAsString(productVariationInfo);
        } catch (final JsonProcessingException e) {
            System.out.println(e);
        }

        return productVariationInfoJson;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String productVariationInfoJSON) {

        Map<String, String> productVariationInfo = null;
        try {
            productVariationInfo = objectMapper.readValue(productVariationInfoJSON, Map.class);
        } catch (final IOException e) {
            System.out.println(e);
        }

        return productVariationInfo;
    }

}
