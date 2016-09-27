import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import redis.clients.jedis.Jedis;



public class EvaluateRedis {

	static int inputSize;
	public static int  numberOfInstance;
	static List<Jedis> lstredishClient = new ArrayList<Jedis>();
	static List<String> keylist = new ArrayList<String>();
	static List<String> valuelist = new ArrayList<String>();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties objProperty = new Properties();
		objProperty.load(new FileInputStream("config.properties"));
		numberOfInstance = Integer.parseInt((objProperty.getProperty(("numberOfInstance")))); 
		inputSize = Integer.parseInt(objProperty.getProperty("keyValueSize"));
		
		prepareKeyValue(inputSize,keylist,valuelist);
		
		prepareRedishClientList(objProperty);
		
		evaluateRedisInsert(keylist,valuelist);
		
		evaluateMongoDbSearch(keylist);
		
		evaluateMongoDbDelete(keylist);

	}
	
	public static void evaluateRedisInsert(List<String> keylist,List<String> valuelist){
		long starttime = System.currentTimeMillis();
		int i=0;
		for(String key : keylist){
		int arrayr= asciiValue(key)%numberOfInstance;
		lstredishClient.get(arrayr).set(key, valuelist.get(i));
		i++;
		}
		System.out.println("Total Time in milliseconds Insert:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void evaluateMongoDbSearch(List<String> keylist){
		long starttime = System.currentTimeMillis();
		for(String key : keylist){
			int arrayr= asciiValue(key)%numberOfInstance;
			lstredishClient.get(arrayr).get(key);
		}
		System.out.println("Total Time in milliseconds GET:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void evaluateMongoDbDelete(List<String> keylist){
		long starttime = System.currentTimeMillis();
		for(String key : keylist){
			int arrayr= asciiValue(key)%numberOfInstance;
			lstredishClient.get(arrayr).del(key);
		}
		System.out.println("Total Time in milliseconds Delete:"+(starttime -System.currentTimeMillis()));
	}
	
	public static void prepareRedishClientList(Properties objProperty){
		for(int i=1;i<=numberOfInstance;i++){
			String host = objProperty.getProperty(("host"+i));
			Jedis objJedis =new Jedis(host);
			lstredishClient.add(objJedis);
		}
	}
	
	public static int asciiValue(String input){
		int value=0;
		for(int i=0;i<input.length();i++){
			value = value+(int)input.charAt(i);
		}
		return value;
	}
	
	public static void prepareKeyValue(int keyValueSize,List<String> keylist,List<String> valuelist){
		for(int i=0;i<keyValueSize;i++){
			keylist.add("Aviiiiii"+i);
			valuelist.add("Aviiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+i);
		}
	}

}

