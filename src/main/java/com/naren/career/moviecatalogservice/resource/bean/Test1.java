package com.naren.career.moviecatalogservice.resource.bean;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {
        Comparator<String> SortComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.compareTo(o2));
            }
        };

        Comparator<String> sortComparator = String::compareTo;
        List<String> allColorBalls = Arrays.asList("r1", "b1", "g1", "r2", "b3", "g4", "r5", "b1", "g1", "r6", "b7", "g8", "r3", "b3", "g6", "r8", "b9", "g3", "r1", "b6", "g1", "r8", "b9", "g2");
        List<String> r = allColorBalls.stream()
                .sorted(sortComparator).collect(Collectors.toList());
        System.out.println("Red Color balls: " + r);
    }
}
