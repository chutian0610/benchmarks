package info.victorchu.jlb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackson 序列化器
 */
public class JacksonSerializer implements IJsonSerializer {

    private ObjectMapper objectMapper;


    public JacksonSerializer(ObjectMapper om) {
        this.objectMapper = om;
    }

    public String serialize(Object source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String source, Class<T> type) {
        try {
            return objectMapper.readValue(source,type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
