package serveurDonnees.modele.dao;
 
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rmi.DroneInt;
import rmi.MissionInt;
import rmi.ReleveInt;
import serveurDonnees.modele.bean.CoordGps;
import serveurDonnees.modele.bean.Drone;
import serveurDonnees.modele.bean.Mission;
import serveurDonnees.modele.bean.Releve;
 
@Repository
public class MissionDAO extends NavidroneDAO {
 
    public MissionDAO() {
         
    }
     

    @Transactional
    public List<Mission> list() throws RemoteException {
        @SuppressWarnings("unchecked")
        List<Mission> listMission = (List<Mission>)  getSession()
                .createCriteria(Mission.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        
        for(Mission m:listMission){
        	m=addReleve(m);   
        	m=addDrone(m);
        }
        return listMission;
    }

    @Transactional
    public void saveOrUpdate(Mission mission){
    	
    	trace("Mise à jour de la mission : "+mission.getName());	
    	
    	getSession().saveOrUpdate(mission);
    	 
     	trace("Je suis sorti...");
    	 
    	 ReleveDAO releveDAO = new ReleveDAO();
    	 DroneDAO droneDAO = new DroneDAO();
    	 
    	 for(Releve r:(ArrayList<Releve>)mission.getReleve()){
    		 releveDAO.saveOrUpdate(r);
    	 }
    	 
    	 for(Drone d:(ArrayList<Drone>)mission.getFlotte()){
    		 droneDAO.saveOrUpdate(d);
    	 }
    	 
    	 majFlotte(mission);
    }
    
    @Transactional
    public void saveOrUpdateFromRMI(MissionInt missionInt) throws RemoteException{
    	
    	trace("Mise à jour de la mission : "+missionInt.getName());	

    		CoordGps gpsDep = null;
    		CoordGps gpsAr = null;
    		
    		if("bathymetrie".equals(missionInt.getType())){
        		gpsAr = new CoordGps(missionInt.getCoord_ar().getId(),
    							missionInt.getCoord_ar().getLattitude(), 
    							missionInt.getCoord_ar().getLongitude());
    			
    		}
    		 gpsDep =  new CoordGps(missionInt.getCoord_dep().getId(),
							missionInt.getCoord_dep().getLattitude(), 
							missionInt.getCoord_dep().getLongitude());
    		
    	
        	Mission missionToSave = new Mission(missionInt.getId(), 
        										missionInt.getName(), 
        										missionInt.getType(),
        										gpsDep , 
        										gpsAr , 
        										missionInt.getPeriode(), 
        										missionInt.getDensite(), 
        										missionInt.getPortee());
        	
        	trace("Etat du coordonnées GPS de départ");
        	trace("ID : "+missionToSave.getCoord_dep().getId());
        	trace("LATTITUDE : "+missionToSave.getCoord_dep().getLattitude());
        	trace("LONGITUDE : "+missionToSave.getCoord_dep().getLongitude());
        	
        	if(missionToSave.getId() != null){
            	getSession().saveOrUpdate(merge(missionToSave));        		
        	}else{
            	getSession().saveOrUpdate(missionToSave);
        	}
        	
        	 
        	
     	trace("Je suis sorti...");
    	 
    	 ReleveDAO releveDAO = new ReleveDAO();
    	 DroneDAO droneDAO = new DroneDAO();
    	 
    	 if(missionInt.getReleve() != null){
        	 for(ReleveInt releveInt:missionInt.getReleve()){
        		 Releve r = new Releve(releveInt,missionToSave);    			 
        		 releveDAO.saveOrUpdate(r);
        	 }
    		 
    	 }
    	 
    	 if(missionInt.getFlotte() != null){
    		 missionToSave.setFlotte(new ArrayList<Drone>());
        	 for(DroneInt droneInt:missionInt.getFlotte()){
    			Drone d = new Drone();
    			BeanUtils.copyProperties(droneInt, d);
        		 droneDAO.saveOrUpdate(d);
        		 ((ArrayList<Drone>)missionToSave.getFlotte()).add(d);
        	 }
        	 
        	 majFlotteFromRMI(missionToSave);
    	 }
    }
 

    @Transactional
    public void delete(int id) {
        Mission missionToDelete = null;
		try {
			missionToDelete = new Mission();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        missionToDelete.setId(id);
        getSession().delete(merge(missionToDelete));
        getSession().flush();
    }


    @Transactional
    public Mission get(int id) throws RemoteException {    	

    	trace("Récupération de la mission : "+id);
    	
        String hql = "from Mission where id=" + id;
               
        Query query = getSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Mission> missions = (List<Mission>) query.list();
         
        if (missions != null && !missions.isEmpty()) {
        	Mission m = missions.get(0);
        	m = addReleve(m);
        	m = addDrone(m);
            return m;
        }
         
        return null;
    }
    
    @Transactional
    public Mission merge(Mission mission){
    	return (Mission)getSession().merge(mission);
    }
    
    @Transactional
    private Mission addReleve(Mission m) throws RemoteException
    {
    	ReleveDAO releveDAO = new ReleveDAO();
    	
    	m.setReleve((List<? extends ReleveInt>)releveDAO.getByMission(m.getId()));
    	
    	return m;
    }
    
    @Transactional
    private Mission addDrone(Mission m)
    {
    	DroneDAO droneDAO = new DroneDAO();
    	
    	m.setFlotte((ArrayList<Drone>)droneDAO.getByMission(m.getId()));
    	
    	return m;
    }

    @Transactional
    private void majFlotte(Mission m){    		
    	
    	/*Purge de la table Flotte*/
    	String sql = "Delete from FLOTTE where MISSION_ID=" + m.getId() ;
        Query query = getSession().createSQLQuery(sql);
        query.executeUpdate();
    	
    	/*Insertion de la table Flotte*/   	 
	   	 for(Drone d:(ArrayList<Drone>)m.getFlotte()){
	   		 sql = "Insert into FLOTTE (MISSION_ID,DRONE_ID) values(" + m.getId()+","+d.getId()+")" ;
	         query.executeUpdate();
	   	 }     
    	
    }
    
    @Transactional
    private void majFlotteFromRMI(MissionInt m) throws RemoteException{    		
    	
    	/*Purge de la table Flotte*/
    	String sql = "Delete from FLOTTE where MISSION_ID=" + m.getId() ;
        Query query = getSession().createSQLQuery(sql);
        query.executeUpdate();
    	
    	/*Insertion de la table Flotte*/   	 
	   	 for(DroneInt d:m.getFlotte()){
	   		 sql = "Insert into FLOTTE (MISSION_ID,DRONE_ID) values(" + m.getId()+","+d.getId()+")" ;
	         query.executeUpdate();
	   	 }     
    	
    }
    
    @Transactional
    private int getNewMissionId(){

        String sql = "Select max(MISSION_ID)+1 from MISSION";
        Query query = getSession().createSQLQuery(sql);

        @SuppressWarnings("unchecked")
		List listRes = query.list();
        
        return listRes == null || listRes.isEmpty() ? 0 : ((BigInteger)listRes.get(0)).intValue();
    }
    
}
