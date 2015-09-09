package serveurDonnees.modele.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import rmi.CoordGpsInt;

@Entity
@Table(name = "COORD_GPS")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // pour l'héritage...
public class CoordGps extends UnicastRemoteObject  implements Serializable, CoordGpsInt{

	public CoordGps() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CoordGps(Integer id, Double lattitude, Double longitude)
			throws RemoteException {
		super();
		this.id = id;
		this.lattitude = lattitude;
		this.longitude = longitude;
	}





	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name = "COORD_GPS_ID")
	private Integer id;
    @Column(name = "COORD_GPS_LATTITUDE")
	private Double lattitude;
    @Column(name = "COORD_GPS_LONGITUDE")
	private Double longitude;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}