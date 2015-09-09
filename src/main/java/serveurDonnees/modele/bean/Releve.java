/**
 * 
 */
package serveurDonnees.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import rmi.CoordGpsInt;
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
	
	
	
	
	
	
}
