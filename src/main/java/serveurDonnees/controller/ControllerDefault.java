package serveurDonnees.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

import serveurDonnees.service.FabriqueMissionImp;



public class ControllerDefault {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		 try 
	        { 
	            FabriqueMissionImp fabrique = new FabriqueMissionImp(); 
	            
	            Registry registry = getRegistry(1099);
	            registry.rebind("FabriqueMission", fabrique); 
	            
	            System.out.println("bind complete port : 1099");
	            
	        } 
	        catch (Exception e) 
	        { 
	            System.out.println("FabriqueMission err: " + e.getMessage()); 
	            e.printStackTrace(); 
	        } 

	}
	
	public static Registry getRegistry(int port) throws RemoteException {
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
			e.printStackTrace();
		  }
		catch (  RemoteException e) {
			System.out.println("failed to communicate with registry - assuming it's not running");
			e.printStackTrace();
		    reg=null;
		  }
		  return (reg != null);
		}
	
}