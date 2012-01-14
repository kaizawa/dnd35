/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.AbilityMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class AbilityMasterFacade implements AbilityMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(AbilityMaster abilityMaster) {
        em.persist(abilityMaster);
    }

    public void edit(AbilityMaster abilityMaster) {
        em.merge(abilityMaster);
    }

    public void remove(AbilityMaster abilityMaster) {
        em.remove(em.merge(abilityMaster));
    }

    public AbilityMaster find(Object id) {
        return em.find(entity.AbilityMaster.class, id);
    }

    public List<AbilityMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<AbilityMaster> result = em.createQuery("select object(o) from AbilityMaster as o").getResultList();
        return result;
    }

}
