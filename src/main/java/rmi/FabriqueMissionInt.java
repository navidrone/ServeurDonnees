package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FabriqueMissionInt extends Remote {
	
	
	/**
	 * 
	 * Remonte la liste int�grale des missions stock�es en base
	 * 
	 * @return
	 * @throws RemoteException
	 */
	List<? extends MissionInt> getListMission() throws RemoteException;
	
	
	/**
	 * 
	 * remonte la mission "id" depuis la base de donn�es
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	MissionInt getMission(int id) throws RemoteException;
	
	/**
	 * 
	 * Sauvegarde/met � jour la mission "id" 
	 * 
	 * @param mission
	 * @throws RemoteException
	 */
	void saveMission(MissionInt mission) throws RemoteException;
	
	
	/**
	 * 
	 * Supprime la mission "id" du mod�le
	 * 
	 * @param id
	 * @throws RemoteException
	 */
	void deleteMission(int id) throws RemoteException;
	
	
	/**
	 * 
	 * D�clenche le calcul des relev�s vierges par le serveur de calcul
	 * Ceux-ci sont sauvegard�s en base automatiquement. 
	 * 
	 * Cette fonctionnalit� est bloqu�e si la mission a d�j� des relev�s
	 * 
	 * @param mission
	 * @throws RemoteException
	 */
	void calculeMission(MissionInt mission) throws RemoteException;

}
