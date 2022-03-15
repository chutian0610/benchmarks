package info.victorchu.jlb;


/**
 * 抽象JSON 序列化器
 * @param <T>
 */
public interface IJsonSerializer {

    /**
     * 将对象序列化为字符串
     * @param source
     * @return
     */
    String serialize(Object source);

    /**
     * 将字符串反序列化为对象
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    <T> T deserialize(String source, Class<T> type);
}
