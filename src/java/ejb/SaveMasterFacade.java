/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SaveMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class SaveMasterFacade implements SaveMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(SaveMaster saveMaster) {
        em.persist(saveMaster);
    }

    public void edit(SaveMaster saveMaster) {
        em.merge(saveMaster);
    }

    public void remove(SaveMaster saveMaster) {
        em.remove(em.merge(saveMaster));
    }

    public SaveMaster find(Object id) {
        return em.find(entity.SaveMaster.class, id);
    }

    public List<SaveMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<SaveMaster> result = em.createQuery("select object(o) from SaveMaster as o").getResultList();
        return result;
    }

}
