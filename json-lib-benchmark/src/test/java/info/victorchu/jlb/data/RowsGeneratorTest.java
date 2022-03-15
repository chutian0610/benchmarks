package info.victorchu.jlb.data;


import org.junit.jupiter.api.Test;

class RowsGeneratorTest {

    @Test
    void populate() {
        Rows rows = new Rows();
        new RowsGenerator().populate(rows,10000);
        System.out.println(rows);
    }
}