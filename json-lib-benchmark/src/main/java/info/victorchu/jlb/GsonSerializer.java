package info.victorchu.jlb;

import com.google.gson.Gson;

public class GsonSerializer implements IJsonSerializer {

    private Gson gson;

    public GsonSerializer(Gson gson){
        this.gson = gson;
    }

    public String serialize(Object source) {

        return gson.toJson(source);
    }

    public <T> T deserialize(String source, Class<T> type) {
        return gson.fromJson(source, type);
    }
}
