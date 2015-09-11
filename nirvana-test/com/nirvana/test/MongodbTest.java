package com.nirvana.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongodbTest {

	private static DBCollection collection = null;

	public static void main(String[] args) throws UnknownHostException {
		List<ServerAddress> addresses = new ArrayList<ServerAddress>();
		ServerAddress address1 = new ServerAddress("192.168.1.241", 10001);
		ServerAddress address2 = new ServerAddress("192.168.1.242", 10001);
		ServerAddress address3 = new ServerAddress("192.168.1.243", 10001);
		addresses.add(address1);
		addresses.add(address2);
		addresses.add(address3);

		MongoClient client = new MongoClient(addresses);
		DB db = client.getDB("test");
		collection = db.getCollection("person");
		get();
	}

	public static void add() {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", 1);
		dbObject.put("date", new Date());
		dbObject.put("Integer", 3);
		dbObject.put("String", "3");
		collection.insert(dbObject, WriteConcern.SAFE);
	}

	public static void get() {
		DBObject object = collection.findOne();
		long count = collection.count();
		System.out.println(count);
	}

	public static void delete() {
		collection.drop();
	}
}
