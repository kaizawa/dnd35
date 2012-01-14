/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterEquipment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class CharacterEquipmentFacade implements CharacterEquipmentFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CharacterEquipment characterEquipment) {
        em.persist(characterEquipment);
    }

    public void edit(CharacterEquipment characterEquipment) {
        em.merge(characterEquipment);
    }

    public void remove(CharacterEquipment characterEquipment) {
        em.remove(em.merge(characterEquipment));
    }

    public CharacterEquipment find(Object id) {
        return em.find(entity.CharacterEquipment.class, id);
    }

    public List<CharacterEquipment> findAll() {
        @SuppressWarnings("unchecked")        
        List<CharacterEquipment> result =  em.createQuery("select object(o) from CharacterEquipment as o").getResultList();
        return result;
    }

}
