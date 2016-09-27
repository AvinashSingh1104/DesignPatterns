import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EvaluationClass5 {
	public static List<Socket> lstOfSockets;
	public static List<ObjectOutputStream> lstOfObjectOutputStream;
	public static List<ObjectInputStream> lstOfObjectInputStream;
	public static int  numberOfServerInSystems;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties objProperty = new Properties();
		objProperty.load(new FileInputStream("config.properties"));
		numberOfServerInSystems = Integer.parseInt((objProperty.getProperty(("numberOfServersInSystem"))));
		NodeClass5 objNodeClass = new NodeClass5();
		
		lstOfSockets = new ArrayList<Socket>();
		lstOfObjectOutputStream = new ArrayList<ObjectOutputStream>();
		lstOfObjectInputStream = new ArrayList<ObjectInputStream>();
		createServerSocketList(lstOfSockets,objProperty);
		createObjectInputStreamList(lstOfSockets,lstOfObjectInputStream);
		createObjectOutputStreamList(lstOfSockets,lstOfObjectOutputStream);
		
		//Test Put Method 
		testPutKeyValueFuntion(objNodeClass,objProperty);
		
		//Test get Method
		testGetValue(objNodeClass,objProperty);
		
		//Test delete Method
		testdeleteValue(objNodeClass,objProperty);
	}

	public static  void testPutKeyValueFuntion(NodeClass5 objNodeClass,Properties objProperty) throws NumberFormatException, UnknownHostException, IOException{
		
		long starttime = System.currentTimeMillis();
		
		for(int i=0;i<100000;i++){
			String key = "e"+i;
			
			ObjectInputStream objInStmm =getInPutStream(lstOfObjectInputStream,key);
			ObjectOutputStream objOutStmm =getOutPutStream(lstOfObjectOutputStream,key);
			String newKey= String.format("%1$"+23+"s", key);
			//System.out.println(newKey);
			objNodeClass.sendReceiveMessageToServer(objOutStmm, objInStmm, "1"+newKey+"qwamjivbdnsubhgfxfxhgfxhgfxxgfdzgdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwqwamjivbdnsubhgfxfxhgfxhgfxxgfdzgdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwqwamjivbdnsubhgfxfxhgfxhgfxxgfdzgdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwqwamjivbdnsubhgfxfxhgfxhgfxxgfdzgdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		}
		System.out.println("Total Time in milliseconds (Put):"+(System.currentTimeMillis()-starttime));
	}
	
	public static  void testGetValue(NodeClass5 objNodeClass,Properties objProperty) throws NumberFormatException, UnknownHostException, IOException{
			
			long starttime = System.currentTimeMillis();
			
			for(int i=0;i<100000;i++){
				String key = "e"+i;
				
				ObjectInputStream objInStmm =getInPutStream(lstOfObjectInputStream,key);
				ObjectOutputStream objOutStmm =getOutPutStream(lstOfObjectOutputStream,key);
				String newKey= String.format("%1$"+23+"s", key);
				objNodeClass.sendReceiveMessageToServer(objOutStmm, objInStmm, "2"+newKey);
			}
			System.out.println("Total Time in milliseconds (get):"+(System.currentTimeMillis()-starttime));
		}
	
	public static  void testdeleteValue(NodeClass5 objNodeClass,Properties objProperty) throws NumberFormatException, UnknownHostException, IOException{
		
		long starttime = System.currentTimeMillis();
		
		for(int i=0;i<100000;i++){
			String key = "e"+i;
			
			ObjectInputStream objInStmm =getInPutStream(lstOfObjectInputStream,key);
			ObjectOutputStream objOutStmm =getOutPutStream(lstOfObjectOutputStream,key);
			String newKey= String.format("%1$"+23+"s", key);
			objNodeClass.sendReceiveMessageToServer(objOutStmm, objInStmm, "2"+newKey);
		}
		System.out.println("Total Time in milliseconds (delete):"+(System.currentTimeMillis()-starttime));
	}
		
		
		
	
	
	
	
	
	
	public static void createServerSocketList(List<Socket> lstOfServerInfo,Properties objProperty){
		for(int i=1;i<=numberOfServerInSystems;i++){
			try {
				Socket clientsocket = new Socket((objProperty.getProperty(("serverIP"+i))), Integer.parseInt((objProperty.getProperty(("serverPort"+i)))));
				lstOfServerInfo.add(clientsocket);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void createObjectInputStreamList(List<Socket> lstOfServerInfo,List<ObjectInputStream> lstOfObjectInputStream) throws IOException{
		for(Socket objSockt : lstOfServerInfo){
			ObjectInputStream objInStm = new ObjectInputStream(objSockt.getInputStream());
			lstOfObjectInputStream.add(objInStm);
		}
	}
	
	public static void createObjectOutputStreamList(List<Socket> lstOfServerInfo,List<ObjectOutputStream> lstOfObjectOutputStream) throws IOException{
		for(Socket objSockt : lstOfServerInfo){
			ObjectOutputStream objOutStm = new ObjectOutputStream(objSockt.getOutputStream());
			lstOfObjectOutputStream.add(objOutStm);
		}
	}
	
	public static ObjectOutputStream getOutPutStream(List<ObjectOutputStream> lstOfObjectOutPutStream,String key){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		
		return lstOfObjectOutPutStream.get(arrayr);
	}
	
	public static ObjectInputStream getInPutStream(List<ObjectInputStream> lstOfObjectInputStream,String key){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		
		//System.out.println(arrayr+", "+key+" , "+asciiValue(key));
		return lstOfObjectInputStream.get(arrayr);
	}
	
	public static int asciiValue(String input){
		int value=0;
		for(int i=0;i<input.length();i++){
			value = value+(int)input.charAt(i);
		}
		return value;
	}

}
