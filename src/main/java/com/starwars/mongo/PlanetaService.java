package com.starwars.mongo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starwars.model.Planeta;
import com.starwars.mongo.factory.MongoFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
@Service("planetaService")
@Transactional
public class PlanetaService {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("conf");
	
	String db_name = resource.getString("db_name");
	
	String db_collection = resource.getString("db_collection");
	
	private static Logger log = Logger.getLogger(PlanetaService.class);

	// Fetch all planets from the mongo database.
	
	public List<Planeta> getAll() {
		
		List<Planeta> planet_list = new ArrayList<Planeta>();
		
		MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);
		
		// Fetching cursor object for iterating on the database records.	
		
		FindIterable<Document> docs  = coll.find();
		
		for (Document doc : docs) {
			
			try {
	
				Planeta planeta = new Planeta();
				
				planeta.setId(doc.get("_id").toString());
				
				planeta.setClima(doc.get("clima").toString());
				
				planeta.setNome(doc.get("nome").toString());
				
				planeta.setTerreno(doc.get("terreno").toString());
	
				
				if (!doc.get("filmes").toString().equals("")){
					
					planeta.setFilmes(doc.get("filmes").toString());
					
				}
				// Adding the planeta details to the list.
				planet_list.add(planeta);
				
			} catch(NullPointerException e) {
			
			}
			
			
		}
		
		log.debug("Total records fetched from the mongo database are= " + planet_list.size());
		
		return planet_list;
	}

	// Add a new planet to the mongo database.
	
	public Boolean add(Planeta planeta) {
		
		boolean output = false;
		
		log.debug("Adding a new planeta to the mongo database; Entered planet_name is= " + planeta.getNome());
		
		try {			
			
			MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new planet details to this object.
			Document doc = new Document();
			
			doc.put("nome", planeta.getNome());
			
			doc.put("clima", planeta.getClima());
			
			doc.put("terreno", planeta.getTerreno());
			
			doc.put("filmes", getFilmeReference(planeta));

			// Save a new planet to the mongo collection.
			coll.insertOne(doc);
			
			output = true;
			
		} catch (Exception e) {
			
			output = false;
			
			log.error("An error occurred while saving a new planet to the mongo database", e);			
		}
		return output;
	}

	// Update the selected planet in the mongo database.
	public Boolean edit(Planeta planeta) {
		
		boolean output = false;
		
		log.debug("Updating the existing planet in the mongo database; Entered planet_id is= " + planeta.getId());
		
		try {
			
			// Fetching the planet details.
			Document existing = getDocumentObj(planeta.getId());
			
			MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);
			
			Document edited = null;
		
			// Create a new object and assign the updated details.
			edited = new Document(); 
			
			edited.put("nome", planeta.getNome());
				
			edited.put("terreno", planeta.getTerreno());
				
			edited.put("clima", planeta.getClima());
				
			edited.put("filmes", getFilmeReference(planeta));
		
			// Update the existing planet to the mongo database.
	
			coll.replaceOne(existing, edited, 
				    new UpdateOptions().upsert(true));
			
			output = true;
			
		} catch (Exception e) {
			
			output = false;
			
			log.error("An error has occurred while updating an existing planet to the mongo database", e);
			
		}
		return output;
	}

	// Delete a planet from the mongo database.
	public Boolean delete(String id) {
		
		boolean output = false;
		
		log.debug("Deleting an existing planet from the mongo database; Entered planeta_id is= " + id);
		
		try {
			// Fetching the required planet from the mongo database.
			Document item = getDocumentObj(id);

			MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected planet from the mongo database.
			coll.deleteOne(item);
			
			output = true;		
			
		} catch (Exception e) {
			
			output = false;
			
			log.error("An error occurred while deleting an existing planet from the mongo database", e);			
		}	
		return output;
	}

	// Fetching a particular record from the mongo database.
	private Document getDocumentObj(String id) {
		
		MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		
		Document myDoc = null;
		
		try { 

			myDoc = (Document) coll.find(eq("_id", new ObjectId(id))).first();
			
		} catch(IllegalArgumentException e) {}
		
		return myDoc;
	}

	// Fetching a single planet details from the mongo database.
	public List<Planeta> findPlanetaId(String id) {
		
		Planeta planeta = new Planeta();
		
		MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		
		List<Planeta> data = new ArrayList<Planeta>();
		
		try {
			
			Bson bsonFilter = Filters.eq("_id", new ObjectId(id));
			
			FindIterable<Document> docs = coll.find(bsonFilter);
					
			for (Document doc : docs) {		
					
					planeta.setId(doc.get("_id").toString());
				
					planeta.setNome(doc.get("nome").toString());
	
					planeta.setTerreno(doc.get("terreno").toString());
				
					planeta.setClima(doc.get("clima").toString());
				
					planeta.setFilmes(doc.get("filmes").toString());
				
				data.add(planeta);
			}
			
		} catch(IllegalArgumentException e) {}
		
		return data;
	
	}
	
	// Fetching a single planet details from the mongo database.
	public List<Planeta> findPlanetaName(String nome) {
		
		Planeta planeta = new Planeta();
		
		MongoCollection<Document> coll = MongoFactory.getCollection(db_name, db_collection);

		List<Planeta> data = new ArrayList<Planeta>();
		
		// Fetching the record object from the mongo database.
		
		Bson bsonFilter = Filters.eq("nome", nome);
		
		FindIterable<Document> docs = coll.find(bsonFilter);
		
		for (Document doc : docs) {		
		  
			try {
				
				planeta.setId(doc.get("_id").toString());
				
				planeta.setNome(doc.get("nome").toString());
		
				planeta.setTerreno(doc.get("terreno").toString());
				
				planeta.setClima(doc.get("clima").toString());
				
				planeta.setFilmes(doc.get("filmes").toString());
				
				data.add(planeta);
			}
			catch(NullPointerException e) {}
		}
		
		// Return planet object.
		return data;
	}
	
public String getFilmeReference(Planeta planeta) {
		
		//String sURL = "https://swapi.co/api/planets/9/?format=json"; 
		
		String sURL = "https://swapi.co/api/planets/" + planeta.getNome() + "/?format=json"; //just a string
		
		
	    // Connect to the URL using java's native library
		
	    URL url = null;
	    
		try {
			
			url = new URL(sURL);
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
	    URLConnection request = null;
	    
		try {
			
			request = url.openConnection();
			
			request.addRequestProperty("User-Agent", 
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    try {
	    	
			request.connect();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	    // Convert to a JSON object to print data
	    JsonParser jp = new JsonParser(); //from gson
	    
	    JsonElement root = null;
	    
		try {
			
			root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			
		} catch (JsonIOException e) {
			
			//e.printStackTrace();
			
		} catch (JsonSyntaxException e) {
			
			//e.printStackTrace();
			
		} catch (IOException e) {
			
			//e.printStackTrace();
			
		} //Convert the input stream to a json element
		
		int count = 0;
		
		try {
			
			JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
	  
			JsonArray filmes = rootobj.get("films").getAsJsonArray();
	    
			for(JsonElement i : filmes)
			{
	            count++;
			}
			
		} catch(Exception e) {
			
		}
		
		return String.valueOf(count);
	}	
}