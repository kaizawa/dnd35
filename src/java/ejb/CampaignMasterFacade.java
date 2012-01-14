/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CampaignMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class CampaignMasterFacade implements CampaignMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CampaignMaster campaignMaster) {
        em.persist(campaignMaster);
    }

    public void edit(CampaignMaster campaignMaster) {
        em.merge(campaignMaster);
    }

    public void remove(CampaignMaster campaignMaster) {
        em.remove(em.merge(campaignMaster));
    }

    public CampaignMaster find(Object id) {
        return em.find(entity.CampaignMaster.class, id);
    }

    public List<CampaignMaster> findAll() {
        @SuppressWarnings("unchecked")        
        List<CampaignMaster> result = em.createQuery("select object(o) from CampaignMaster as o").getResultList();
        return result;
    }

}
