package serveurDonnees.service;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.springframework.beans.factory.annotation.Autowired;

import rmi.FabriqueMissionInt;
import rmi.MissionInt;
import serveurDonnees.modele.bean.Mission;
import serveurDonnees.modele.dao.CoordGpsDAO;
import serveurDonnees.modele.dao.MissionDAO;
import serveurDonnees.modele.dao.NavidroneDAO;
import serveurDonnees.modele.dao.UtilisateurDAO;

public class FabriqueMissionImp extends UnicastRemoteObject implements FabriqueMissionInt {

	@Autowired
    private MissionDAO missionDao;
	
	private static final long serialVersionUID = 1L;

	
    public FabriqueMissionImp(String nomRegister, int portRegister) throws Exception 
        { 
       			super(); 
       			missionDao = new MissionDAO();
       			try{
       				Naming.unbind("rmi://localhost:"+portRegister+ "/"+ nomRegister);
       			}catch (Exception e){
       				
       			}
                Naming.bind("rmi://localhost:"+portRegister+ "/"+ nomRegister,this);        		
        } 


	public MissionInt getMission(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return missionDao.get(id);
	}

	public void saveMission(MissionInt mission) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void deleteMission(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
