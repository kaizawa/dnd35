/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "RACE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RaceMaster.findAll", query = "SELECT r FROM RaceMaster r"),
    @NamedQuery(name = "RaceMaster.findById", query = "SELECT r FROM RaceMaster r WHERE r.id = :id"),
    @NamedQuery(name = "RaceMaster.findByRaceName", query = "SELECT r FROM RaceMaster r WHERE r.raceName = :raceName"),
    @NamedQuery(name = "RaceMaster.findBySpeed", query = "SELECT r FROM RaceMaster r WHERE r.speed = :speed"),
    @NamedQuery(name = "RaceMaster.findByDescription", query = "SELECT r FROM RaceMaster r WHERE r.description = :description")})
public class RaceMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "RACE_NAME")
    private String raceName;
    @Column(name = "SPEED")
    private Integer speed;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "SIZE_ID", referencedColumnName = "ID")
    @ManyToOne
    private SizeMaster sizeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceMaster")
    private Collection<RaceAbilityMaster> raceAbilityMasterCollection;
    @OneToMany(mappedBy = "raceId")
    private Collection<CharacterRecord> characterRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceMaster")
    private Collection<RaceSaveMaster> raceSaveMasterCollection;

    public RaceMaster() {
    }

    public RaceMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SizeMaster getSizeId() {
        return sizeId;
    }

    public void setSizeId(SizeMaster sizeId) {
        this.sizeId = sizeId;
    }

    @XmlTransient
    public Collection<RaceAbilityMaster> getRaceAbilityMasterCollection() {
        return raceAbilityMasterCollection;
    }

    public void setRaceAbilityMasterCollection(Collection<RaceAbilityMaster> raceAbilityMasterCollection) {
        this.raceAbilityMasterCollection = raceAbilityMasterCollection;
    }

    @XmlTransient
    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
    }

    @XmlTransient
    public Collection<RaceSaveMaster> getRaceSaveMasterCollection() {
        return raceSaveMasterCollection;
    }

    public void setRaceSaveMasterCollection(Collection<RaceSaveMaster> raceSaveMasterCollection) {
        this.raceSaveMasterCollection = raceSaveMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceMaster)) {
            return false;
        }
        RaceMaster other = (RaceMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceMaster[ id=" + id + " ]";
    }
    
}
