package serveurcomm.modele.dao;
 
import java.rmi.RemoteException;
import java.util.List;
 

import serveurcomm.modele.bean.Mission;
 

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class MissionDAO {
    @Autowired
    private SessionFactory sessionFactory;
 
    public MissionDAO() {
         
    }
     
    public MissionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Mission> list() {
        @SuppressWarnings("unchecked")
        List<Mission> listMission = (List<Mission>) sessionFactory.getCurrentSession()
                .createCriteria(Mission.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listMission;
    }

    @Transactional
    public void saveOrUpdate(Mission mission) {
        sessionFactory.getCurrentSession().saveOrUpdate(mission);
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
        sessionFactory.getCurrentSession().delete(missionToDelete);
    }


    @Transactional
    public Mission get(int id) {
        String hql = "from Mission where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Mission> missions = (List<Mission>) query.list();
         
        if (missions != null && !missions.isEmpty()) {
            return missions.get(0);
        }
         
        return null;
    }
}
