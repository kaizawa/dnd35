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
@Table(name = "RACE_SAVE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RaceSaveMaster.findAll", query = "SELECT r FROM RaceSaveMaster r"),
    @NamedQuery(name = "RaceSaveMaster.findByRaceId", query = "SELECT r FROM RaceSaveMaster r WHERE r.raceSaveMasterPK.raceId = :raceId"),
    @NamedQuery(name = "RaceSaveMaster.findBySaveId", query = "SELECT r FROM RaceSaveMaster r WHERE r.raceSaveMasterPK.saveId = :saveId"),
    @NamedQuery(name = "RaceSaveMaster.findByModifier", query = "SELECT r FROM RaceSaveMaster r WHERE r.modifier = :modifier")})
public class RaceSaveMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RaceSaveMasterPK raceSaveMasterPK;
    @Column(name = "MODIFIER")
    private Integer modifier;
    @JoinColumn(name = "SAVE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SaveMaster saveMaster;
    @JoinColumn(name = "RACE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RaceMaster raceMaster;

    public RaceSaveMaster() {
    }

    public RaceSaveMaster(RaceSaveMasterPK raceSaveMasterPK) {
        this.raceSaveMasterPK = raceSaveMasterPK;
    }

    public RaceSaveMaster(int raceId, int saveId) {
        this.raceSaveMasterPK = new RaceSaveMasterPK(raceId, saveId);
    }

    public RaceSaveMasterPK getRaceSaveMasterPK() {
        return raceSaveMasterPK;
    }

    public void setRaceSaveMasterPK(RaceSaveMasterPK raceSaveMasterPK) {
        this.raceSaveMasterPK = raceSaveMasterPK;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public SaveMaster getSaveMaster() {
        return saveMaster;
    }

    public void setSaveMaster(SaveMaster saveMaster) {
        this.saveMaster = saveMaster;
    }

    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raceSaveMasterPK != null ? raceSaveMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceSaveMaster)) {
            return false;
        }
        RaceSaveMaster other = (RaceSaveMaster) object;
        if ((this.raceSaveMasterPK == null && other.raceSaveMasterPK != null) || (this.raceSaveMasterPK != null && !this.raceSaveMasterPK.equals(other.raceSaveMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceSaveMaster[ raceSaveMasterPK=" + raceSaveMasterPK + " ]";
    }
    
}
