package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WordsConfig {

    @Bean
    public Words getWords() throws Exception {
        List<String> list = new ArrayList<>();
        String filePath = "src/main/resources/StopWordsChinese.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while ((strTmp = buffReader.readLine()) != null) {
            list.add(strTmp);
        }
        buffReader.close();
        return new Words(list);
    }

    @Data
    @AllArgsConstructor
    public static class Words {
        private List<String> words;
    }
}
