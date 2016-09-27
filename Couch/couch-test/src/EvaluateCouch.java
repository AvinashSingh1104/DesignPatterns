import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;


public class EvaluateCouch {

	static int inputSize;
	public static int  numberOfInstance;
	static List<String> keylist = new ArrayList<String>();
	static List<String> valuelist = new ArrayList<String>();
	static List<String> host= new ArrayList<String>();
	static Session objdbSession[];
	static Database objdb[];
	
	public static Properties objProperty = new Properties();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		objProperty.load(new FileInputStream("config.properties"));
		numberOfInstance = Integer.parseInt((objProperty.getProperty(("numberOfInstance")))); 
		inputSize = Integer.parseInt(objProperty.getProperty("keyValueSize"));
		prepareKeyValue(inputSize,keylist,valuelist);

		prepareMetaDataForCouch();
		
		evaluateCouchInsert(keylist,valuelist);
		
		evaluateCouchDbSearch(keylist);
		
		evaluateCouchDbDelete(keylist);
	}
	
	public static void evaluateCouchInsert(List<String> keylist,List<String> valuelist){
		long starttime = System.currentTimeMillis();
		int i=0;
		for(String key : keylist){
		int arrayr= asciiValue(key)%numberOfInstance;
		Document objdoc = new Document();
		objdoc.setId(key);
		objdoc.put("value", valuelist.get(i));
		objdb[arrayr].saveDocument(objdoc);
		i++;
		}
		System.out.println("Total Time in milliseconds Insert:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void evaluateCouchDbSearch(List<String> keylist){
		long starttime = System.currentTimeMillis();
		for(String key : keylist){
			int arrayr= asciiValue(key)%numberOfInstance;
			Document d = objdb[arrayr].getDocument(key);
			String value= (String)d.get("value");
		}
		System.out.println("Total Time in milliseconds GET:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void evaluateCouchDbDelete(List<String> keylist){
		long starttime = System.currentTimeMillis();
		for(String key : keylist){
			int arrayr= asciiValue(key)%numberOfInstance;
			Document docc = objdb[arrayr].getDocument(key);
			objdb[arrayr].deleteDocument(docc);
		}
		System.out.println("Total Time in milliseconds Delete:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void prepareKeyValue(int keyValueSize,List<String> keylist,List<String> valuelist){
		for(int i=0;i<keyValueSize;i++){
			keylist.add("Aviiiiii"+i);
			valuelist.add("Aviiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+i);
		}
	}
	
	public static void prepareMetaDataForCouch(){
		
		for (int i = 1; i <= numberOfInstance; i++) {
			host.add(objProperty.getProperty(("host"+i)));
		}
		
		objdbSession=new Session[numberOfInstance];
		objdb=new Database[numberOfInstance];
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																												
		for (int i = 0; i <numberOfInstance; i++) {
		String stringg=host.get(i);
		
		objdbSession[i] = new Session(stringg,27017);
		objdbSession[i].createDatabase("couchtable");
		
		objdb[i]=objdbSession[i].getDatabase("couchtable");
		}
	}
	
	public static int asciiValue(String input){
		int value=0;
		for(int i=0;i<input.length();i++){
			value = value+(int)input.charAt(i);
		}
		return value;
	}

}
