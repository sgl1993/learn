package com.sgl.learn.jdk.jdk8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Description：流 （Stream）
 * 1、是什么？
 *  Stream（流）是一个来自数据源的元素队列并支持聚合操作
 *
 * 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 * 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 * 和以前的Collection操作不同， Stream操作还有两个基础的特征：
 *
 * Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
 * 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
 *
 * 2、怎么做：
 *  在 Java 8 中, 集合接口有两个方法来生成流：
 *      stream() − 为集合创建串行流。
 *
 *      parallelStream() − 为集合创建并行流。
 *
 * 3、使用方法：
 *  > 【forEach】:Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
 *  Random random = new Random();
 *  random.ints().limit(10).forEach(System.out::println);
 *
 *  > 【map】:用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
 *  List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
 *  // 获取对应的平方数
 *  List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
 *
 *  > 【filter】:用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
 *  List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
 *  // 获取空字符串的数量
 *  int count = strings.stream().filter(string -> string.isEmpty()).count();
 *
 *  > 【limit】:limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
 *  Random random = new Random();
 *  random.ints().limit(10).forEach(System.out::println);
 *
 *  > 【sorted】:sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
 *  Random random = new Random();
 *  random.ints().limit(10).sorted().forEach(System.out::println);
 *
 *  > 【并行（parallel）程序】:parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
 *  List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
 *  // 获取空字符串的数量
 *  int count = strings.parallelStream().filter(string -> string.isEmpty()).count();
 *
 *  > 【Collectors】:Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
 *  List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
 *  List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
 *
 *  System.out.println("筛选列表: " + filtered);
 *  String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
 *  System.out.println("合并字符串: " + mergedString);
 *
 *  > 【summaryStatistics】:产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
 *  List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
 *  IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
 *
 *  System.out.println("列表中最大的数 : " + stats.getMax());
 *  System.out.println("列表中最小的数 : " + stats.getMin());
 *  System.out.println("所有数之和 : " + stats.getSum());
 *  System.out.println("平均数 : " + stats.getAverage());
 *
 * @author shaoguoli
 * @date 16:54 2018/7/26
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        Random random = new Random();

        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(s -> s.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> collect = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        collect.stream().forEach(string -> System.out.println("筛选后的列表:" + string));

        String joinStr = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + joinStr);

        List<Integer> mapList = numbers.stream().map(num -> num * num).collect(Collectors.toList());
        System.out.println("Squares List: " + mapList);

        IntSummaryStatistics summaryStatistics = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + summaryStatistics.getMax());
        System.out.println("列表中最小的数 : " + summaryStatistics.getMin());
        System.out.println("所有数之和 : " + summaryStatistics.getSum());
        System.out.println("平均数 : " + summaryStatistics.getAverage());

        //6个随机数 并排序输出
        random.ints().limit(6).sorted().forEach(System.out::println);

        //并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("并行处理空字符串的数量为: " + count);

        System.out.println("430181199308130317".replaceAll("(\\d{3})\\d{11}(\\w{4})","$1***********$2"));
    }

}
