package org.idpass.lite;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.*;
import java.util.List;
import java.util.Map;

public class IdentFieldsDeserializer extends StdDeserializer<IdentFields> {

    public void f()
            throws IOException
    {
        String proj = "idpass-mosip";
        InputStream is = IdentFieldsDeserializer.class.getClassLoader().getResourceAsStream("idpass-lite-map.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode all = objectMapper.readTree(is);
        JsonNode jproj = all.get(proj);
        Map<String, FieldDesc> treeMap = objectMapper.readValue(jproj.toString(), Map.class);
    }

    public IdentFieldsDeserializer() {
        this(null);
    }

    public IdentFieldsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public IdentFields deserialize(JsonParser parser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException
    {
        IdentFields idf = null;
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream is = IdentFieldsDeserializer.class.getClassLoader().getResourceAsStream("idpass-lite-map.json");
        JsonNode jMap = objectMapper.readTree(is);

        JsonNode m = jMap.get("idpass-mosip");
        JsonNode a = m.get("lang");
        int n = a.size();
        JsonNode b = a.get(0);
        String s = b.asText();
        JsonNode c = a.get(1);
        s = c.asText();

        List<String> langs = objectMapper.readValue(is, new TypeReference<List<String>>(){});

        try {
            JsonNode colorNode = node.get("gender");
            String color = colorNode.asText();

            idf = new IdentFields();
            idf.setGender(color);

        } catch (NullPointerException e) {}

        return idf;
    }
}
