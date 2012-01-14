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
public class CharacterSkillRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHARACTER_ID")
    private int characterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SKILL_ID")
    private int skillId;

    public CharacterSkillRecordPK() {
    }

    public CharacterSkillRecordPK(int characterId, int skillId) {
        this.characterId = characterId;
        this.skillId = skillId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) skillId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSkillRecordPK)) {
            return false;
        }
        CharacterSkillRecordPK other = (CharacterSkillRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.skillId != other.skillId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSkillRecordPK[ characterId=" + characterId + ", skillId=" + skillId + " ]";
    }
    
}
