package ru.inno.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MongoTest {

    private final static String uri = "mongodb://localhost:27017/";
    private MongoClient client;

    @BeforeEach
    public void setUp() {
        client = MongoClients.create(uri);
    }

    @AfterEach
    public void tearDown() {
        client.close();
    }

    @Test
    public void iCanConnect() {
        MongoDatabase cats = client.getDatabase("cats");
        assertNotNull(cats);
    }

    @Test
    public void iCanInsertOne() {
        MongoDatabase cats = client.getDatabase("cats");

        Document doc = new Document()
                .append("name", "Пиксель")
                .append("age", 2.5)
                .append("hobbies", List.of("Гонять крышечку от бутылки", "Шуршать по ночам")
                );
        InsertOneResult newCat = cats.getCollection("cats_info").insertOne(doc);
    }

    @Test
    public void iCanQuery(){
        MongoDatabase cats = client.getDatabase("cats");

        FindIterable<Document> cats_info = cats.getCollection("cats_info").find();

        cats_info.forEach(d -> {
            String catName = d.get("name", String.class);
            Integer catAge = d.getInteger("age");
            System.out.println(catName + " " + catAge);
        });
    }
}
