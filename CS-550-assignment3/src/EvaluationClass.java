import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

public class EvaluationClass {

	public static List<Socket> lstOfSockets;
	public static List<ObjectOutputStream> lstOfObjectOutputStream;
	public static List<ObjectInputStream> lstOfObjectInputStream;
	public static int  numberOfServerInSystems;
	public static List<String> fileList;
	public static List<String> serverIPPort;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		Properties objProperty = new Properties();
		objProperty.load(new FileInputStream("config.properties"));
		numberOfServerInSystems = Integer.parseInt((objProperty.getProperty(("numberOfServersInSystem"))));
		NodeClass objNodeClass = new NodeClass();
		EvaluationClass objEvaluationClass = new EvaluationClass();
		
		lstOfSockets = new ArrayList<Socket>();
		lstOfObjectOutputStream = new ArrayList<ObjectOutputStream>();
		lstOfObjectInputStream = new ArrayList<ObjectInputStream>();
		serverIPPort = new ArrayList<String>();
		getallsrverIpPortLst(objProperty);
		fileList = filesAtFolder(objProperty.getProperty("fileLocation"));
		
		objEvaluationClass.InputOutStreamCreate(lstOfSockets,lstOfObjectOutputStream,lstOfObjectInputStream,objProperty);
		
		while(true){
		if(Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(12)== 20){
			
		//Test Register Method 
		testRegisterFuntion(objNodeClass,objProperty);
		
		//Test Search Method 
		testSearchFunction(objNodeClass,objProperty);
		
		//Test Download Method 
		testDownloadFunction(objNodeClass,objProperty);
		break;
		}
		}
	}
	
	public static void testRegisterFuntion(NodeClass objNodeClass,Properties objProperty) throws ClassNotFoundException, IOException{
		long starttime = System.currentTimeMillis();
		for(String file :fileList){
			int server1 = getServer(file);
			String message = "1" +":"+serverIPPort.get(0)+ ":" + file;
			ObjectInputStream objInStm1 = lstOfObjectInputStream.get(server1);
			ObjectOutputStream objOutStm1 = lstOfObjectOutputStream.get(server1);
			
			objNodeClass.sendReceiveMessageToServer(objOutStm1, objInStm1, message);
		}
		System.out.println("");
		System.out.println("Total Time in milliseconds (Registering 10000 file):"+(System.currentTimeMillis()-starttime));
		System.gc();
	}
	
	public static void testSearchFunction(NodeClass objNodeClass,Properties objProperty) throws ClassNotFoundException, IOException{
		long starttime = System.currentTimeMillis();
		for(String file :fileList){
			int server1 = getServer(file);
			String message = "2" +":"+ file;
			ObjectInputStream objInStm1 = lstOfObjectInputStream.get(server1);
			ObjectOutputStream objOutStm1 = lstOfObjectOutputStream.get(server1);
			
			objNodeClass.sendReceiveMessageToServer(objOutStm1, objInStm1, message);
			
		}
		System.out.println("");
		System.out.println("Total Time in milliseconds (Searching 10000 file):"+(System.currentTimeMillis()-starttime));
		System.gc();
	}
	
	public static void testDownloadFunction(NodeClass objNodeClass,Properties objProperty) throws ClassNotFoundException, IOException{
		System.gc();
		long starttime = System.currentTimeMillis();
		for(String file :fileList){
		//for(int i=0;i<1;i++){
			//String file = "Avi_" + i + ".txt";
			//System.out.println(i);
			objNodeClass.downloadFileFromPeer(objProperty.getProperty("serverIP"), objProperty.getProperty("serverPort"), file, objProperty);
			System.gc();
		}
		System.out.println("");
		System.out.println("Total Time in milliseconds (Downloading 10000 file):"+(System.currentTimeMillis()-starttime));
	}
	
	
	
	private void InputOutStreamCreate(List<Socket> lstOfSockets,List<ObjectOutputStream> lstOfObjectOutputStream,List<ObjectInputStream> lstOfObjectInputStream,Properties objProperty) {
		try{
		for(int i=1;i<=numberOfServerInSystems;i++){
			@SuppressWarnings("resource")
			Socket clientsocket = new Socket((objProperty.getProperty(("serverIP"+i))), Integer.parseInt((objProperty.getProperty(("serverPort"+i)))));
			ObjectOutputStream objOutStm = new ObjectOutputStream(clientsocket.getOutputStream());
			ObjectInputStream objInStm = new ObjectInputStream(clientsocket.getInputStream());
			lstOfSockets.add(clientsocket);
			lstOfObjectOutputStream.add(objOutStm);
			lstOfObjectInputStream.add(objInStm);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int getServer(String key){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		return arrayr;
	}
	
	public static int asciiValue(String input){
		int value=0;
		for(int i=0;i<input.length();i++){
			value = value+(int)input.charAt(i);
		}
		return value;
	}
	
	public static void getallsrverIpPortLst(Properties objProperty){
		for(int i=1;i<=numberOfServerInSystems;i++){
			serverIPPort.add(objProperty.getProperty(("serverIP"+i))+":"+objProperty.getProperty(("serverPort"+i)));
		}
	}
	
	public static List<String> filesAtFolder(String path) {
		List<String> fileList = new ArrayList<>();
		File objFile = new File(path);
		for (File listOfFiles : (objFile.listFiles())) {
			if (listOfFiles.isFile()) {
				fileList.add(listOfFiles.getName());
			}
		}
		return fileList;
	}

}
