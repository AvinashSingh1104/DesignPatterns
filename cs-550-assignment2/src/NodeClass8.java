import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class NodeClass8 {

	public static BufferedReader reader;
	
	public static List<Socket> lstOfSockets;
	public static List<ObjectOutputStream> lstOfObjectOutputStream;
	public static List<ObjectInputStream> lstOfObjectInputStream;
	
	public static int  numberOfServerInSystems;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		NodeClass8 objNodeClass = new NodeClass8();
		Properties objProperty = new Properties();
		objProperty.load(new FileInputStream("config.properties"));
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		lstOfSockets = new ArrayList<Socket>();
		lstOfObjectOutputStream = new ArrayList<ObjectOutputStream>();
		lstOfObjectInputStream = new ArrayList<ObjectInputStream>();
		
		numberOfServerInSystems = Integer.parseInt((objProperty.getProperty(("numberOfServersInSystem")))); 
		boolean flag = true;
		
		
		
		try{
			Thread objThread = new Thread(new NodeAsServer(Integer.parseInt(objProperty.getProperty("serverPort8"))));
			objThread.start();
			
			
			do{
				System.out.println("***********************************************************");
				System.out.println("Enter the choice(1,2,3,4,5) to Perform Operations : ");
				System.out.println("1 - Put a key/value.");
				System.out.println("2 - Get a value.");
				System.out.println("3 - Delete key/value.");
				System.out.println("4 - Size of HashTable on all Servers in the System.");
				System.out.println("5 - Exit.");
				System.out.println("***********************************************************");
				
				
				System.out.print("Enter your choice : ");
				String input = reader.readLine();
				if(flag){
				createServerSocketList(lstOfSockets,objProperty);
				createObjectInputStreamList(lstOfSockets,lstOfObjectInputStream);
				createObjectOutputStreamList(lstOfSockets,lstOfObjectOutputStream);
				
				flag=false;
				}
				switch (input) {

				case "1":
					objNodeClass.setKeyValueToTable();
					break;

				case "2":
					objNodeClass.getValueFromTable();
					break;

				case "3":
					objNodeClass.deleteValueFromTable();
					break;
				case "4":
					objNodeClass.getSizeOfHashTable();
					break;
				case "5":
					System.exit(0);
					break;

				default:
					System.out.println("Invalid Input.");
					break;
				}

			}
			while(true);
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void setKeyValueToTable() throws IOException{
		
		String key = getKeyFromUser();
		
		String value= getValueFromUser();

		ObjectInputStream objInStm =getInPutStream(lstOfObjectInputStream,key);
		ObjectOutputStream objOutStm = getOutPutStream(lstOfObjectOutputStream,key);
		String newKey= String.format("%1$"+23+"s", key);
		String message = "1"+newKey+value;
		
		Object objResponse = sendReceiveMessageToServer(objOutStm, objInStm, message);
		System.out.println();
		System.out.println("Key/Value inserted : "+objResponse.toString());
		System.out.println();
	}
	
	public void getValueFromTable() throws IOException{
		
		String key = getKeyFromUser();
		
		ObjectInputStream objInStm =getInPutStream(lstOfObjectInputStream,key);
		ObjectOutputStream objOutStm = getOutPutStream(lstOfObjectOutputStream,key);
		String newKey= String.format("%1$"+23+"s", key);
		String message = "2"+newKey;
		
		Object objResponse = sendReceiveMessageToServer(objOutStm, objInStm, message);
		System.out.println();
		System.out.println("Value for the key "+key+" is :"+objResponse.toString());
		System.out.println();
	}
	

	public void deleteValueFromTable() throws IOException{
		
		String key = getKeyFromUser();

		ObjectInputStream objInStm =getInPutStream(lstOfObjectInputStream,key);
		ObjectOutputStream objOutStm = getOutPutStream(lstOfObjectOutputStream,key);
		String newKey= String.format("%1$"+23+"s", key);
		String message = "3"+newKey;
		
		Object objResponse = sendReceiveMessageToServer(objOutStm, objInStm, message);
		System.out.println();
		System.out.println("Value Delete : "+objResponse.toString());
		System.out.println();
	}
	
	public void getSizeOfHashTable() throws IOException{
			
		for(int k=0;k<numberOfServerInSystems;k++)
		{
		ObjectInputStream objInStm =lstOfObjectInputStream.get(k);
		ObjectOutputStream objOutStm =lstOfObjectOutputStream.get(k);
		
		String message = "4";
		
		Object objResponse = sendReceiveMessageToServer(objOutStm, objInStm, message);
		System.out.println("Size of HashTable at Server "+(k+1)+" : "+objResponse.toString());
		}
		System.out.println();
	}
	
	
	public Socket hashFunctionForFindingServer(String key,List<Socket> lstOfSockets){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		
		return lstOfSockets.get(arrayr);
	}
	
	public ObjectOutputStream getOutPutStream(List<ObjectOutputStream> lstOfObjectOutPutStream,String key){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		
		return lstOfObjectOutPutStream.get(arrayr);
	}
	
	public ObjectInputStream getInPutStream(List<ObjectInputStream> lstOfObjectInputStream,String key){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		
		return lstOfObjectInputStream.get(arrayr);
	}
	
	public String getKeyFromUser() throws IOException{
		boolean flag;
		String key;

		do {
			System.out.print("Enter Key :");
			key = reader.readLine();
			flag = validatekey(key);
			if(!flag){
				System.out.println("*****In-Valide Key, Re-Enter Key*****");	
			}
		} while (!flag);
		
		return key;
	}
	
	public String getValueFromUser() throws IOException{
		boolean flag1;
		String value;

		do {
			System.out.print("Enter Value :");
		    value = reader.readLine();
			flag1 = validateValue(value);
			if(!flag1){
				System.out.println("*****In-Valide Value, Re-Enter Value*****");	
			}
		} while (!flag1);
		
		return value;
	}
	
	public boolean validatekey(String key){
		int size;
		boolean flag = false;
		try {
			size = key.getBytes("UTF-8").length;
			if(size<=23)
			{
				flag = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean validateValue(String value){
		int size;
		boolean flag = false;
		try {
			size = value.getBytes("UTF-8").length;
			if(size<=1000)
			{
				flag = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return flag;
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

	public Object sendReceiveMessageToServer(ObjectOutputStream objOutStm,ObjectInputStream objInStm,String message){
		Object objObject=null;
		try {
			objOutStm.writeObject(message);
			objOutStm.flush();
			objObject = objInStm.readObject();
			
		}  catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objObject;

	}
	
	public static int asciiValue(String input){
		int value=0;
		for(int i=0;i<input.length();i++){
			value = value+(int)input.charAt(i);
		}
		return value;
	}
			
}
