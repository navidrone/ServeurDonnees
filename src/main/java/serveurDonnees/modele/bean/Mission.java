package serveurDonnees.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import rmi.MissionInt;

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
	private Integer name;
    
    @CollectionTable(name="RELEVE",
            		 joinColumns=@JoinColumn(name="MISSION_ID")
    		  )
    private ArrayList<CoordGps> waypoints;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public ArrayList<CoordGps> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(ArrayList<CoordGps> waypoints) {
		this.waypoints = waypoints;
	}

	
}
