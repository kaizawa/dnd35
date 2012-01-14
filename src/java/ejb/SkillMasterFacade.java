/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SkillMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class SkillMasterFacade implements SkillMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(SkillMaster skillMaster) {
        em.persist(skillMaster);
    }

    public void edit(SkillMaster skillMaster) {
        em.merge(skillMaster);
    }

    public void remove(SkillMaster skillMaster) {
        em.remove(em.merge(skillMaster));
    }

    public SkillMaster find(Object id) {
        return em.find(entity.SkillMaster.class, id);
    }

    public List<SkillMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<SkillMaster> result = em.createQuery("select object(o) from SkillMaster as o").getResultList();
        return result;
    }

}
