package info.victorchu.jlb;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class JsonLibProvider {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsString()))
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (value, typeOfSrc, context) -> new JsonPrimitive(value.toString()))
            .registerTypeAdapter(OffsetDateTime.class, (JsonDeserializer<OffsetDateTime>) (json, type, jsonDeserializationContext) -> OffsetDateTime.parse(json.getAsString()))
            .registerTypeAdapter(OffsetDateTime.class, (JsonSerializer<OffsetDateTime>) (value, typeOfSrc, context) -> new JsonPrimitive(value.toString()))
            .create();

    private final ObjectMapper jackson = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//            .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
            .registerModule(new JavaTimeModule());



    public GsonSerializer gson(){
        return new GsonSerializer(gson);
    }

    public JacksonSerializer jackson() {
        return new JacksonSerializer(jackson);
    }
}
