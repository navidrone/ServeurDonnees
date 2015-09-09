package serveurDonnees.modele.dao;
 
import java.rmi.RemoteException;
import java.util.List;
 


import serveurDonnees.modele.bean.CoordGps;
 


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class CoordGpsDAO extends NavidroneDAO {
	
	
    public CoordGpsDAO() {
         
    }


    @Transactional
    public List<CoordGps> list() {
        @SuppressWarnings("unchecked")
        List<CoordGps> listCoordGps = (List<CoordGps>) getSession()
                .createCriteria(CoordGps.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listCoordGps;
    }

    @Transactional
    public void saveOrUpdate(CoordGps coordGps) {
        getSession().saveOrUpdate(coordGps);
    }
 

    @Transactional
    public void delete(int id) throws RemoteException {
    	CoordGps coordGpsToDelete = new CoordGps();
    	coordGpsToDelete.setId(id);
        getSession().delete(coordGpsToDelete);
    }


    @Transactional
    public CoordGps get(int id) {
        String hql = "from CoordGps where id=" + id;
        Query query = getSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<CoordGps> coordGps = (List<CoordGps>) query.list();
         
        if (coordGps != null && !coordGps.isEmpty()) {
            return coordGps.get(0);
        }
         
        return null;
    }
}
