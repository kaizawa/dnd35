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
@Table(name = "CHARACTER_SKILL_GROWTH_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CharacterSkillGrowthRecord.findAll", query = "SELECT c FROM CharacterSkillGrowthRecord c"),
    @NamedQuery(name = "CharacterSkillGrowthRecord.findByCharacterId", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.characterSkillGrowthRecordPK.characterId = :characterId"),
    @NamedQuery(name = "CharacterSkillGrowthRecord.findByCharacterLevel", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.characterSkillGrowthRecordPK.characterLevel = :characterLevel"),
    @NamedQuery(name = "CharacterSkillGrowthRecord.findBySkillId", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.characterSkillGrowthRecordPK.skillId = :skillId"),
    @NamedQuery(name = "CharacterSkillGrowthRecord.findBySkillPoint", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.skillPoint = :skillPoint"),
    @NamedQuery(name = "CharacterSkillGrowthRecord.findByDescription", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.description = :description")})
public class CharacterSkillGrowthRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharacterSkillGrowthRecordPK characterSkillGrowthRecordPK;
    @Column(name = "SKILL_POINT")
    private Integer skillPoint;
    @Size(max = 400)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SkillMaster skillMaster;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CharacterRecord characterRecord;

    public CharacterSkillGrowthRecord() {
    }

    public CharacterSkillGrowthRecord(CharacterSkillGrowthRecordPK characterSkillGrowthRecordPK) {
        this.characterSkillGrowthRecordPK = characterSkillGrowthRecordPK;
    }

    public CharacterSkillGrowthRecord(int characterId, int characterLevel, int skillId) {
        this.characterSkillGrowthRecordPK = new CharacterSkillGrowthRecordPK(characterId, characterLevel, skillId);
    }

    public CharacterSkillGrowthRecordPK getCharacterSkillGrowthRecordPK() {
        return characterSkillGrowthRecordPK;
    }

    public void setCharacterSkillGrowthRecordPK(CharacterSkillGrowthRecordPK characterSkillGrowthRecordPK) {
        this.characterSkillGrowthRecordPK = characterSkillGrowthRecordPK;
    }

    public Integer getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(Integer skillPoint) {
        this.skillPoint = skillPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SkillMaster getSkillMaster() {
        return skillMaster;
    }

    public void setSkillMaster(SkillMaster skillMaster) {
        this.skillMaster = skillMaster;
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
        hash += (characterSkillGrowthRecordPK != null ? characterSkillGrowthRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSkillGrowthRecord)) {
            return false;
        }
        CharacterSkillGrowthRecord other = (CharacterSkillGrowthRecord) object;
        if ((this.characterSkillGrowthRecordPK == null && other.characterSkillGrowthRecordPK != null) || (this.characterSkillGrowthRecordPK != null && !this.characterSkillGrowthRecordPK.equals(other.characterSkillGrowthRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSkillGrowthRecord[ characterSkillGrowthRecordPK=" + characterSkillGrowthRecordPK + " ]";
    }
    
}
