/**
 * 
 */
package serveurDonnees.modele.dao;

import java.rmi.RemoteException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import serveurDonnees.modele.bean.CoordGps;
import serveurDonnees.modele.bean.Drone;

/**
 * @author Jullien
 *
 */
public class DroneDAO extends NavidroneDAO {
	
    public DroneDAO() {
         super();
    }
     

    @Transactional
    public List<Drone> list() {
        @SuppressWarnings("unchecked")
        List<Drone> listDrone = (List<Drone>) getSession()
                .createCriteria(Drone.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listDrone;
    }

    @Transactional
    public void saveOrUpdate(Drone drone) {
    	if(drone.getId() != null){
        	getSession().saveOrUpdate(merge(drone));    		
    	}else{
        	getSession().saveOrUpdate(drone);    		
        }
    }
 

    @Transactional
    public void delete(int id) throws RemoteException {
        Drone droneToDelete = new Drone();
        droneToDelete.setId(id);
        getSession().delete(droneToDelete);
    }


    @Transactional
    public Drone get(int id) {
        String hql = "from Drone where id=" + id;
        Query query = getSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Drone> drones = (List<Drone>) query.list();
         
        if (drones != null && !drones.isEmpty()) {
            return drones.get(0);
        }
         
        return null;
    }
    
    @Transactional
    public  List<Drone> getByMission(int missionId) {
        String sql = "Select * from DRONE where DRONE_ID in (select DRONE_ID from FLOTTE where MISSION_ID=" + missionId +")";
        Query query = getSession().createSQLQuery(sql).addEntity(Drone.class);
        
        @SuppressWarnings("unchecked")
        List<Drone> drones = (List<Drone>) query.list();
       
        if(drones != null && !drones.isEmpty()){
        	System.out.println("Drones ok : "+drones.get(0).getName());
        }else{
        	System.out.println("LISTE DE DRONES VIDE !!!!!!!!!!!");
        }
        
        return drones;
    }
    
    @Transactional
    public Drone merge(Drone done){
    	return (Drone)getSession().merge(done);
    }
}