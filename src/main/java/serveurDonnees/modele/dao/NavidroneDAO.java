/**
 * 
 */
package serveurDonnees.modele.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import serveurDonnees.modele.bean.CoordGps;
import serveurDonnees.modele.bean.Mission;
import serveurDonnees.modele.bean.Utilisateur;

/**
 * @author Jullien
 *
 */
public class NavidroneDAO {
	
	public NavidroneDAO() {
		
		if(sessionFactory == null ){

			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());
		 
			sessionBuilder.addAnnotatedClasses(Utilisateur.class);
			sessionBuilder.addAnnotatedClasses(CoordGps.class);
			sessionBuilder.addAnnotatedClasses(Mission.class);
			
			sessionFactory = sessionBuilder.buildSessionFactory();
		}
		
	}

    protected static SessionFactory sessionFactory;
    
	private DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/navidrone");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
	 
		return dataSource;
	}
	
	

	
	
}
