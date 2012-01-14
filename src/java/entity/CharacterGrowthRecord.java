/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "CHARACTER_GROWTH_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CharacterGrowthRecord.findAll", query = "SELECT c FROM CharacterGrowthRecord c"),
    @NamedQuery(name = "CharacterGrowthRecord.findByCharacterId", query = "SELECT c FROM CharacterGrowthRecord c WHERE c.characterGrowthRecordPK.characterId = :characterId"),
    @NamedQuery(name = "CharacterGrowthRecord.findByCharacterLevel", query = "SELECT c FROM CharacterGrowthRecord c WHERE c.characterGrowthRecordPK.characterLevel = :characterLevel"),
    @NamedQuery(name = "CharacterGrowthRecord.findByAbilityEnhancement", query = "SELECT c FROM CharacterGrowthRecord c WHERE c.abilityEnhancement = :abilityEnhancement"),
    @NamedQuery(name = "CharacterGrowthRecord.findByHitPoint", query = "SELECT c FROM CharacterGrowthRecord c WHERE c.hitPoint = :hitPoint"),
    @NamedQuery(name = "CharacterGrowthRecord.findByUpdateDescription", query = "SELECT c FROM CharacterGrowthRecord c WHERE c.updateDescription = :updateDescription")})
public class CharacterGrowthRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharacterGrowthRecordPK characterGrowthRecordPK;
    @Column(name = "ABILITY_ENHANCEMENT")
    private Integer abilityEnhancement;
    @Column(name = "HIT_POINT")
    private Integer hitPoint;
    @Size(max = 4000)
    @Column(name = "UPDATE_DESCRIPTION")
    private String updateDescription;
    @JoinColumn(name = "CLASS_ID", referencedColumnName = "ID")
    @ManyToOne
    private ClassMaster classId;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CharacterRecord characterRecord;

    public CharacterGrowthRecord() {
    }

    public CharacterGrowthRecord(CharacterGrowthRecordPK characterGrowthRecordPK) {
        this.characterGrowthRecordPK = characterGrowthRecordPK;
    }

    public CharacterGrowthRecord(int characterId, int characterLevel) {
        this.characterGrowthRecordPK = new CharacterGrowthRecordPK(characterId, characterLevel);
    }

    public CharacterGrowthRecordPK getCharacterGrowthRecordPK() {
        return characterGrowthRecordPK;
    }

    public void setCharacterGrowthRecordPK(CharacterGrowthRecordPK characterGrowthRecordPK) {
        this.characterGrowthRecordPK = characterGrowthRecordPK;
    }

    public Integer getAbilityEnhancement() {
        return abilityEnhancement;
    }

    public void setAbilityEnhancement(Integer abilityEnhancement) {
        this.abilityEnhancement = abilityEnhancement;
    }

    public Integer getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(Integer hitPoint) {
        this.hitPoint = hitPoint;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public ClassMaster getClassId() {
        return classId;
    }

    public void setClassId(ClassMaster classId) {
        this.classId = classId;
    }

    public CharacterRecord getCharacterRecord() {
        return characterRecord;
    }

    public void setCharacterRecord(CharacterRecord characterRecord) {
        this.characterRecord = characterRecord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (characterGrowthRecordPK != null ? characterGrowthRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterGrowthRecord)) {
            return false;
        }
        CharacterGrowthRecord other = (CharacterGrowthRecord) object;
        if ((this.characterGrowthRecordPK == null && other.characterGrowthRecordPK != null) || (this.characterGrowthRecordPK != null && !this.characterGrowthRecordPK.equals(other.characterGrowthRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterGrowthRecord[ characterGrowthRecordPK=" + characterGrowthRecordPK + " ]";
    }
    
}
