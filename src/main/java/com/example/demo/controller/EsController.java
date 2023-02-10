package com.example.demo.controller;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/es")
public class EsController {

    @Resource
    private RestHighLevelClient client;

    @Resource
    private HttpServletRequest request;

    @GetMapping("/get")
    public void test() throws IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        System.out.println("================");
        GetRequest getRequest = new GetRequest("test","testdoc1");
        GetResponse resp = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(resp);
    }

}
