package com.starwars.mongo.factory;

import org.apache.log4j.Logger;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ResourceBundle;

public class MongoFactory {
	
	private static Logger log = Logger.getLogger(MongoFactory.class);

	private static ResourceBundle resource = ResourceBundle.getBundle("conf");
	
	private MongoFactory() { }

	// Returns a mongo instance.
	public static MongoClient getMongo() {
		
		String hostname = resource.getString("hostname");

		int port_no = Integer.parseInt(resource.getString("port_no"));
		
		MongoClient mongo = null;
		
		if (mongo == null) {
			
			try {
				
				mongo = new MongoClient(hostname, port_no);
				
			} catch (MongoException ex) {
				
				log.error(ex);
			}
		}
		return mongo;
	}

	// Fetches the mongo database.
	public static MongoDatabase getDB(String db_name) {		
		
		return getMongo().getDatabase(db_name);
	}
    
	// Fetches the collection from the mongo database.
	public static MongoCollection<Document> getCollection(String db_name, String db_collection) {
		
		return getDB(db_name).getCollection(db_collection);
	}
}