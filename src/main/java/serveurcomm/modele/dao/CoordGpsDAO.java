package serveurcomm.modele.dao;
 
import java.util.List;
 
import serveurcomm.modele.bean.CoordGps;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class CoordGpsDAO {
    @Autowired
    private SessionFactory sessionFactory;
 
    public CoordGpsDAO() {
         
    }
     
    public CoordGpsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<CoordGps> list() {
        @SuppressWarnings("unchecked")
        List<CoordGps> listCoordGps = (List<CoordGps>) sessionFactory.getCurrentSession()
                .createCriteria(CoordGps.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listCoordGps;
    }

    @Transactional
    public void saveOrUpdate(CoordGps coordGps) {
        sessionFactory.getCurrentSession().saveOrUpdate(coordGps);
    }
 

    @Transactional
    public void delete(int id) {
    	CoordGps coordGpsToDelete = new CoordGps();
    	coordGpsToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(coordGpsToDelete);
    }


    @Transactional
    public CoordGps get(int id) {
        String hql = "from CoordGps where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<CoordGps> coordGps = (List<CoordGps>) query.list();
         
        if (coordGps != null && !coordGps.isEmpty()) {
            return coordGps.get(0);
        }
         
        return null;
    }
}
