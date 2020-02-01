package com.test.hci.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class TrimStringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserialize(jsonParser.getText());
    }

    public static String deserialize(String text) {
        if (text != null) {
            String result = text.trim();
            if (!result.equalsIgnoreCase("")) {
                return result;
            }
        }
        return null;
    }
}