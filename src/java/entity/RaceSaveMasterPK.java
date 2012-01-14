/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kaizawa
 */
@Embeddable
public class RaceSaveMasterPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "RACE_ID")
    private int raceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAVE_ID")
    private int saveId;

    public RaceSaveMasterPK() {
    }

    public RaceSaveMasterPK(int raceId, int saveId) {
        this.raceId = raceId;
        this.saveId = saveId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getSaveId() {
        return saveId;
    }

    public void setSaveId(int saveId) {
        this.saveId = saveId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) raceId;
        hash += (int) saveId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceSaveMasterPK)) {
            return false;
        }
        RaceSaveMasterPK other = (RaceSaveMasterPK) object;
        if (this.raceId != other.raceId) {
            return false;
        }
        if (this.saveId != other.saveId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceSaveMasterPK[ raceId=" + raceId + ", saveId=" + saveId + " ]";
    }
    
}
