import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class NodeClass {

	public static BufferedReader reader;
	
	public static List<Socket> lstOfSockets;
	public static List<ObjectOutputStream> lstOfObjectOutputStream;
	public static List<ObjectInputStream> lstOfObjectInputStream;
	public static List<String> fileList;
	
	public static String nodePort;
	public static String nodeIp;
	public static List<String> serverIPPort;
	public static int  numberOfServerInSystems;
	public static Properties objProperty;
	public static int fileReplicationFactor;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		NodeClass objNodeClass = new NodeClass();
		objProperty = new Properties();
		objProperty.load(new FileInputStream("config.properties"));
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		lstOfSockets = new ArrayList<Socket>();
		lstOfObjectOutputStream= new ArrayList<ObjectOutputStream>();
		lstOfObjectInputStream = new ArrayList<ObjectInputStream>();
		
		
		serverIPPort = new ArrayList<String>();
		numberOfServerInSystems = Integer.parseInt((objProperty.getProperty(("numberOfServersInSystem")))); 
		getallsrverIpPortLst(objProperty);
		
		fileReplicationFactor = Integer.parseInt((objProperty.getProperty(("fileReplicationFactor")))); 
		
		nodePort =objProperty.getProperty("serverPort");
		nodeIp =objProperty.getProperty("serverIP");
		boolean flag = true;
		
		try{
			Thread objThread = new Thread(new NodeAsServer(Integer.parseInt(objProperty.getProperty("serverPort"))));
			objThread.start();
			
			do{
				System.out.println();
				System.out.println("**********************Main Menu****************************");
				System.out.println("Enter the choice(1,2,3) to Perform Operations : ");
				System.out.println("1 - Register Files.");
				System.out.println("2 - Search a File.");
				System.out.println("3 - Exit.");
				System.out.println("***********************************************************");
				
				System.out.println("Enter your choice : ");
				String input = reader.readLine();
				if(flag){
					InputOutStreamCreate(lstOfSockets,lstOfObjectOutputStream,lstOfObjectInputStream,objProperty);
					flag=false;
				}
				fileList = filesAtFolder(objProperty.getProperty("fileLocation"));
				switch (input) {

				case "1":
					objNodeClass.registerAtServer();
					break;
				case "2":
					objNodeClass.serachFile();
					break;
				case "3":
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
	
	public static void getallsrverIpPortLst(Properties objProperty){
		for(int i=1;i<=numberOfServerInSystems;i++){
			serverIPPort.add(objProperty.getProperty(("serverIP"+i))+":"+objProperty.getProperty(("serverPort"+i)));
		}
	}
		
	public int getServer(String key){
		int arrayr= asciiValue(key)%numberOfServerInSystems;
		return arrayr;
	}
		
	private static void InputOutStreamCreate(List<Socket> lstOfSockets,List<ObjectOutputStream> lstOfObjectOutputStream,List<ObjectInputStream> lstOfObjectInputStream,Properties objProperty) {
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
	
	/*
	 * Method : filesAtFolder
	 * -------------------------------------------------------------------------
	 * @param : String path return : List<String>
	 *
	 * To get the files present at a location.
	 */
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
	
	public void registerAtServer() throws IOException, ClassNotFoundException {
		Object objResponse1 = null, objResponse2 = null;
		
		for(String file :fileList){
			int server1 = getServer(file);
			List<Integer> server = new ArrayList<Integer>();
			/*
			for(int r=1;r<=fileReplicationFactor;r++){
				if(r==1){
				server.add(server1);}
				else{
					if( server.get(r-2) == (numberOfServerInSystems-1) ){
						server.add(0);
					}else{
						server.add(server.get(r-2)+1);}
				}
			}
			*/
			String message = "1" +":"+serverIPPort.get(0)+ ":" + file;
			/*
			for(int k=0;k<server.size();k++){
				message = message +(":" +serverIPPort.get(server.get(k)));
			}
			
			message = message+":"+nodeIp+":"+nodePort;
			*/
			for(int value :server){
				Socket objSocket = lstOfSockets.get(value);
				ObjectInputStream objInStm1 = lstOfObjectInputStream.get(value);
				ObjectOutputStream objOutStm1 = lstOfObjectOutputStream.get(value);
				objResponse1 = sendReceiveMessageToServer(objOutStm1, objInStm1, message);
				//fileUpload(objSocket,objOutStm1,objInStm1,file);
			}
			
		}
		
		if (null != objResponse1 || null != objResponse2)
		System.out.println("Files Registered on "+fileReplicationFactor+" servers & replicated.");
	}
	
	public void serachFile() throws IOException{
		Object objResponse1=null,objResponse2=null;
		System.out.println("Enter File Name :");
		String file = reader.readLine();
		String message = "2"+":"+file;
		
		int server1 = getServer(file);
		int server2;
		if(server1 == (numberOfServerInSystems-1)){
			server2=0;
		}else {
			server2=server1+1;
		}
		
		ObjectInputStream objInStm1 =lstOfObjectInputStream.get(server1);
		ObjectOutputStream objOutStm1 =lstOfObjectOutputStream.get(server1);
		
		ObjectInputStream objInStm2 =lstOfObjectInputStream.get(server2);
		ObjectOutputStream objOutStm2 =lstOfObjectOutputStream.get(server2);
		
		objResponse1 =sendReceiveMessageToServer(objOutStm1, objInStm1, message);
		if(null==objResponse1 || objResponse1.toString().equalsIgnoreCase("File Not Found")){
		objResponse2 =sendReceiveMessageToServer(objOutStm2, objInStm2, message);
		}
		
		// logic to Show outPut of search.
		String Str3 =null;
		if(null==objResponse1 || objResponse1.toString().equalsIgnoreCase("File Not Found"))
		{	
			Str3=objResponse2.toString();
		}else if(objResponse1.toString().equalsIgnoreCase("File Not Found") && (null==objResponse2 ||objResponse2.toString().equalsIgnoreCase("File Not Found"))){
			Str3="File Not Found";
		}else{
			Str3=objResponse1.toString();
		}
		
		List<String> serverIpPort = new ArrayList<String>();
		
		if(!Str3.equalsIgnoreCase("File Not Found"))
		{
		String outPut[]= Str3.split("#");
		System.out.println();
		System.out.println("Searched file is available at below server/servers :");
		for(int i=0;i<=outPut.length-1;i++){
			serverIpPort.add(outPut[i]);
			String serverIpPod[] = outPut[i].split(":");
			System.out.println("Server"+(i+1)+" : "+"With IP :"+serverIpPod[0]+" , Port :"+serverIpPod[1]);
		}
		filedownloadMenu(serverIpPort,file,objProperty);
		}else{
			System.out.println("File Not Found");
		}
	}
	
	public void filedownloadMenu(List<String> serverIpPort,String fileName,Properties objProperty) throws IOException{
		System.out.println();
		System.out.println("***********************************************************");
		System.out.println("Enter the choice to Perform Operations : ");
		System.out.println("1 - Download the serarched file.");
		System.out.println("Any OTHER key to continue to Main Menu");
		System.out.println("***********************************************************");
		System.out.println("Enter your choice : ");
		String inputt = reader.readLine();
		switch (inputt) {
		case "1":{
			System.out.println();
			System.out.println("***********************************************************");
			System.out.println("Enter the Server number from where you want to download : ");
			System.out.println("Press 1 - Server1, Press 2 - Server2, Press 3 - Server3...");
			System.out.println("Any OTHER key to continue to Main Menu");
			System.out.println("***********************************************************");
			System.out.println("Enter your choice : ");
			String inputtt = reader.readLine();
			int inpt = Integer.parseInt(inputtt);
			if( inpt <= serverIpPort.size()){
				String ipPort[]=serverIpPort.get(inpt-1).split(":");
				downloadFileFromPeer(ipPort[0],ipPort[1],fileName,objProperty);
			}
			}
			break;
		}
	}
	
	 public void downloadFileFromPeer(String peerWithFileIP,String peerWithFilePort,String filename,Properties objProperty) throws NumberFormatException, UnknownHostException, IOException{
			Socket objpeerSocket = new Socket(peerWithFileIP, Integer.parseInt(peerWithFilePort));
			try {
				ObjectOutputStream objClientOutStream = new ObjectOutputStream(objpeerSocket.getOutputStream());
		    	objClientOutStream.writeObject("3:"+filename);
				objClientOutStream.flush();
				
				
		    	byte [] mybytearray  = new byte [ 2048];
		    	FileOutputStream  fileOPstream = new FileOutputStream(objProperty.getProperty("fileLocation")+filename);
	    		DataInputStream objDataInputStream = new DataInputStream(objpeerSocket.getInputStream());
	    		long sixToGet = objDataInputStream.readLong();
	    		BufferedOutputStream objBufferedOutputStream = new BufferedOutputStream(fileOPstream);
				
				int  sizeGet = 0,sizeAtPresent = 0;	
				while(sizeGet < sixToGet){
					sizeAtPresent = (int)sixToGet - sizeGet;
					sizeAtPresent = sizeAtPresent < mybytearray.length ? sizeAtPresent : mybytearray.length;
					objDataInputStream.read(mybytearray, 0, (int) sizeAtPresent);
					objBufferedOutputStream.write(mybytearray,0,sizeAtPresent);
					objBufferedOutputStream.flush();
					sizeGet += sizeAtPresent;
				}
				//if(sizeGet==fileSize)
				//System.out.println("**********File Downloaded Successfully***********");
				objBufferedOutputStream.close();
			} catch (Exception e) 
				{
				}
	 }
	 
	 public void fileUpload(Socket objSocket ,ObjectOutputStream objClientServerOutStream,ObjectInputStream objClientServerInStream,String file){
			try {
				
				File myfile = new File(objProperty.getProperty("fileLocation")+file);
				long lengthOfFile = myfile.length();
				byte [] mybytearray  = new byte [1024];
				DataOutputStream objDataOutputStream = new DataOutputStream(objSocket.getOutputStream());
				
				objClientServerOutStream.writeObject("4:"+file);
				objClientServerOutStream.flush();
				objDataOutputStream.writeLong(lengthOfFile);
				objDataOutputStream.flush();
				FileInputStream fileInSt =new FileInputStream(myfile);
				BufferedInputStream objBufInStream =new BufferedInputStream(fileInSt);
					
				long sizeSent = 0;	
				while(sizeSent < lengthOfFile){
					int sizeAtPresent = ((int)lengthOfFile - (int) sizeSent);
					sizeAtPresent = sizeAtPresent < mybytearray.length ? sizeAtPresent : mybytearray.length;
					objBufInStream.read(mybytearray, 0, (int) sizeAtPresent);
					objDataOutputStream.write(mybytearray,0,sizeAtPresent);
					objDataOutputStream.flush();
					sizeSent += sizeAtPresent;
				}
				objBufInStream.close();
			}catch(Exception e){
			}
		}
	
	
}
