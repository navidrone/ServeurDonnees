package serveurDonnees.modele.dao;
 
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import serveurDonnees.modele.bean.Drone;
import serveurDonnees.modele.bean.Mission;
import serveurDonnees.modele.bean.Releve;
 
@Repository
public class MissionDAO extends NavidroneDAO {
 
    public MissionDAO() {
         
    }
     

    @Transactional
    public List<Mission> list() {
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
    	
    	if(mission.getId() == null){
    		mission.setId(getNewMissionId());
    	}
    	trace("ID de la mission : "+mission.getId());
    	
    	 getSession().saveOrUpdate(mission);
    	 
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
    public void delete(int id) {
        Mission missionToDelete = null;
		try {
			missionToDelete = new Mission();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        missionToDelete.setId(id);
        getSession().delete(missionToDelete);
    }


    @Transactional
    public Mission get(int id) {    	

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
    private Mission addReleve(Mission m)
    {
    	ReleveDAO releveDAO = new ReleveDAO();
    	
    	m.setReleve((ArrayList<Releve>)releveDAO.getByMission(m.getId()));
    	
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
    	String sql = "Delete from Flotte where MISSION_ID=" + m.getId() ;
        Query query = getSession().createSQLQuery(sql);
        query.executeUpdate();
    	
    	/*Insertion de la table Flotte*/   	 
	   	 for(Drone d:(ArrayList<Drone>)m.getFlotte()){
	   		 sql = "Insert into Flotte (MISSION_ID,DRONE_ID) values(" + m.getId()+","+d.getId()+")" ;
	         query.executeUpdate();
	   	 }     
    	
    }
    
    @Transactional
    private int getNewMissionId(){

        String sql = "Select max(MISSION_ID)+1 from Mission";
        Query query = getSession().createSQLQuery(sql);

        @SuppressWarnings("unchecked")
		List listRes = query.list();
        
        return listRes == null || listRes.isEmpty() ? 0 : ((BigInteger)listRes.get(0)).intValue();
    }
    
}
