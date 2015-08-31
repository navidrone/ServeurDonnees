/**
 * 
 */
package serveurDonnees.modele.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * Classe de connexion à la base
 * Utilise les informations dans le fichier hibernate.cfg.xml
 * 
 * @author Jullien
 *
 */
@EnableTransactionManagement
public class NavidroneDAO {
	
	private boolean verbeux = true;
	
    protected static SessionFactory sessionFactory;
    private static Session session ;
    
    
	static {
		
		if(sessionFactory == null ){
			
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
		
	        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}
	}

	/** La gestion d'un DataSource en java ne semble pas marcher, en XML ça passe*/
	
//	private static DataSource getDataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/navidrone");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//	 
//		return dataSource;
//	}	

	protected Session getSession(){
		
		 if(session == null || !session.isOpen())
	        {
	        	 session = sessionFactory.openSession();
	        }
		
		return session;
	}
	
	
	protected void trace(String trace){
		
		if(verbeux){
			System.out.println(trace);
		}
		
	}
	
}
