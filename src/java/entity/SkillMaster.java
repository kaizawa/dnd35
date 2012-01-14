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
@Table(name = "SKILL_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SkillMaster.findAll", query = "SELECT s FROM SkillMaster s"),
    @NamedQuery(name = "SkillMaster.findById", query = "SELECT s FROM SkillMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SkillMaster.findBySkillName", query = "SELECT s FROM SkillMaster s WHERE s.skillName = :skillName"),
    @NamedQuery(name = "SkillMaster.findByAcceptNoRank", query = "SELECT s FROM SkillMaster s WHERE s.acceptNoRank = :acceptNoRank"),
    @NamedQuery(name = "SkillMaster.findByArmorCheck", query = "SELECT s FROM SkillMaster s WHERE s.armorCheck = :armorCheck")})
public class SkillMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "SKILL_NAME")
    private String skillName;
    @Column(name = "ACCEPT_NO_RANK")
    private Integer acceptNoRank;
    @Column(name = "ARMOR_CHECK")
    private Integer armorCheck;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID")
    @ManyToOne
    private AbilityMaster abilityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private Collection<SkillSynergyMaster> skillSynergyMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private Collection<CharacterSkillRecord> characterSkillRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private Collection<CharacterSkillGrowthRecord> characterSkillGrowthRecordCollection;

    public SkillMaster() {
    }

    public SkillMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getAcceptNoRank() {
        return acceptNoRank;
    }

    public void setAcceptNoRank(Integer acceptNoRank) {
        this.acceptNoRank = acceptNoRank;
    }

    public Integer getArmorCheck() {
        return armorCheck;
    }

    public void setArmorCheck(Integer armorCheck) {
        this.armorCheck = armorCheck;
    }

    public AbilityMaster getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(AbilityMaster abilityId) {
        this.abilityId = abilityId;
    }

    @XmlTransient
    public Collection<SkillSynergyMaster> getSkillSynergyMasterCollection() {
        return skillSynergyMasterCollection;
    }

    public void setSkillSynergyMasterCollection(Collection<SkillSynergyMaster> skillSynergyMasterCollection) {
        this.skillSynergyMasterCollection = skillSynergyMasterCollection;
    }

    @XmlTransient
    public Collection<CharacterSkillRecord> getCharacterSkillRecordCollection() {
        return characterSkillRecordCollection;
    }

    public void setCharacterSkillRecordCollection(Collection<CharacterSkillRecord> characterSkillRecordCollection) {
        this.characterSkillRecordCollection = characterSkillRecordCollection;
    }

    @XmlTransient
    public Collection<CharacterSkillGrowthRecord> getCharacterSkillGrowthRecordCollection() {
        return characterSkillGrowthRecordCollection;
    }

    public void setCharacterSkillGrowthRecordCollection(Collection<CharacterSkillGrowthRecord> characterSkillGrowthRecordCollection) {
        this.characterSkillGrowthRecordCollection = characterSkillGrowthRecordCollection;
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
        if (!(object instanceof SkillMaster)) {
            return false;
        }
        SkillMaster other = (SkillMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SkillMaster[ id=" + id + " ]";
    }
    
}
