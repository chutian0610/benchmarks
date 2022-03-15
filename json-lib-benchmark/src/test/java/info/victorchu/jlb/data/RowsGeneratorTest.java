package info.victorchu.jlb.data;


import com.alibaba.fastjson.JSON;
import info.victorchu.jlb.JsonLibProvider;
import org.junit.jupiter.api.Test;

class RowsGeneratorTest {

    @Test
    void populate() {
        RowsGenerator rowsGenerator = new RowsGenerator();
        Rows row1 = new Rows();
        rowsGenerator.populate(row1,1000);
        String result= JSON.toJSONString(row1);
        System.out.println(new JsonLibProvider().jackson().deserialize(result,Rows.class));
    }
}