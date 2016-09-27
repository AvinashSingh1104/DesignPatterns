import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class NodeAsServer implements Runnable {


	public int ports;
	
	Hashtable<String, String> objHashTable = new Hashtable<String,String>();

	public NodeAsServer(int port) {
		ports = port;
	}

	@Override
	public void run() {
		try {

			ServerSocket objSocket = new ServerSocket(ports);

			do {
				Socket objServerSocket = objSocket.accept();
				new NodeAsServerThread(objServerSocket,objHashTable).start();
			} while (true);

		} catch (Exception e) {
		}
	}
}
