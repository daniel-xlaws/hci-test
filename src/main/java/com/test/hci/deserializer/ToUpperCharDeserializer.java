package com.test.hci.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ToUpperCharDeserializer extends JsonDeserializer<Character> {
    @Override
    public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserialize(jsonParser.getText());
    }

    public static char deserialize(String text) {
        return text.toUpperCase().charAt(0);
    }
}