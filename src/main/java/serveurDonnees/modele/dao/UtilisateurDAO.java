package serveurDonnees.modele.dao;
 
import java.util.List;
 

import serveurDonnees.modele.bean.Utilisateur;
 

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class UtilisateurDAO extends NavidroneDAO {
	
    public UtilisateurDAO() {
         super();
    }
     

    @Transactional
    public List<Utilisateur> list() {
        @SuppressWarnings("unchecked")
        List<Utilisateur> listUtilisateur = (List<Utilisateur>) getSession()
                .createCriteria(Utilisateur.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUtilisateur;
    }

    @Transactional
    public void saveOrUpdate(Utilisateur user) {
    	getSession().saveOrUpdate(user);
    }
 

    @Transactional
    public void delete(int id) {
        Utilisateur userToDelete = new Utilisateur();
        userToDelete.setId(id);
        getSession().delete(userToDelete);
    }


    @Transactional
    public Utilisateur get(int id) {
        String hql = "from Utilisateur where id=" + id;
        Query query = getSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Utilisateur> users = (List<Utilisateur>) query.list();
         
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
         
        return null;
    }
}
