package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceCalculeMissionInt extends Remote {
	
	
	/**
	 * 
	 * D�clenche le calcul des relev�s vierges par le serveur de calcul
	 * Ceux-ci sont sauvegard�s en base automatiquement. 
	 * 
	 * Cette fonctionnalit� est bloqu�e si la mission a d�j� des relev�s
	 * 
	 * @param id
	 * @throws RemoteException
	 */
	void calculeMission(int id) throws RemoteException;

}
