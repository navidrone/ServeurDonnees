/**
 * 
 */
package serveurDonnees.modele.bean;

import java.io.Serializable;

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
public class Drone implements Serializable, DroneInt {
	
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
