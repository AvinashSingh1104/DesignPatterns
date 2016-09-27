import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Properties;

public class NodeAsServerThread extends Thread {

	Socket objSocket;
	Hashtable<String, String> objHashTable;

	public NodeAsServerThread(Socket objSocket, Hashtable<String, String> objHashTable) {
		this.objSocket = objSocket;
		this.objHashTable = objHashTable;
	}
	
	


	@SuppressWarnings("static-access")
	public void run() {
		try {
			ObjectOutputStream objClientServerOutStream = new ObjectOutputStream(objSocket.getOutputStream());
			ObjectInputStream objClientServerInStream = new ObjectInputStream(objSocket.getInputStream());

			while (true){
				String message =(String)objClientServerInStream.readObject();
				String[] msgg = message.split(":");
				
				if (msgg[0].equalsIgnoreCase("1")) {
					boolean response =register(msgg[3], msgg[1]+":"+msgg[2]);
					/*for(int k=3;k<msgg.length-1;){
						register(msgg[3], msgg[k+1]+":"+msgg[k+2]);
						k=k+2;
					}*/
					objClientServerOutStream.writeObject(response);
					objClientServerOutStream.flush();
				} else if (msgg[0].equalsIgnoreCase("2")) {
					String response = searchFile(msgg[1]);
					objClientServerOutStream.writeObject(response);
					objClientServerOutStream.flush();
				} else if (msgg[0].equalsIgnoreCase("3")) {
					fileUpload(objClientServerOutStream,objClientServerInStream,msgg[1]);
				} else if (msgg[0].equalsIgnoreCase("4")) {
					downloadFileFromPeer(objClientServerOutStream,objClientServerInStream,msgg[1]);
				} 
				
			}
		} catch (Exception e) {
		}
	}

	public boolean register(String fileName, String serverDetail) throws UnknownHostException {
		if (objHashTable.containsKey(fileName)) {
			String fileDetails = objHashTable.get(fileName);
			if (!fileDetails.contains(serverDetail))
				fileDetails=fileDetails+"#"+serverDetail;
			objHashTable.put(fileName, fileDetails);
		} else {
			objHashTable.put(fileName, serverDetail);
		}
		return true;
	}

	public String  searchFile(String fileNm) {
		if(null==objHashTable.get(fileNm)){
			return "File Not Found";
		}else{
			String listOfStr =objHashTable.get(fileNm);
			return listOfStr;
		}
	}
	
	public void fileUpload(ObjectOutputStream objClientServerOutStream,ObjectInputStream objClientServerInStream,String filename){
		try {
			Properties objProperty = new Properties();
			objProperty.load(new FileInputStream("config.properties"));
		
				
			File myfile = new File(objProperty.getProperty("fileLocation")+filename);
			long lengthOfFile = myfile.length();
			byte [] mybytearray  = new byte [2048];
			DataOutputStream objDataOutputStream = new DataOutputStream(objSocket.getOutputStream());
			
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
	

	 public void downloadFileFromPeer(ObjectOutputStream objClientServerOutStream,ObjectInputStream objClientServerInStream,String fileName) throws NumberFormatException, UnknownHostException, IOException{
			try {
				DataInputStream objDataInputStream = new DataInputStream(objSocket.getInputStream());
				long fileSize = objDataInputStream.readLong();
				Properties objProperty = new Properties();
				objProperty.load(new FileInputStream("config.properties"));
				byte [] mybytearray  = new byte [ 1024];
		    	FileOutputStream  fileOPstream = new FileOutputStream(objProperty.getProperty("fileLocation")+fileName);
	    		BufferedOutputStream objBufferedOutputStream = new BufferedOutputStream(fileOPstream);
				
	    		int  sizeGet = 0,sizeAtPresent = 0;	
				long sixToGet = fileSize;
				while(sizeGet < sixToGet){
					sizeAtPresent = (int)sixToGet - sizeGet;
					sizeAtPresent = sizeAtPresent < mybytearray.length ? sizeAtPresent : mybytearray.length;
					objDataInputStream.read(mybytearray, 0, (int) sizeAtPresent);
					objBufferedOutputStream.write(mybytearray,0,sizeAtPresent);
					objBufferedOutputStream.flush();
					sizeGet += sizeAtPresent;
				}
				objBufferedOutputStream.close();
			} catch (Exception e) 
				{
				}
			
	 }

}
