package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.utils.JacksonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mongodb")
public class MongoDBController {

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("/test")
    public void test() {
        MongoDatabase db = mongoTemplate.getDb();
        System.out.println("[dbname]:" + db.getName());
        MongoCollection<Document> collection = mongoTemplate.createCollection(User.class);
        System.out.println("[collection]:" + collection.getNamespace());
        mongoTemplate.save(User.builder().name("张三").age(25).build());
        mongoTemplate.insert(getUsers(), User.class);
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println("[findall]:" + JacksonUtil.writeValueAsString(all));
        Query query = Query.query(Criteria.where("name").is("张三"));
        List<User> userList = mongoTemplate.find(query, User.class);
        System.out.println("[findbyexample]:" + JacksonUtil.writeValueAsString(userList));
        Update update = Update.update("age", 15);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        System.out.println("[update]:" + updateResult.getModifiedCount());
        DeleteResult remove = mongoTemplate.remove(query, User.class);
        System.out.println("[remove]:" + remove.getDeletedCount());
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(User.builder().name("a" + i).age(i).build());
        }
        return userList;
    }
}
