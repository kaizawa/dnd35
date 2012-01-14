/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SizeMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class SizeMasterFacade implements SizeMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(SizeMaster sizeMaster) {
        em.persist(sizeMaster);
    }

    public void edit(SizeMaster sizeMaster) {
        em.merge(sizeMaster);
    }

    public void remove(SizeMaster sizeMaster) {
        em.remove(em.merge(sizeMaster));
    }

    public SizeMaster find(Object id) {
        return em.find(entity.SizeMaster.class, id);
    }

    public List<SizeMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<SizeMaster> result =  em.createQuery("select object(o) from SizeMaster as o").getResultList();
        return result;
    }

}
