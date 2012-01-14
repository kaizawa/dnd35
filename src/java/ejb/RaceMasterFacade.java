/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.RaceMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class RaceMasterFacade implements RaceMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(RaceMaster raceMaster) {
        em.persist(raceMaster);
    }

    public void edit(RaceMaster raceMaster) {
        em.merge(raceMaster);
    }

    public void remove(RaceMaster raceMaster) {
        em.remove(em.merge(raceMaster));
    }

    public RaceMaster find(Object id) {
        return em.find(entity.RaceMaster.class, id);
    }

    public List<RaceMaster> findAll() {
        @SuppressWarnings("unchecked")        
        List<RaceMaster> result =  em.createQuery("select object(o) from RaceMaster as o").getResultList();
        return result;
    }

}
