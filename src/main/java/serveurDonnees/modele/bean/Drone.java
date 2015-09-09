/**
 * 
 */
package serveurDonnees.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import rmi.DroneInt;

/**
 * @author Jullien
 *
 */

@Entity
@Table(name = "DRONE")
public class Drone extends UnicastRemoteObject  implements Serializable, DroneInt {
	
	public Drone() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue
    @Column(name = "DRONE_ID")
	private Integer id;

    @Column(name = "DRONE_NAME")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
