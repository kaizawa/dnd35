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
public class CharacterSaveRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHARACTER_ID")
    private int characterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAVE_ID")
    private int saveId;

    public CharacterSaveRecordPK() {
    }

    public CharacterSaveRecordPK(int characterId, int saveId) {
        this.characterId = characterId;
        this.saveId = saveId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getSaveId() {
        return saveId;
    }

    public void setSaveId(int saveId) {
        this.saveId = saveId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) saveId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSaveRecordPK)) {
            return false;
        }
        CharacterSaveRecordPK other = (CharacterSaveRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.saveId != other.saveId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSaveRecordPK[ characterId=" + characterId + ", saveId=" + saveId + " ]";
    }
    
}
