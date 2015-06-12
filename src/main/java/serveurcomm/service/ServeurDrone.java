package serveurcomm.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurDrone implements Runnable{
	private ServerSocket serverSocket;
    private int port;
    
    public ServeurDrone(int port) {
        this.port = port;
    }
	@Override
	public void run() {
		
        try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Socket client = null;
        
        while(true){
        	System.out.println("Waiting for clients...");
        	try {
				client = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Thread thread = new Thread(new GererMessageDrone(client));
            thread.start();
        }     
	}

}
