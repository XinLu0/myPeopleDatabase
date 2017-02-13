package com.unittest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.database.DocumentController;
import com.database.MongoDocument;
import com.database.Sex;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * 
 * @author Xintong
 * This class is an example for unit Test
 */
public class TestDocumentController {
	DocumentController dc = new DocumentController("testDB","test");
	MongoDocument doc = new MongoDocument("user1",18,Sex.male);
	String mockString;
	MongoCollection<Document> mockMC;
	//test updateDocument
	@Test
	public void testUpdateDocument(){
		dc.updateDocument(doc);
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
        
        MongoDatabase mongoDatabase = mongoClient.getDatabase("testDB");
        MongoCollection collection =  mongoDatabase.getCollection("test");
        BasicDBObject fields = new BasicDBObject();
        fields.put("name", "user1");
       FindIterable<Document> fi =collection.find(fields);
       MongoCursor<Document> iter= fi.iterator();
       Document doc = iter.next();
        
		assertEquals(18,doc.get("age"));
		assertEquals("male", doc.get("sex"));
	}

	//test getDocumentss
		@Test
		public void testGetDocument(){

			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	        
	        Document document = new Document("name","user2").append("age", 17).append("sex", "female");
	        MongoDatabase mongoDatabase = mongoClient.getDatabase("testDB");
	        MongoCollection collection =  mongoDatabase.getCollection("test");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);  
	        
	        collection.insertMany(documents);
	        
			assertEquals(documents,dc.getDocument("user2"));
			
		}
		
		// test removeDocument using mockito
		
		@Before  
	    public void setUp() {  
			mockString = mock(String.class); 
			mockMC = mock(MongoCollection.class);
			dc.setMongoCollection(mockMC);
	        when(mockString.trim()).thenReturn("user3");
	        
	        
	    }  
		@Test
		public void testRemoveDocument()
		{
			dc.removeDocument(mockString);
			verify(mockMC,times(1)).deleteOne(Filters.eq("name", "user3"));
			
			
		}
		// test getAllDocument
		//...
}
