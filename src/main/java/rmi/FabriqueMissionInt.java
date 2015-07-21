package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FabriqueMissionInt extends Remote {
	
	MissionInt getMission(int id) throws RemoteException;
	
	void saveMission(MissionInt mission) throws RemoteException;
	
	void deleteMission(int id) throws RemoteException;

}
