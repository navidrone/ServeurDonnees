package serveurcomm.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MISSION")
public class Mission extends UnicastRemoteObject implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Mission() throws RemoteException {
		super();
	}

	@Id
    @GeneratedValue
    @Column(name = "MISSION_ID")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}