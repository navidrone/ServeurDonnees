/**
 * 
 */
package serveurDonnees.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import rmi.CoordGpsInt;
import rmi.MissionInt;
import rmi.ReleveInt;

/**
 * @author Jullien
 *
 */

@Entity
@Table(name = "RELEVE")
public class Releve extends UnicastRemoteObject  implements Serializable, ReleveInt {

	public Releve() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Releve(ReleveInt releveInt, MissionInt missionInt) throws RemoteException {
		super();
		CoordGpsInt coordGpsInt = releveInt.getCoordGps();
		this.setDateReleve(releveInt.getDateReleve());
		this.setProfondeur(profondeur);
		this.setCoordGps(new CoordGps(coordGpsInt.getId(),
									coordGpsInt.getLattitude(),
									coordGpsInt.getLongitude()));	
		this.setRelevePk(new RelevePK(missionInt.getId(), coordGpsInt.getId()));
	}

	private static final long serialVersionUID = 1L;	

	@EmbeddedId
	private RelevePK relevePk;
	
	@Transient
	private CoordGps coordGps;

    @Column(name = "PROFONDEUR")
	private Double profondeur;

    @Column(name = "DATE")
	private Date dateReleve;
	

	public Double getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(Double profondeur) {
		this.profondeur = profondeur;
	}

	public Date getDateReleve() {
		return dateReleve;
	}

	public void setDateReleve(Date dateReleve) {
		this.dateReleve = dateReleve;
	}

	public RelevePK getRelevePk() {
		return relevePk;
	}

	public void setRelevePk(RelevePK relevePk) {
		this.relevePk = relevePk;
	}

	public CoordGps getCoordGps() {
		return coordGps;
	}

	public void setCoordGps(CoordGpsInt coordGps) {
		this.coordGps = (CoordGps)coordGps;
	}

	@Override
	public Integer getId() throws RemoteException {
		return coordGps.getId();
	}

	@Override
	public void setId(Integer id) throws RemoteException {
		coordGps.setId(id);	
	}

	@Override
	public Double getLattitude() throws RemoteException {
		return coordGps.getLattitude();
	}

	@Override
	public void setLattitude(Double lattitude) throws RemoteException {
		coordGps.setLattitude(lattitude);
		
	}

	@Override
	public Double getLongitude() throws RemoteException {
		return coordGps.getLongitude();
	}

	@Override
	public void setLongitude(Double longitude) throws RemoteException {
		coordGps.setLongitude(longitude);
		
	}
	
	
	
	
	
	
}
