/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "RACE_ABILITY_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RaceAbilityMaster.findAll", query = "SELECT r FROM RaceAbilityMaster r"),
    @NamedQuery(name = "RaceAbilityMaster.findByRaceId", query = "SELECT r FROM RaceAbilityMaster r WHERE r.raceAbilityMasterPK.raceId = :raceId"),
    @NamedQuery(name = "RaceAbilityMaster.findByAbilityId", query = "SELECT r FROM RaceAbilityMaster r WHERE r.raceAbilityMasterPK.abilityId = :abilityId"),
    @NamedQuery(name = "RaceAbilityMaster.findByModifier", query = "SELECT r FROM RaceAbilityMaster r WHERE r.modifier = :modifier")})
public class RaceAbilityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RaceAbilityMasterPK raceAbilityMasterPK;
    @Column(name = "MODIFIER")
    private Integer modifier;
    @JoinColumn(name = "RACE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RaceMaster raceMaster;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AbilityMaster abilityMaster;

    public RaceAbilityMaster() {
    }

    public RaceAbilityMaster(RaceAbilityMasterPK raceAbilityMasterPK) {
        this.raceAbilityMasterPK = raceAbilityMasterPK;
    }

    public RaceAbilityMaster(int raceId, int abilityId) {
        this.raceAbilityMasterPK = new RaceAbilityMasterPK(raceId, abilityId);
    }

    public RaceAbilityMasterPK getRaceAbilityMasterPK() {
        return raceAbilityMasterPK;
    }

    public void setRaceAbilityMasterPK(RaceAbilityMasterPK raceAbilityMasterPK) {
        this.raceAbilityMasterPK = raceAbilityMasterPK;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }

    public AbilityMaster getAbilityMaster() {
        return abilityMaster;
    }

    public void setAbilityMaster(AbilityMaster abilityMaster) {
        this.abilityMaster = abilityMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raceAbilityMasterPK != null ? raceAbilityMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceAbilityMaster)) {
            return false;
        }
        RaceAbilityMaster other = (RaceAbilityMaster) object;
        if ((this.raceAbilityMasterPK == null && other.raceAbilityMasterPK != null) || (this.raceAbilityMasterPK != null && !this.raceAbilityMasterPK.equals(other.raceAbilityMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceAbilityMaster[ raceAbilityMasterPK=" + raceAbilityMasterPK + " ]";
    }
    
}
