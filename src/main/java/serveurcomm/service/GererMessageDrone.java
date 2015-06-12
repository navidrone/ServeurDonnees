package serveurcomm.service;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import ch.qos.logback.classic.Logger;

public class GererMessageDrone implements Runnable{
	private Socket client;

	  public GererMessageDrone(Socket client) {
		this.client = client;
	  }
	@Override
	public void run() {
	    InputStream is = null;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    int bufferSize = 0;
		try {
	        is = client.getInputStream();

	        bufferSize = client.getReceiveBufferSize();
	        System.out.println("Buffer size: " + bufferSize);
	    } catch (IOException ex) {
	        System.out.println("Can't get socket input stream. ");
	    }

	    try {
	        fos = new FileOutputStream("message_drone.json");
	        bos = new BufferedOutputStream(fos);

	    } catch (FileNotFoundException ex) {
	        System.out.println("File not found. ");
	    }

	    byte[] bytes = new byte[bufferSize];

	    int count;
	    try{
		    while ((count = is.read(bytes)) > 0) {
		        bos.write(bytes, 0, count);
		    }
		    bos.flush();
		    bos.close();
		    is.close();
		    client.close();
		    //serverSocket.close();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }

	}
	
	public void traiterMessageClient(){
		FileInputStream fis = new FileInputStream(arg0);
		String json = new String(Files.readAllBytes(Paths.get("message_drone.json"), "UTF-8")); 
		final ObjectMapper mapper = new ObjectMapper();
	    final TestSuite readValue = mapper.readValue(json, TestSuite.class);
	    S
	}
}
