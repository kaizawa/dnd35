/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.BonusRankMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class BonusRankMasterFacade implements BonusRankMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(BonusRankMaster bonusRankMaster) {
        em.persist(bonusRankMaster);
    }

    public void edit(BonusRankMaster bonusRankMaster) {
        em.merge(bonusRankMaster);
    }

    public void remove(BonusRankMaster bonusRankMaster) {
        em.remove(em.merge(bonusRankMaster));
    }

    public BonusRankMaster find(Object id) {
        return em.find(entity.BonusRankMaster.class, id);
    }

    public List<BonusRankMaster> findAll() {
        @SuppressWarnings("unchecked")        
        List<BonusRankMaster> result = em.createQuery("select object(o) from BonusRankMaster as o").getResultList();
        return result;
    }

}
