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
			 			 
	            FabriqueMissionImp fabrique = new FabriqueMissionImp("FabriqueMission",1099); 

		        DataInputStream in = new DataInputStream(System.in); 
		        System.out.println("************************************************"); 
		        System.out.println("************************************************"); 
		        System.out.println("SERVEUR DE DONNEES DEMARRE !!!!!!!!!! "); 
		        System.out.println("************************************************"); 
		        System.out.println("************************************************"); 
		        String valeur= in.readLine(); 

		        // Désenregistrement de l'OD de l'annuaire 
		        Naming.unbind("rmi://localhost:1099/FabriqueMission"); 

		        // Destruction de l'OD 
		        UnicastRemoteObject.unexportObject(fabrique,true); 
		        
	            
	        } 
	        catch (Exception e) 
	        { 
	            System.out.println("FabriqueMission err: " + e.getMessage()); 
	            e.printStackTrace(); 
	        } 

	}
	
	
}