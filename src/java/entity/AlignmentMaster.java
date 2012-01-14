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
@Table(name = "ALIGNMENT_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlignmentMaster.findAll", query = "SELECT a FROM AlignmentMaster a"),
    @NamedQuery(name = "AlignmentMaster.findById", query = "SELECT a FROM AlignmentMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AlignmentMaster.findByAlignmentName", query = "SELECT a FROM AlignmentMaster a WHERE a.alignmentName = :alignmentName"),
    @NamedQuery(name = "AlignmentMaster.findByAlignmentShortName", query = "SELECT a FROM AlignmentMaster a WHERE a.alignmentShortName = :alignmentShortName")})
public class AlignmentMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "ALIGNMENT_NAME")
    private String alignmentName;
    @Size(max = 100)
    @Column(name = "ALIGNMENT_SHORT_NAME")
    private String alignmentShortName;
    @OneToMany(mappedBy = "alignmentId")
    private Collection<CharacterRecord> characterRecordCollection;
    @OneToMany(mappedBy = "alignmentId")
    private Collection<ReligionMaster> religionMasterCollection;

    public AlignmentMaster() {
    }

    public AlignmentMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlignmentName() {
        return alignmentName;
    }

    public void setAlignmentName(String alignmentName) {
        this.alignmentName = alignmentName;
    }

    public String getAlignmentShortName() {
        return alignmentShortName;
    }

    public void setAlignmentShortName(String alignmentShortName) {
        this.alignmentShortName = alignmentShortName;
    }

    @XmlTransient
    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
    }

    @XmlTransient
    public Collection<ReligionMaster> getReligionMasterCollection() {
        return religionMasterCollection;
    }

    public void setReligionMasterCollection(Collection<ReligionMaster> religionMasterCollection) {
        this.religionMasterCollection = religionMasterCollection;
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
        if (!(object instanceof AlignmentMaster)) {
            return false;
        }
        AlignmentMaster other = (AlignmentMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AlignmentMaster[ id=" + id + " ]";
    }
    
}
