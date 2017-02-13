package com.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
/**
 * 
 * @author Xintong Lu
 * This class is used as an interface between web and mongoDatabase
 */
public class DocumentController {
	String collectionName;
	String databaseName;
	MongoCollection<Document> collection;
	//constructor
	public DocumentController(String dName, String cName){
		databaseName = dName;
		collectionName = cName;

		 MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
	        
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        collection =  mongoDatabase.getCollection(collectionName);
        
	}
	/**
	 * get document which has name==n
	 * @param n
	 * @return the document information
	 */
	public String getDocument(String n){
		BasicDBObject fields = new BasicDBObject();
        fields.put("name", n.trim());
        FindIterable<Document> mongoCursor =  collection.find( fields);
        MongoCursor<Document> iterator = mongoCursor.iterator();  
        if(!iterator.hasNext())
        {
       	 return "no document is founded";
        }
        else{
        	ArrayList<String> result =new ArrayList<String>();
        	while(iterator.hasNext()){  
          	   StringBuffer tmp = new StringBuffer();
          	   Document tmpD = iterator.next();
          	   tmp.append("Name: "+ tmpD.get("name")+" Age: "+tmpD.get("age")+" Sex: "+tmpD.get("sex")+"\n");
               result.add(tmp.toString()); 
             }
        	StringBuffer sb = new StringBuffer();
        	for(String st: result)
        	{
        		sb.append(st);
        	}
        	return sb.toString();
        }
        
	}
	/**
	 * update Document 
	 * if the user is existing, the user information will be updated
	 * if the user is not existing, the user information will be created and stored
	 * @param md
	 */
	public void updateDocument(MongoDocument md)
	{

         BasicDBObject fields = new BasicDBObject();
         fields.put("name", md.getName());
         FindIterable<Document> mongoCursor =  collection.find( fields);
         MongoCursor<Document> iterator = mongoCursor.iterator();  
         if(!iterator.hasNext())
         {


        	 Document doc = new Document("name",md.getName()).append("age", md.getAge()).append("sex", md.getSex()==null?"null":md.getSex().toString());
        	 List<Document> documents = new ArrayList<Document>();  
             documents.add(doc);  
             collection.insertMany(documents);
         }
         else{
        	 collection.updateMany(Filters.eq("name", md.getName()), new Document("$set",new Document("age",md.getAge()).append("sex", md.getSex().toString()))); 
         }
         
	}
	/**
	 * Get all document of current collection
	 * @return a arrayList of users' information
	 */
	public ArrayList<String> getAllDocument()
	{
		ArrayList<String> stringList = new ArrayList<String>();
		
		BasicDBObject fields = new BasicDBObject();
		 FindIterable<Document> mongoCursor =  collection.find( fields);
		 MongoCursor<Document> iterator = mongoCursor.iterator(); 
         while(iterator.hasNext())
         {
        	 Document tmpD= iterator.next();
        	 stringList.add("Name: "+ tmpD.get("name")+" Age: "+tmpD.get("age")+" Sex: "+tmpD.get("sex")+"\r\n" );
         }
         return stringList;
	}
	/**
	 * removedName's information will be deleted.
	 * @param removedName
	 */
	public void removeDocument(String removedName){
		BasicDBObject fields = new BasicDBObject();
		fields.put("name", removedName.trim());
		collection.deleteOne(Filters.eq("name", removedName));
	}
	public void setMongoCollection(MongoCollection<Document> c)
	{
		collection = c;
	}
}
