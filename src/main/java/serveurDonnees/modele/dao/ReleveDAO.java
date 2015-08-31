package serveurDonnees.modele.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import serveurDonnees.modele.bean.Releve;

public class ReleveDAO  extends NavidroneDAO {
	
	
    public ReleveDAO() {
         
    }


    @Transactional
    public List<Releve> list() {
        @SuppressWarnings("unchecked")
        List<Releve> listReleve = (List<Releve>) getSession()
                .createCriteria(Releve.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listReleve;
    }

    @Transactional
    public void saveOrUpdate(Releve releve) {
        getSession().saveOrUpdate(releve);
        CoordGpsDAO coordGpsDAO = new CoordGpsDAO();
        coordGpsDAO.saveOrUpdate(releve.getCoordGps());
    }
 

    @Transactional
    public void delete(int idMission, int idCoordGps) {
        String hql = "delete Releve where MISSION_ID=" + idMission+" and COORD_GPS_ID="+idCoordGps;
    	Query q =  getSession().createQuery(hql);
    	q.executeUpdate();
    }


    @Transactional
    public Releve get(int idMission, int idCoordGps) {
        String hql = "from Releve where MISSION_ID=" + idMission+" and COORD_GPS_ID="+idCoordGps;
        Query query = getSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Releve> releve = (List<Releve>) query.list();
         
        if (releve != null && !releve.isEmpty()) {
            return releve.get(0);
        }
         
        return null;
    }
    
    @Transactional
    public  List<Releve> getByMission(int idMission) {
        String hql = "from Releve where MISSION_ID=" + idMission;
        Query query = getSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Releve> releve = (List<Releve>) query.list();
        
        CoordGpsDAO coordGpsDAO = new CoordGpsDAO();
        
        for(Releve r:releve){
            
            if (r != null) {
            	r.setCoordGps(coordGpsDAO.get(r.getRelevePk().getCoordGpsID()));
            }
        	
        }
         
        return releve;
    }
}