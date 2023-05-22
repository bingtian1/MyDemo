package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class WordsConfig {

    @Bean
    public Words getWords() throws Exception {
        Path path = Paths.get("src/main/resources/StopWordsChinese.txt");
        List<String> list = Files.readAllLines(path);
        return new Words(list);
    }

    @Data
    @AllArgsConstructor
    public static class Words {
        private List<String> words;
    }
}
