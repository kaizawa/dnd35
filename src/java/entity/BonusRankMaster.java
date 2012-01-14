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
@Table(name = "BONUS_RANK_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonusRankMaster.findAll", query = "SELECT b FROM BonusRankMaster b"),
    @NamedQuery(name = "BonusRankMaster.findById", query = "SELECT b FROM BonusRankMaster b WHERE b.id = :id"),
    @NamedQuery(name = "BonusRankMaster.findByRankName", query = "SELECT b FROM BonusRankMaster b WHERE b.rankName = :rankName")})
public class BonusRankMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "RANK_NAME")
    private String rankName;
    @OneToMany(mappedBy = "baseAttackRankId")
    private Collection<ClassMaster> classMasterCollection;
    @OneToMany(mappedBy = "rankId")
    private Collection<ClassAbilityMaster> classAbilityMasterCollection;
    @OneToMany(mappedBy = "rankId")
    private Collection<ClassSaveMaster> classSaveMasterCollection;

    public BonusRankMaster() {
    }

    public BonusRankMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    @XmlTransient
    public Collection<ClassMaster> getClassMasterCollection() {
        return classMasterCollection;
    }

    public void setClassMasterCollection(Collection<ClassMaster> classMasterCollection) {
        this.classMasterCollection = classMasterCollection;
    }

    @XmlTransient
    public Collection<ClassAbilityMaster> getClassAbilityMasterCollection() {
        return classAbilityMasterCollection;
    }

    public void setClassAbilityMasterCollection(Collection<ClassAbilityMaster> classAbilityMasterCollection) {
        this.classAbilityMasterCollection = classAbilityMasterCollection;
    }

    @XmlTransient
    public Collection<ClassSaveMaster> getClassSaveMasterCollection() {
        return classSaveMasterCollection;
    }

    public void setClassSaveMasterCollection(Collection<ClassSaveMaster> classSaveMasterCollection) {
        this.classSaveMasterCollection = classSaveMasterCollection;
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
        if (!(object instanceof BonusRankMaster)) {
            return false;
        }
        BonusRankMaster other = (BonusRankMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BonusRankMaster[ id=" + id + " ]";
    }
    
}
