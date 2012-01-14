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
@Table(name = "CHARACTER_SAVE_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CharacterSaveRecord.findAll", query = "SELECT c FROM CharacterSaveRecord c"),
    @NamedQuery(name = "CharacterSaveRecord.findByCharacterId", query = "SELECT c FROM CharacterSaveRecord c WHERE c.characterSaveRecordPK.characterId = :characterId"),
    @NamedQuery(name = "CharacterSaveRecord.findBySaveId", query = "SELECT c FROM CharacterSaveRecord c WHERE c.characterSaveRecordPK.saveId = :saveId"),
    @NamedQuery(name = "CharacterSaveRecord.findByMiscModifier", query = "SELECT c FROM CharacterSaveRecord c WHERE c.miscModifier = :miscModifier"),
    @NamedQuery(name = "CharacterSaveRecord.findByDescription", query = "SELECT c FROM CharacterSaveRecord c WHERE c.description = :description")})
public class CharacterSaveRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharacterSaveRecordPK characterSaveRecordPK;
    @Column(name = "MISC_MODIFIER")
    private Integer miscModifier;
    @Size(max = 400)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "SAVE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SaveMaster saveMaster;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CharacterRecord characterRecord;

    public CharacterSaveRecord() {
    }

    public CharacterSaveRecord(CharacterSaveRecordPK characterSaveRecordPK) {
        this.characterSaveRecordPK = characterSaveRecordPK;
    }

    public CharacterSaveRecord(int characterId, int saveId) {
        this.characterSaveRecordPK = new CharacterSaveRecordPK(characterId, saveId);
    }

    public CharacterSaveRecordPK getCharacterSaveRecordPK() {
        return characterSaveRecordPK;
    }

    public void setCharacterSaveRecordPK(CharacterSaveRecordPK characterSaveRecordPK) {
        this.characterSaveRecordPK = characterSaveRecordPK;
    }

    public Integer getMiscModifier() {
        return miscModifier;
    }

    public void setMiscModifier(Integer miscModifier) {
        this.miscModifier = miscModifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SaveMaster getSaveMaster() {
        return saveMaster;
    }

    public void setSaveMaster(SaveMaster saveMaster) {
        this.saveMaster = saveMaster;
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
        hash += (characterSaveRecordPK != null ? characterSaveRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSaveRecord)) {
            return false;
        }
        CharacterSaveRecord other = (CharacterSaveRecord) object;
        if ((this.characterSaveRecordPK == null && other.characterSaveRecordPK != null) || (this.characterSaveRecordPK != null && !this.characterSaveRecordPK.equals(other.characterSaveRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSaveRecord[ characterSaveRecordPK=" + characterSaveRecordPK + " ]";
    }
    
}
