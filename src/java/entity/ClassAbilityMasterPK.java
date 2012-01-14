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
public class ClassAbilityMasterPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_ID")
    private int classId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITY_ID")
    private int abilityId;

    public ClassAbilityMasterPK() {
    }

    public ClassAbilityMasterPK(int classId, int abilityId) {
        this.classId = classId;
        this.abilityId = abilityId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
        hash += (int) classId;
        hash += (int) abilityId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassAbilityMasterPK)) {
            return false;
        }
        ClassAbilityMasterPK other = (ClassAbilityMasterPK) object;
        if (this.classId != other.classId) {
            return false;
        }
        if (this.abilityId != other.abilityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassAbilityMasterPK[ classId=" + classId + ", abilityId=" + abilityId + " ]";
    }
    
}
