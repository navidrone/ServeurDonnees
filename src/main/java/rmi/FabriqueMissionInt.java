package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FabriqueMissionInt extends Remote {
	
	
	/**
	 * 
	 * Remonte la liste intégrale des missions stockées en base
	 * 
	 * @return
	 * @throws RemoteException
	 */
	List<? extends MissionInt> getListMission() throws RemoteException;
	
	
	/**
	 * 
	 * remonte la mission "id" depuis la base de données
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	MissionInt getMission(int id) throws RemoteException;
	
	/**
	 * 
	 * Sauvegarde/met à jour la mission "id" 
	 * 
	 * @param mission
	 * @throws RemoteException
	 */
	void saveMission(MissionInt mission) throws RemoteException;
	
	
	/**
	 * 
	 * Supprime la mission "id" du modèle
	 * 
	 * @param id
	 * @throws RemoteException
	 */
	void deleteMission(int id) throws RemoteException;
	
	
	/**
	 * 
	 * Déclenche le calcul des relevés vierges par le serveur de calcul
	 * Ceux-ci sont sauvegardés en base automatiquement. 
	 * 
	 * Cette fonctionnalité est bloquée si la mission a déjà des relevés
	 * 
	 * @param mission
	 * @throws RemoteException
	 */
	void calculeMission(MissionInt mission) throws RemoteException;

}
