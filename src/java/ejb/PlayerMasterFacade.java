/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.PlayerMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class PlayerMasterFacade implements PlayerMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(PlayerMaster playerMaster) {
        em.persist(playerMaster);
    }

    public void edit(PlayerMaster playerMaster) {
        em.merge(playerMaster);
    }

    public void remove(PlayerMaster playerMaster) {
        em.remove(em.merge(playerMaster));
    }

    public PlayerMaster find(Object id) {
        return em.find(PlayerMaster.class, id);
    }

    public List<PlayerMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<PlayerMaster> result =  em.createQuery("select object(o) from PlayerMaster as o").getResultList();
        return result;
    }
    public PlayerMaster findByUsername(String username){
        try {
            return (PlayerMaster) em.createNamedQuery("PlayerMaster.findByUsername").setParameter("username", username).getSingleResult();
        } catch (NoResultException ex ){
            return null;
        }
    }
}
