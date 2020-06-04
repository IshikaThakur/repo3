package com.ecommerceApp.ecommerceApp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter2 implements AttributeConverter<Map<String,String>,String>
{
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> reportInfo) {

        String reportInfoJson = null;
        try {
            reportInfoJson = objectMapper.writeValueAsString(reportInfo);
        } catch (final JsonProcessingException e) {
            System.out.println(e);
        }

        return reportInfoJson;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String reportInfoJSON) {

        Map<String, String> reportInfo = null;
        try {
            reportInfo = objectMapper.readValue(reportInfoJSON, Map.class);
        } catch (final IOException e) {
            System.out.println(e);
        }

        return reportInfo;
    }

}
