import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class MongoEvaluation {
	

	
	static int inputSize;
	static List<String> keylist = new ArrayList<String>();
	static List<String> valuelist = new ArrayList<String>();
	
	static List<DBCollection> lstDBCollection = new ArrayList<DBCollection>();
	static List<DBCursor> lstDBCursor = new ArrayList<DBCursor>();
	
	public static int  numberOfInstance;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties objProperty = new Properties();
		objProperty.load(new FileInputStream("config.properties"));
		numberOfInstance = Integer.parseInt((objProperty.getProperty(("numberOfInstance")))); 
		inputSize = Integer.parseInt(objProperty.getProperty("keyValueSize"));
		prepareKeyValue(inputSize,keylist,valuelist);
		
		prepareDBcollectionDbcursorList(objProperty);
		
		evaluateMongoDbInsert(keylist,valuelist);
		
		evaluateMongoDbSearch(keylist);
		
		evaluateMongoDbDelete(keylist);
			
	}
	
	public static void evaluateMongoDbInsert(List<String> keylist,List<String> valuelist){
		long starttime = System.currentTimeMillis();
		int i=0;
		for(String key : keylist){
		int arrayr= asciiValue(key)%numberOfInstance;
		BasicDBObject dbObj = new BasicDBObject(key, valuelist.get(i));
		lstDBCollection.get(arrayr).insert(dbObj);
		i++;
		}
		System.out.println("Total Time in milliseconds Insert:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void evaluateMongoDbSearch(List<String> keylist){
		long starttime = System.currentTimeMillis();
		for(String key : keylist){
			int arrayr= asciiValue(key)%numberOfInstance;
			BasicDBObject dbObjFetch = (BasicDBObject) lstDBCursor.get(arrayr).next();
			dbObjFetch.get(key);
		}
		System.out.println("Total Time in milliseconds GET:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void evaluateMongoDbDelete(List<String> keylist){
		long starttime = System.currentTimeMillis();
		for(String key : keylist){
			int arrayr= asciiValue(key)%numberOfInstance;
			BasicDBObject dbObjFetch = new BasicDBObject();
			dbObjFetch.remove(key);
			lstDBCollection.get(arrayr).save(dbObjFetch);
		}
		System.out.println("Total Time in milliseconds Delete:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void prepareKeyValue(int keyValueSize,List<String> keylist,List<String> valuelist){
		for(int i=0;i<keyValueSize;i++){
			keylist.add("Aviiiiii"+i);
			valuelist.add("Aviiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+i);
		}
	}
	
	public static int asciiValue(String input){
		int value=0;
		for(int i=0;i<input.length();i++){
			value = value+(int)input.charAt(i);
		}
		return value;
	}
	
	public static void prepareDBcollectionDbcursorList(Properties objProperty){
		for(int i=1;i<=numberOfInstance;i++){
			String host = objProperty.getProperty(("host"+i));
			MongoClient mongoClient = new MongoClient();
			DB db = mongoClient.getDB("mongoDB");
			DBCollection objDbcollection= db.getCollection("KeyValue_1");
			DBCursor objBbcursor= objDbcollection.find();
			lstDBCollection.add(objDbcollection);
			lstDBCursor.add(objBbcursor);
		}
	}

}
