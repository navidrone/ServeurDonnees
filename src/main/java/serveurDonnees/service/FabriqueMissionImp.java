package serveurDonnees.service;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import rmi.DroneInt;
import rmi.FabriqueMissionInt;
import rmi.MissionInt;
import rmi.ReleveInt;
import serveurDonnees.modele.bean.Drone;
import serveurDonnees.modele.bean.Mission;
import serveurDonnees.modele.bean.Releve;
import serveurDonnees.modele.dao.MissionDAO;

public class FabriqueMissionImp extends UnicastRemoteObject implements FabriqueMissionInt {

	@Autowired
    private MissionDAO missionDao;
	
	private static final long serialVersionUID = 1L;

	
    public FabriqueMissionImp(String nomRegister, int portRegister) throws Exception 
        { 
       			super(); 
       			missionDao = new MissionDAO();
       			

       			try{
           			LocateRegistry.createRegistry(portRegister);
       			}catch (Exception e){
       				e.printStackTrace();       				
       			}
       			
       			try{
       				Naming.unbind("rmi://localhost:"+portRegister+ "/"+ nomRegister);
       			}catch (Exception e){
       				//e.printStackTrace();
       			}
       			
       			
                Naming.bind("rmi://localhost:"+portRegister+ "/"+ nomRegister,this); 
        } 


	public MissionInt getMission(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return missionDao.get(id);
	}

	public void saveMission(MissionInt mission) throws RemoteException {
		// TODO Auto-generated method stub
				
		missionDao.saveOrUpdate(recycleMission(mission));
	}

	public void deleteMission(int id) throws RemoteException {
		// TODO Auto-generated method stub
		missionDao.delete(id);
	}

	@Override
	public void calculeMission(MissionInt mission) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
	private Mission recycleMission(MissionInt missionInt) throws RemoteException{
		Mission m = new Mission();
		BeanUtils.copyProperties(missionInt, m);
		
		ArrayList<Releve> releveList = new ArrayList<Releve>();
		ArrayList<Drone>  droneList  = new ArrayList<Drone>();
		
		if(missionInt.getReleve() != null){
			
			for(ReleveInt releveInt:missionInt.getReleve()){
				Releve r = new Releve();
				BeanUtils.copyProperties(releveInt, r);
				releveList.add(r);
			}
			
		}

		if(missionInt.getFlotte() != null){
		
			for(DroneInt droneInt:missionInt.getFlotte()){
				Drone d = new Drone();
				BeanUtils.copyProperties(droneInt, d);
				droneList.add(d);
			}
			
		}
		
		m.setReleve(releveList);
		m.setFlotte(droneList);
		
		return m;
	}

}
