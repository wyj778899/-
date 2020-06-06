package test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

public class MongoTest {

    @Test
    //查询所有文档
    public void fun1() {
        //1 创建连接
        MongoClient client = new MongoClient("192.168.40.128");
        //2 指定要操作的数据库
        MongoDatabase db = client.getDatabase("spitdb");
        //3 指定要操作的集合
        MongoCollection<Document> collection = db.getCollection("spit");
        //4 查询所有文档
        FindIterable<Document> documents = collection.find();

        for(Document doc:documents){
        //5 遍历打印
            System.out.println(doc.get("_id"));
            System.out.println(doc.get("nickname"));
            System.out.println(doc.get("visits"));
            System.out.println("--------------------------------------------------------------");
        }


    }

    @Test
    //条件查询
    public void fun2() {
        //1 创建连接
        MongoClient client = new MongoClient("192.168.40.128");
        //2 指定要操作的数据库
        MongoDatabase db = client.getDatabase("spitdb");
        //3 指定要操作的集合
        MongoCollection<Document> collection = db.getCollection("spit");
        //4 查询所有文档
        BasicDBObject basicDBObject = BasicDBObject.parse("{\"visits\":{$gt:1000}}");
        //条件查询
        FindIterable<Document> documents = collection.find(basicDBObject);

        for(Document doc:documents){
            //5 遍历打印
            System.out.println(doc.get("_id"));
            System.out.println(doc.get("nickname"));
            System.out.println(doc.get("visits"));
            System.out.println("--------------------------------------------------------------");
        }
    }

    @Test
    //修改
    public void fun3() {
        //1 创建连接
        MongoClient client = new MongoClient("192.168.40.128");
        //2 指定要操作的数据库
        MongoDatabase db = client.getDatabase("spitdb");
        //3 指定要操作的集合
        MongoCollection<Document> collection = db.getCollection("spit");
        //4 查询所有文档
        BasicDBObject b1 = BasicDBObject.parse("{\"_id\":\"1\"}");
        BasicDBObject b2 = BasicDBObject.parse("{$inc:{\"visits\":1}}");

        collection.updateOne(b1, b2);
    }

    @Test
    //删除
    public void fun4() {
        //1 创建连接
        MongoClient client = new MongoClient("192.168.40.128");
        //2 指定要操作的数据库
        MongoDatabase db = client.getDatabase("spitdb");
        //3 指定要操作的集合
        MongoCollection<Document> collection = db.getCollection("spit");
        //4 查询所有文档
        BasicDBObject b1 = BasicDBObject.parse("{\"_id\":\"1\"}");
        //删除指定条件文档
        collection.deleteOne(b1);
    }

    @Test
    //添加文档
    public void fun5() {
        //1 创建连接
        MongoClient client = new MongoClient("192.168.40.128");
        //2 指定要操作的数据库
        MongoDatabase db = client.getDatabase("spitdb");
        //3 指定要操作的集合
        MongoCollection<Document> collection = db.getCollection("spit");
        //4 查询所有文档
        BasicDBObject b1 = BasicDBObject.parse("{\"_id\":\"1\"}");

        Document document = new Document();

        document.put("_id", "1");
        document.put("content", "呵呵");
        document.put("visits", 2020);
        document.put("nickname", "李磊");


        //删除指定条件文档
        collection.insertOne(document);
    }
}
