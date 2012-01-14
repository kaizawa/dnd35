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
public class CharacterAbilityRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHARACTER_ID")
    private int characterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITY_ID")
    private int abilityId;

    public CharacterAbilityRecordPK() {
    }

    public CharacterAbilityRecordPK(int characterId, int abilityId) {
        this.characterId = characterId;
        this.abilityId = abilityId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
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
        hash += (int) characterId;
        hash += (int) abilityId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterAbilityRecordPK)) {
            return false;
        }
        CharacterAbilityRecordPK other = (CharacterAbilityRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.abilityId != other.abilityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterAbilityRecordPK[ characterId=" + characterId + ", abilityId=" + abilityId + " ]";
    }
    
}
