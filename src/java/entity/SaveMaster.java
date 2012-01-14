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
@Table(name = "SAVE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaveMaster.findAll", query = "SELECT s FROM SaveMaster s"),
    @NamedQuery(name = "SaveMaster.findById", query = "SELECT s FROM SaveMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SaveMaster.findBySaveName", query = "SELECT s FROM SaveMaster s WHERE s.saveName = :saveName")})
public class SaveMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "SAVE_NAME")
    private String saveName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saveMaster")
    private Collection<CharacterSaveRecord> characterSaveRecordCollection;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID")
    @ManyToOne
    private AbilityMaster abilityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saveMaster")
    private Collection<ClassSaveMaster> classSaveMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saveMaster")
    private Collection<RaceSaveMaster> raceSaveMasterCollection;

    public SaveMaster() {
    }

    public SaveMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    @XmlTransient
    public Collection<CharacterSaveRecord> getCharacterSaveRecordCollection() {
        return characterSaveRecordCollection;
    }

    public void setCharacterSaveRecordCollection(Collection<CharacterSaveRecord> characterSaveRecordCollection) {
        this.characterSaveRecordCollection = characterSaveRecordCollection;
    }

    public AbilityMaster getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(AbilityMaster abilityId) {
        this.abilityId = abilityId;
    }

    @XmlTransient
    public Collection<ClassSaveMaster> getClassSaveMasterCollection() {
        return classSaveMasterCollection;
    }

    public void setClassSaveMasterCollection(Collection<ClassSaveMaster> classSaveMasterCollection) {
        this.classSaveMasterCollection = classSaveMasterCollection;
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
        if (!(object instanceof SaveMaster)) {
            return false;
        }
        SaveMaster other = (SaveMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SaveMaster[ id=" + id + " ]";
    }
    
}
