package info.victorchu.collection;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MultiReaderList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.StringIterate;
import org.openjdk.jol.info.GraphLayout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CollectionBenchmark
{
    private static MultiReaderList<Triple<String, Long, Long>> RESULTS =
            Lists.multiReader.empty();
    private static void recordTriple(String list, Object ecType, Object jdkType)
    {
        long ecPrimitiveSize = GraphLayout.parseInstance(ecType).totalSize();
        long jdkBoxedSize = GraphLayout.parseInstance(jdkType).totalSize();
        RESULTS.add(Tuples.triple(list, ecPrimitiveSize, jdkBoxedSize));
    }

    public static void intList(int size)
    {
        MutableIntList bigIntList =
                IntInterval.oneTo(size).toList();
        List<Integer> bigIntegerList =
                new ArrayList<>(Interval.oneTo(size));

        recordTriple("IntList:"+size, bigIntList, bigIntegerList);
    }

    public static void main(String[] args)
    {
        intList(10);
        intList(100);
        intList(1000);
        intList(10_000);
        intList(100_000);
        intList(200_000);
        intList(500_000);
        intList(1000_000);
        RESULTS.sortThisByLong(triple -> Math.min(triple.getTwo(), triple.getThree()));
        System.out.println(StringIterate.padOrTrim("Primitive vs. Boxed Collections", 49));
        System.out.println(StringIterate.repeat("-", 49));
        System.out.println("                          " + StringIterate.padOrTrim("EC Primitive", 12) + " " + StringIterate.padOrTrim("JDK Boxed", 12));
        RESULTS.each(triple -> System.out.println("Type: " + StringIterate.padOrTrim(triple.getOne(), 20)
                + " Size (bytes): " + StringIterate.padOrTrim(String.valueOf(triple.getTwo()), 12)
                + " " + StringIterate.padOrTrim(String.valueOf(triple.getThree()), 12)));
    }
}
