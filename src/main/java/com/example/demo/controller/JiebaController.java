package com.example.demo.controller;

import com.example.demo.config.WordsConfig;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jieba")
public class JiebaController {

    @Resource
    private WordsConfig.Words stopwords;

    @GetMapping("/test")
    public List<String> test(@RequestParam("str") String str) {
        return fenci(str);
    }

    private List<String> fenci(String str) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<String> collect = segmenter.sentenceProcess(str).stream()
                .filter(s -> !stopwords.getWords().contains(s))
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }
}
