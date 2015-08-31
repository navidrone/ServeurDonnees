package serveurDonnees.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import rmi.DroneInt;
import rmi.MissionInt;
import rmi.ReleveInt;

@Entity
@Table(name = "MISSION")
public class Mission extends UnicastRemoteObject implements Serializable,MissionInt{


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
	
    @Column(name = "MISSION_NAME")
	private String name;
    
    /** Attributs renseignés "à la main" dans la factory */
    @Transient
    private ArrayList<Releve> releve;
    @Transient
    private ArrayList<Drone> flotte;

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

	

	public List<? extends ReleveInt> getReleve() {
		return releve;
	}

	public void setReleve(List<? extends ReleveInt> releve) {
		this.releve = (ArrayList<Releve>)releve;
	}

	public List<? extends DroneInt> getFlotte() {
		return flotte;
	}

	public void setFlotte(List<? extends DroneInt> flotte) {
		this.flotte = (ArrayList<Drone>)flotte;
	}


	

	
}
