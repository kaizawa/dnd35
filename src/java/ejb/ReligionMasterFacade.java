/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ReligionMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class ReligionMasterFacade implements ReligionMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(ReligionMaster religionMaster) {
        em.persist(religionMaster);
    }

    public void edit(ReligionMaster religionMaster) {
        em.merge(religionMaster);
    }

    public void remove(ReligionMaster religionMaster) {
        em.remove(em.merge(religionMaster));
    }

    public ReligionMaster find(Object id) {
        return em.find(entity.ReligionMaster.class, id);
    }

    public List<ReligionMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<ReligionMaster> result =  em.createQuery("select object(o) from ReligionMaster as o").getResultList();
        return result;
    }

}
