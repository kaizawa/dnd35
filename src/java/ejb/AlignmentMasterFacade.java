/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.AlignmentMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class AlignmentMasterFacade implements AlignmentMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(AlignmentMaster alignmentMaster) {
        em.persist(alignmentMaster);
    }

    public void edit(AlignmentMaster alignmentMaster) {
        em.merge(alignmentMaster);
    }

    public void remove(AlignmentMaster alignmentMaster) {
        em.remove(em.merge(alignmentMaster));
    }

    public AlignmentMaster find(Object id) {
        return em.find(entity.AlignmentMaster.class, id);
    }

    public List<AlignmentMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<AlignmentMaster> result = em.createQuery("select object(o) from AlignmentMaster as o").getResultList();
        return result;
    }

}
