package serveurDonnees.service;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rmi.FabriqueMissionInt;
import rmi.MissionInt;
import rmi.ServiceCalculeMissionInt;
import serveurDonnees.modele.dao.MissionDAO;

public class FabriqueMissionImp extends UnicastRemoteObject implements FabriqueMissionInt {

	@Autowired
    private MissionDAO missionDao;
	
	private static final long serialVersionUID = 1L;


	private ServiceCalculeMissionInt serviceCalculeMissionInt;
	
	/**
	 * 
	 * Accès exclusif à la FabriqueMission RMI
	 * 
	 * @return
	 */
	private ServiceCalculeMissionInt getServiceCalculeMission() {
		
		if(serviceCalculeMissionInt == null){
			
			 try {
				 serviceCalculeMissionInt =  (ServiceCalculeMissionInt) Naming.lookup("rmi://localhost:1099/CalculMission");
		            
		        } catch (Exception e) {
		            System.err.println("Client exception: " + e.toString());
		            e.printStackTrace();
		        }
			
		}
		
		return serviceCalculeMissionInt;
	}
	
	
	
	
    public FabriqueMissionImp(String nomRegister, int portRegister) throws Exception 
        { 
       			super(); 
       			missionDao = new MissionDAO();
       			

       			try{
           			LocateRegistry.createRegistry(portRegister);
           			//System.setProperty("java.security.policy","file:D:/git/serveurDonnees/security.policy");
   						
       			}catch (Exception e){
       				//e.printStackTrace();       				
       			}
       			       			
       			try{
       				Naming.unbind("rmi://localhost:"+portRegister+ "/"+ nomRegister);
       			}catch (Exception e){
       				//e.printStackTrace();
       			}
       			
       			
                Naming.bind("rmi://localhost:"+portRegister+ "/"+ nomRegister,this); 
                
        } 


	public MissionInt getMission(int id) throws RemoteException {
		return missionDao.get(id);
	}

	public void saveMission(MissionInt mission) throws RemoteException {
		try {
			missionDao.saveOrUpdateFromRMI(mission);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMission(int id) throws RemoteException {
		missionDao.delete(id);
	}

	@Override
	public void calculeMission(int id) throws RemoteException {

		getServiceCalculeMission().calculeMission(id);
		
	}
	

	@Override
	public List<? extends MissionInt> getListMission() throws RemoteException {
		return missionDao.list();
	}

}
