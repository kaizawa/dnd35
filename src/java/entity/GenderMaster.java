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
@Table(name = "GENDER_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenderMaster.findAll", query = "SELECT g FROM GenderMaster g"),
    @NamedQuery(name = "GenderMaster.findById", query = "SELECT g FROM GenderMaster g WHERE g.id = :id"),
    @NamedQuery(name = "GenderMaster.findByGender", query = "SELECT g FROM GenderMaster g WHERE g.gender = :gender")})
public class GenderMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "GENDER")
    private String gender;
    @OneToMany(mappedBy = "genderId")
    private Collection<CharacterRecord> characterRecordCollection;

    public GenderMaster() {
    }

    public GenderMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlTransient
    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
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
        if (!(object instanceof GenderMaster)) {
            return false;
        }
        GenderMaster other = (GenderMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GenderMaster[ id=" + id + " ]";
    }
    
}
