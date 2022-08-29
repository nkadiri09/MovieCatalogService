package com.naren.career.moviecatalogservice.resource.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String st = "Narendra Reddy Kadiri";
        Map<String, Long> collect = st.chars().mapToObj((Character::toString)).collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
        System.out.println("Collect is:" + collect);
        List<String> strings = Arrays.asList("Kadiri", "Narendra", "Reddy");
        strings.forEach(System.out::println);
    }

    Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            // Perform some computation
            Thread.sleep(2000);
            return "Return some result";
        }
    };


}
