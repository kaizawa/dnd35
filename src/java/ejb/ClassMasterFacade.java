/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ClassMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class ClassMasterFacade implements ClassMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(ClassMaster classMaster) {
        em.persist(classMaster);
    }

    public void edit(ClassMaster classMaster) {
        em.merge(classMaster);
    }

    public void remove(ClassMaster classMaster) {
        em.remove(em.merge(classMaster));
    }

    public ClassMaster find(Object id) {
        return em.find(entity.ClassMaster.class, id);
    }

    public List<ClassMaster> findAll() {
        @SuppressWarnings("unchecked")                                
        List<ClassMaster> result = em.createQuery("select object(o) from ClassMaster as o").getResultList();
        return result;
    }

}
