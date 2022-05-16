package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
//        createStream();
//        flatMap();
//        reduce();
//        collect();
//        summaryStatistics();
        sorted();
    }

    public static void sorted(){
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "Mahesh"));
        list.add(new User(2, "Suresh"));
        list.add(new User(3, "Nilesh"));
//        list.stream().sorted().collect(Collectors.toList());
//        System.out.println(list);
//        System.out.println("============");
        list.stream().sorted(Comparator.comparing(User::getAge)).forEach(System.out::println);
    }

    public static void summaryStatistics(){
        List<Integer> numbers = Arrays.asList(-1, -2, 0, 4, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("String : " + stats.toString());
        System.out.println("Max : " + stats.getMax());
        System.out.println("Min : " + stats.getMin());
        System.out.println("Sum : " + stats.getSum());
        System.out.println("Average : " + stats.getAverage());
        System.out.println("Count : " + stats.getCount());
    }

    public static void collect(){
        List<Integer> numbers = Arrays.asList(-1, -2, 0, 4, 5);
        List<Integer> abss = numbers.stream().map( n -> Math.abs(n)).collect(Collectors.toList());
        System.out.println("Abs list: " + abss);
    }

    public static void reduce(){
        List<Integer> numbers = Arrays.asList(-1, -2, 0, -1, 4, 5, 1);
        Integer total = numbers.stream().reduce((t, n) -> t + n).get();
        System.out.println("Total: " + total);
    }


    public static void createStream(){
        //
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Stream<String> stream1 = list.parallelStream();
        list.forEach(i -> System.out.println("list.forEach: " + i));
        stream1.forEach(i -> System.out.println("stream1.forEach: " + i));

        Cat[] cats = new Cat[10];
        Stream<Cat> stream2 = Arrays.stream(cats);

        Stream<String> stream3 = Stream.of("a","b","c");
//        stream3.forEach(x -> System.out.println("List element: " + x));

//        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }

    public static void flatMap(){
        List<String> list = Arrays.asList("1 2", "3 4", "5 6");
        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).forEach(System.out::println);
        System.out.println("==================");
        list.stream().map(item -> Arrays.stream(item.split(" "))).forEach(n ->n.forEach(System.out::println));
        System.out.println("==================");
        list.stream().map(item -> Arrays.stream(item.split(" "))).forEach(System.out::println);
    }
}

@Data
@AllArgsConstructor
@ToString
class User{
    private int age;
    private String name;
}