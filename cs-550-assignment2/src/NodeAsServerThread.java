import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;

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
				String operation=message.valueOf(message.charAt(0));
				String key =null;
				if(!operation.equalsIgnoreCase("4")){
					key =message.substring(1,24).trim();
				}
				if (operation.equalsIgnoreCase("1")) {
					boolean response = putValues(key, message.substring(24));
					//System.out.println(key);
					objClientServerOutStream.writeObject(response);
					objClientServerOutStream.flush();
				} else if (operation.equalsIgnoreCase("2")) {
					String response = getValueforKey(key);
					objClientServerOutStream.writeObject(response);
					objClientServerOutStream.flush();
				} else if (operation.equalsIgnoreCase("3")) {
					boolean response = deleteKeyValue(key);
					objClientServerOutStream.writeObject(response);
					objClientServerOutStream.flush();
				} else if (operation.equalsIgnoreCase("4")) {
					String response = getSizeofTable();
					objClientServerOutStream.writeObject(response);
					objClientServerOutStream.flush();
				}
				
			}
		} catch (Exception e) {
		}
	}

	public boolean putValues(String key,String value) {
		objHashTable.put(key, value);
		return true;
	}

	public String getValueforKey(String key) {
		if(null==objHashTable.get(key)){
			return "Value not found.";
		}else{
			return objHashTable.get(key);
		}
	}

	public boolean deleteKeyValue(String key) {
		if(null==objHashTable.remove(key)){
			return false;
		}else{
			return true;
		}
	}
	
	public String getSizeofTable(){
		int size =objHashTable.size();
		return ""+size;
	}

}
