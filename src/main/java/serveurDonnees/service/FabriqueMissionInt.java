package serveurDonnees.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveurDonnees.modele.bean.Mission;

public interface FabriqueMissionInt extends Remote {
	
	Mission getMission(int id) throws RemoteException;
	
	void saveMission(Mission mission) throws RemoteException;
	
	void deleteMission(int id) throws RemoteException;

}
