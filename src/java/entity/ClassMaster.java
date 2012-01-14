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
@Table(name = "CLASS_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassMaster.findAll", query = "SELECT c FROM ClassMaster c"),
    @NamedQuery(name = "ClassMaster.findById", query = "SELECT c FROM ClassMaster c WHERE c.id = :id"),
    @NamedQuery(name = "ClassMaster.findByClassName", query = "SELECT c FROM ClassMaster c WHERE c.className = :className"),
    @NamedQuery(name = "ClassMaster.findByDescription", query = "SELECT c FROM ClassMaster c WHERE c.description = :description"),
    @NamedQuery(name = "ClassMaster.findBySkillPoint", query = "SELECT c FROM ClassMaster c WHERE c.skillPoint = :skillPoint")})
public class ClassMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "CLASS_NAME")
    private String className;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SKILL_POINT")
    private Integer skillPoint;
    @JoinColumn(name = "HIT_DICE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DiceMaster hitDiceType;
    @JoinColumn(name = "BASE_ATTACK_RANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private BonusRankMaster baseAttackRankId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classMaster")
    private Collection<ClassSkillMaster> classSkillMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classMaster")
    private Collection<ClassAbilityMaster> classAbilityMasterCollection;
    @OneToMany(mappedBy = "classId")
    private Collection<CharacterGrowthRecord> characterGrowthRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classMaster")
    private Collection<ClassSaveMaster> classSaveMasterCollection;

    public ClassMaster() {
    }

    public ClassMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(Integer skillPoint) {
        this.skillPoint = skillPoint;
    }

    public DiceMaster getHitDiceType() {
        return hitDiceType;
    }

    public void setHitDiceType(DiceMaster hitDiceType) {
        this.hitDiceType = hitDiceType;
    }

    public BonusRankMaster getBaseAttackRankId() {
        return baseAttackRankId;
    }

    public void setBaseAttackRankId(BonusRankMaster baseAttackRankId) {
        this.baseAttackRankId = baseAttackRankId;
    }

    @XmlTransient
    public Collection<ClassSkillMaster> getClassSkillMasterCollection() {
        return classSkillMasterCollection;
    }

    public void setClassSkillMasterCollection(Collection<ClassSkillMaster> classSkillMasterCollection) {
        this.classSkillMasterCollection = classSkillMasterCollection;
    }

    @XmlTransient
    public Collection<ClassAbilityMaster> getClassAbilityMasterCollection() {
        return classAbilityMasterCollection;
    }

    public void setClassAbilityMasterCollection(Collection<ClassAbilityMaster> classAbilityMasterCollection) {
        this.classAbilityMasterCollection = classAbilityMasterCollection;
    }

    @XmlTransient
    public Collection<CharacterGrowthRecord> getCharacterGrowthRecordCollection() {
        return characterGrowthRecordCollection;
    }

    public void setCharacterGrowthRecordCollection(Collection<CharacterGrowthRecord> characterGrowthRecordCollection) {
        this.characterGrowthRecordCollection = characterGrowthRecordCollection;
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
        if (!(object instanceof ClassMaster)) {
            return false;
        }
        ClassMaster other = (ClassMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassMaster[ id=" + id + " ]";
    }
    
}
