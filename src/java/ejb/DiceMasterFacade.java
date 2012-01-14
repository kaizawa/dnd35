/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.DiceMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class DiceMasterFacade implements DiceMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DiceMaster diceMaster) {
        em.persist(diceMaster);
    }

    public void edit(DiceMaster diceMaster) {
        em.merge(diceMaster);
    }

    public void remove(DiceMaster diceMaster) {
        em.remove(em.merge(diceMaster));
    }

    public DiceMaster find(Object id) {
        return em.find(entity.DiceMaster.class, id);
    }

    public List<DiceMaster> findAll() {
        @SuppressWarnings("unchecked")        
       List<DiceMaster> result = em.createQuery("select object(o) from DiceMaster as o").getResultList();
        return result;
    }

}
