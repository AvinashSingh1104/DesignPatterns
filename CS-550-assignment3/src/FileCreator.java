import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;



public class FileCreator {
	
	
	private  static String WORKSPACE;
	private final static String FILE_PREFIX = "Avi_";
	private static Random generator = new Random();
	
public static void main(String[] args) throws FileNotFoundException, IOException {
	Properties objProperty = new Properties();
	objProperty.load(new FileInputStream("config.properties"));
	
	WORKSPACE = objProperty.getProperty("fileLocation");
	int Kb =1000;
	int mb =1000*1000;
	int gb = 1000*1000*1000;
	System.out.println("generating files");
	int numberOfFiles=100;
	
	
	//generateFiles(numberOfFiles, 1, Kb);
	//generateFiles(numberOfFiles, 1, 10*Kb);
	generateFiles(numberOfFiles, 1, 100*Kb);
	//generateFiles(numberOfFiles, 1, mb);
	//generateFiles(numberOfFiles, 1, 10*mb);
	//generateFiles(numberOfFiles, 1, 100*mb);
	//generateFiles(numberOfFiles, 1, gb);
	System.out.println("DOne with files creation, number of files created="+numberOfFiles);
}


private static void generateFiles(int fileCount, int fileSizeMin, int fileSizeMax) {
	FileOutputStream output;
	byte[] randomData;
	int fileSizeInBytes;
	
	
	try {
		for(int i = 0; i < fileCount; i++) {
			output = new FileOutputStream(WORKSPACE + FILE_PREFIX + i + ".txt");
			//fileSizeInBytes = generator.nextInt((fileSizeMax - fileSizeMin) + 1) + fileSizeMin;
			fileSizeInBytes= (fileSizeMax-fileSizeMin)+1;
			System.out.println("File size in bytes="+fileSizeInBytes);
			randomData = getByteArrayOfSize(fileSizeInBytes);
			output.write(randomData);
			output.close();
		}
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}catch(IOException e) {
		e.printStackTrace();
	}
}

public static byte[] getByteArrayOfSize(int numberOfBytes) {
	byte[] randomData = new byte[numberOfBytes];
	//generator.nextBytes(randomData);
	for (int i = 0; i < numberOfBytes; i++) {
		randomData[i]=(byte)'a';
	}
	return randomData;
}
}
