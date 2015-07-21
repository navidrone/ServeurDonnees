package serveurDonnees.controller;

import java.io.DataInputStream;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import serveurDonnees.service.FabriqueMissionImp;



public class ControllerDefault {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		 try 
	        { 
			 			 
			   	Registry registry = getRegistry("localhost",1099);

	            FabriqueMissionImp fabrique = new FabriqueMissionImp("FabriqueMission",1099); 

		        DataInputStream in = new DataInputStream(System.in); 
		        System.out.print("Taper rc, pour arreter le serveur..."); 
		        System.out.flush(); 
		        String valeur= in.readLine(); 

		        // Désenregistrement de l'OD de l'annuaire 
		        Naming.unbind("rmi://localhost:1099/FabriqueMission"); 

		        // Destruction de l'OD 
		        UnicastRemoteObject.unexportObject(fabrique,true); 
		        
//	            registry.rebind("FabriqueMission", fabrique); 
//	            
//	            System.out.println("bind complete port : 1099");
	            
	        } 
	        catch (Exception e) 
	        { 
	            System.out.println("FabriqueMission err: " + e.getMessage()); 
	            e.printStackTrace(); 
	        } 

	}
	
	public static Registry getRegistry(String ip,int port) throws RemoteException {
		  if (port <= 0)   port=Registry.REGISTRY_PORT;
		  Registry reg=LocateRegistry.getRegistry(port);
		  if (exists(reg))   System.out.println("Located an RMI registry at :"+ port);
		 else {
			 System.out.println("Starting RMI registry on 'localhost:" + port + "'");
		    reg=LocateRegistry.createRegistry(port);
		  }
		  return reg;
		}
	
	private static boolean exists(Registry reg){
		  try {
			
		    reg.lookup("FabriqueMission");
		  }
		 catch (  NotBoundException e) {
			System.out.println("'FabriqueMission' not bound");
			//e.printStackTrace();
		  }
		catch (  RemoteException e) {
			System.out.println("failed to communicate with registry - assuming it's not running");
			//e.printStackTrace();
		    reg=null;
		  }
		  return (reg != null);
		}
	
	
}