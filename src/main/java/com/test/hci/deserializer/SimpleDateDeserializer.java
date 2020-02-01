package com.test.hci.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            if (jsonParser.getText() != null && !jsonParser.getText().equalsIgnoreCase("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                return sdf.parse(jsonParser.getText());
            }
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
