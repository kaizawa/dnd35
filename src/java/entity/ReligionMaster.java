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
@Table(name = "RELIGION_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReligionMaster.findAll", query = "SELECT r FROM ReligionMaster r"),
    @NamedQuery(name = "ReligionMaster.findById", query = "SELECT r FROM ReligionMaster r WHERE r.id = :id"),
    @NamedQuery(name = "ReligionMaster.findByReligionName", query = "SELECT r FROM ReligionMaster r WHERE r.religionName = :religionName"),
    @NamedQuery(name = "ReligionMaster.findByDescription", query = "SELECT r FROM ReligionMaster r WHERE r.description = :description")})
public class ReligionMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "RELIGION_NAME")
    private String religionName;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "religionId")
    private Collection<CharacterRecord> characterRecordCollection;
    @JoinColumn(name = "ALIGNMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private AlignmentMaster alignmentId;

    public ReligionMaster() {
    }

    public ReligionMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
    }

    public AlignmentMaster getAlignmentId() {
        return alignmentId;
    }

    public void setAlignmentId(AlignmentMaster alignmentId) {
        this.alignmentId = alignmentId;
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
        if (!(object instanceof ReligionMaster)) {
            return false;
        }
        ReligionMaster other = (ReligionMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReligionMaster[ id=" + id + " ]";
    }
    
}
