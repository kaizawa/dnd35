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
@Table(name = "ABILITY_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AbilityMaster.findAll", query = "SELECT a FROM AbilityMaster a"),
    @NamedQuery(name = "AbilityMaster.findById", query = "SELECT a FROM AbilityMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AbilityMaster.findByAbilityName", query = "SELECT a FROM AbilityMaster a WHERE a.abilityName = :abilityName")})
public class AbilityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "ABILITY_NAME")
    private String abilityName;
    @OneToMany(mappedBy = "abilityId")
    private Collection<SkillMaster> skillMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private Collection<CharacterAbilityRecord> characterAbilityRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private Collection<RaceAbilityMaster> raceAbilityMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private Collection<ClassAbilityMaster> classAbilityMasterCollection;
    @OneToMany(mappedBy = "abilityId")
    private Collection<SaveMaster> saveMasterCollection;

    public AbilityMaster() {
    }

    public AbilityMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    @XmlTransient
    public Collection<SkillMaster> getSkillMasterCollection() {
        return skillMasterCollection;
    }

    public void setSkillMasterCollection(Collection<SkillMaster> skillMasterCollection) {
        this.skillMasterCollection = skillMasterCollection;
    }

    @XmlTransient
    public Collection<CharacterAbilityRecord> getCharacterAbilityRecordCollection() {
        return characterAbilityRecordCollection;
    }

    public void setCharacterAbilityRecordCollection(Collection<CharacterAbilityRecord> characterAbilityRecordCollection) {
        this.characterAbilityRecordCollection = characterAbilityRecordCollection;
    }

    @XmlTransient
    public Collection<RaceAbilityMaster> getRaceAbilityMasterCollection() {
        return raceAbilityMasterCollection;
    }

    public void setRaceAbilityMasterCollection(Collection<RaceAbilityMaster> raceAbilityMasterCollection) {
        this.raceAbilityMasterCollection = raceAbilityMasterCollection;
    }

    @XmlTransient
    public Collection<ClassAbilityMaster> getClassAbilityMasterCollection() {
        return classAbilityMasterCollection;
    }

    public void setClassAbilityMasterCollection(Collection<ClassAbilityMaster> classAbilityMasterCollection) {
        this.classAbilityMasterCollection = classAbilityMasterCollection;
    }

    @XmlTransient
    public Collection<SaveMaster> getSaveMasterCollection() {
        return saveMasterCollection;
    }

    public void setSaveMasterCollection(Collection<SaveMaster> saveMasterCollection) {
        this.saveMasterCollection = saveMasterCollection;
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
        if (!(object instanceof AbilityMaster)) {
            return false;
        }
        AbilityMaster other = (AbilityMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AbilityMaster[ id=" + id + " ]";
    }
    
}
