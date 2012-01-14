/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.GenderMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class GenderMasterFacade implements GenderMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(GenderMaster genderMaster) {
        em.persist(genderMaster);
    }

    public void edit(GenderMaster genderMaster) {
        em.merge(genderMaster);
    }

    public void remove(GenderMaster genderMaster) {
        em.remove(em.merge(genderMaster));
    }

    public GenderMaster find(Object id) {
        return em.find(entity.GenderMaster.class, id);
    }

    public List<GenderMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<GenderMaster> result = em.createQuery("select object(o) from GenderMaster as o").getResultList();
        return result;
    }

}
