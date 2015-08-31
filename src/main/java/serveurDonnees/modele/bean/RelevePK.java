/**
 * 
 */
package serveurDonnees.modele.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Jullien
 *
 */
@Embeddable
public class RelevePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	

    @Column(name = "MISSION_ID")
    private Integer missionID;
    
    @Column(name = "COORD_GPS_ID")
    private Integer coordGpsID;

    public RelevePK() {}

    public RelevePK(Integer missionID, Integer coordGpsID) {
        this.missionID = missionID;
        this.coordGpsID = coordGpsID;
    }

	public Integer getMissionID() {
		return missionID;
	}

	public void setMissionID(Integer missionID) {
		this.missionID = missionID;
	}

	public Integer getCoordGpsID() {
		return coordGpsID;
	}

	public void setCoordGpsID(Integer coordGpsID) {
		this.coordGpsID = coordGpsID;
	}
    
    
	
}
