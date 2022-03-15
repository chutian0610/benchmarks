package info.victorchu.jlb;

import com.alibaba.fastjson.JSON;
import info.victorchu.jlb.data.Rows;
import info.victorchu.jlb.data.RowsGenerator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(4)
@Fork(1)
public class JsonBenchMark {

    @State(Scope.Benchmark)
    public static class Context {
        public String[] jsonStrArray = new String[4];
        public JsonLibProvider jsonLibProvider;

        @Setup(Level.Trial)
        public void doSetup() {
            jsonLibProvider = new JsonLibProvider();
            RowsGenerator rowsGenerator = new RowsGenerator();
            Rows row1 = new Rows();
            rowsGenerator.populate(row1,1000);
            jsonStrArray[0] = JSON.toJSONString(row1);

            Rows row2 = new Rows();
            rowsGenerator.populate(row2,10000);
            jsonStrArray[1] = JSON.toJSONString(row2);

            Rows row3 = new Rows();
            rowsGenerator.populate(row3,100000);
            jsonStrArray[2] = JSON.toJSONString(row3);

            Rows row4 = new Rows();
            rowsGenerator.populate(row4,1000000);
            jsonStrArray[3] = JSON.toJSONString(row4);
        }
    }

    @Benchmark
    public void testGsonWith1k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.gson().deserialize(context.jsonStrArray[0],Rows.class)
        );
    }
    @Benchmark
    public void testGsonWith10k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.gson().deserialize(context.jsonStrArray[1],Rows.class)
        );
    }
    @Benchmark
    public void testGsonWith100k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.gson().deserialize(context.jsonStrArray[2],Rows.class)
        );
    }
    @Benchmark
    public void testGsonWith1000k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.gson().deserialize(context.jsonStrArray[3],Rows.class)
        );
    }

    @Benchmark
    public void testJacksonWith1k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.jackson().deserialize(context.jsonStrArray[0],Rows.class)
        );
    }
    @Benchmark
    public void testJacksonWith10k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.jackson().deserialize(context.jsonStrArray[1],Rows.class)
        );
    }
    @Benchmark
    public void testJacksonWith100k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.jackson().deserialize(context.jsonStrArray[2],Rows.class)
        );
    }
    @Benchmark
    public void testJacksonWith1000k(Context context, Blackhole blackhole) {
        blackhole.consume(
                context.jsonLibProvider.jackson().deserialize(context.jsonStrArray[3],Rows.class)
        );
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JsonBenchMark.class.getSimpleName())
                .output(System.getProperty("user.dir")+ File.separator+"Benchmark-json.log") // benchmark log
                .build();
        new Runner(options).run();
    }
}
