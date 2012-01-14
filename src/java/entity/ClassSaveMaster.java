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
@Table(name = "CLASS_SAVE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassSaveMaster.findAll", query = "SELECT c FROM ClassSaveMaster c"),
    @NamedQuery(name = "ClassSaveMaster.findByClassId", query = "SELECT c FROM ClassSaveMaster c WHERE c.classSaveMasterPK.classId = :classId"),
    @NamedQuery(name = "ClassSaveMaster.findBySaveId", query = "SELECT c FROM ClassSaveMaster c WHERE c.classSaveMasterPK.saveId = :saveId")})
public class ClassSaveMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClassSaveMasterPK classSaveMasterPK;
    @JoinColumn(name = "SAVE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SaveMaster saveMaster;
    @JoinColumn(name = "CLASS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ClassMaster classMaster;
    @JoinColumn(name = "RANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private BonusRankMaster rankId;

    public ClassSaveMaster() {
    }

    public ClassSaveMaster(ClassSaveMasterPK classSaveMasterPK) {
        this.classSaveMasterPK = classSaveMasterPK;
    }

    public ClassSaveMaster(int classId, int saveId) {
        this.classSaveMasterPK = new ClassSaveMasterPK(classId, saveId);
    }

    public ClassSaveMasterPK getClassSaveMasterPK() {
        return classSaveMasterPK;
    }

    public void setClassSaveMasterPK(ClassSaveMasterPK classSaveMasterPK) {
        this.classSaveMasterPK = classSaveMasterPK;
    }

    public SaveMaster getSaveMaster() {
        return saveMaster;
    }

    public void setSaveMaster(SaveMaster saveMaster) {
        this.saveMaster = saveMaster;
    }

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }

    public BonusRankMaster getRankId() {
        return rankId;
    }

    public void setRankId(BonusRankMaster rankId) {
        this.rankId = rankId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classSaveMasterPK != null ? classSaveMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassSaveMaster)) {
            return false;
        }
        ClassSaveMaster other = (ClassSaveMaster) object;
        if ((this.classSaveMasterPK == null && other.classSaveMasterPK != null) || (this.classSaveMasterPK != null && !this.classSaveMasterPK.equals(other.classSaveMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassSaveMaster[ classSaveMasterPK=" + classSaveMasterPK + " ]";
    }
    
}
