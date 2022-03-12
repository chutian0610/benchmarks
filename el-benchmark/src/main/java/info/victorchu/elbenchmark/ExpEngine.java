package info.victorchu.elbenchmark;

import java.util.Map;

/**
 * 表达式引擎的抽象
 */
public interface ExpEngine {

    /**
     * 执行表达式
     * @param exp 表达式文本
     * @param env 环境变量
     * @return
     */
    Object evaluate(String exp, Map<String, Object> env);
}
