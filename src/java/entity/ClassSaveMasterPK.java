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
public class ClassSaveMasterPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_ID")
    private int classId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAVE_ID")
    private int saveId;

    public ClassSaveMasterPK() {
    }

    public ClassSaveMasterPK(int classId, int saveId) {
        this.classId = classId;
        this.saveId = saveId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
        hash += (int) classId;
        hash += (int) saveId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassSaveMasterPK)) {
            return false;
        }
        ClassSaveMasterPK other = (ClassSaveMasterPK) object;
        if (this.classId != other.classId) {
            return false;
        }
        if (this.saveId != other.saveId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassSaveMasterPK[ classId=" + classId + ", saveId=" + saveId + " ]";
    }
    
}
