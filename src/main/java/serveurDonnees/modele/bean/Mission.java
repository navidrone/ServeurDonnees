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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import rmi.CoordGpsInt;
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
	
	public Mission(Integer id, String name, String type, CoordGps coord_dep,
			CoordGps coord_ar, Double periode, Double densite, Double portee)
			throws RemoteException {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.coord_dep = coord_dep;
		this.coord_ar = coord_ar;
		this.periode = periode;
		this.densite = densite;
		this.portee = portee;
	}



	@Id
    @GeneratedValue
    @Column(name = "MISSION_ID")
	private Integer id;
	
    @Column(name = "MISSION_NAME")
	private String name;

    @Column(name = "MISSION_TYPE")
	private String type;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "COORD_GPS_DEP")
	private CoordGps coord_dep;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "COORD_GPS_AR")
	private CoordGps coord_ar;
    
    @Column(name = "PERIODE")
	private Double periode;
    
    @Column(name = "DENSITE")
	private Double densite;
    
    @Column(name = "PORTEE")
	private Double portee;
    
    /** Attributs renseignés "à la main" dans la factory */
    @Transient
    private ArrayList<ReleveInt> releve;
    @Transient
    private ArrayList<DroneInt> flotte;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CoordGpsInt getCoord_dep() {
		return coord_dep;
	}

	public void setCoord_dep(CoordGpsInt coord_dep) {
		this.coord_dep = (CoordGps)coord_dep;
	}

	public CoordGpsInt getCoord_ar() {
		return coord_ar;
	}

	public void setCoord_ar(CoordGpsInt coord_ar) {
		this.coord_ar = (CoordGps)coord_ar;
	}

	public Double getPeriode() {
		return periode;
	}

	public void setPeriode(Double periode) {
		this.periode = periode;
	}

	public Double getDensite() {
		return densite;
	}

	public void setDensite(Double densite) {
		this.densite = densite;
	}

	public Double getPortee() {
		return portee;
	}

	public void setPortee(Double portee) {
		this.portee = portee;
	}

	public List<? extends ReleveInt> getReleve() {
		return releve;
	}

	public void setReleve(List<? extends ReleveInt> releve) {
		this.releve = (ArrayList<ReleveInt>)releve;
	}

	public List<? extends DroneInt> getFlotte() {
		return flotte;
	}

	public void setFlotte(List<? extends DroneInt> flotte) {
		this.flotte = (ArrayList<DroneInt>)flotte;
	}

	
}
