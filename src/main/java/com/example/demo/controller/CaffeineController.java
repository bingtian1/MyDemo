package com.example.demo.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class CaffeineController {

    public static void main(String[] args) {
        Cache<String, List<Object>> cache = Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(100)
                .weakKeys()
                .weakValues()
                .executor(new ForkJoinPool(10)).build();
        cache.put("abc", Collections.singletonList("abc-value"));
        cache.invalidateAll();
        List<Object> abc = cache.get("abc", s -> {
            return Arrays.asList("abc");
        });
        System.out.println(abc);
    }
}
