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
public class RaceAbilityMasterPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "RACE_ID")
    private int raceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITY_ID")
    private int abilityId;

    public RaceAbilityMasterPK() {
    }

    public RaceAbilityMasterPK(int raceId, int abilityId) {
        this.raceId = raceId;
        this.abilityId = abilityId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(int abilityId) {
        this.abilityId = abilityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) raceId;
        hash += (int) abilityId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceAbilityMasterPK)) {
            return false;
        }
        RaceAbilityMasterPK other = (RaceAbilityMasterPK) object;
        if (this.raceId != other.raceId) {
            return false;
        }
        if (this.abilityId != other.abilityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceAbilityMasterPK[ raceId=" + raceId + ", abilityId=" + abilityId + " ]";
    }
    
}
