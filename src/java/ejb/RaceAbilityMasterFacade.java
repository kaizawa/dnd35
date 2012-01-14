/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.AbilityMaster;
import entity.RaceAbilityMaster;
import entity.RaceMaster;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka78231
 */
@Stateless
public class RaceAbilityMasterFacade implements RaceAbilityMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(RaceAbilityMaster raceAbilityMaster) {
        em.persist(raceAbilityMaster);
    }

    public void edit(RaceAbilityMaster raceAbilityMaster) {
        em.merge(raceAbilityMaster);
    }

    public void remove(RaceAbilityMaster raceAbilityMaster) {
        em.remove(em.merge(raceAbilityMaster));
    }

    public RaceAbilityMaster find(Object id) {
        return em.find(entity.RaceAbilityMaster.class, id);
    }

    public List<RaceAbilityMaster> findAll() {
        @SuppressWarnings("unchecked")                        
        List<RaceAbilityMaster> result = em.createQuery("select object(o) from RaceAbilityMaster as o").getResultList();
        return result;
    }

    public RaceAbilityMaster findByRaceAndAbility(RaceMaster race, AbilityMaster ability) {
        RaceAbilityMaster result;

        // 種族や能力が渡されていた無かったら NULL を渡す
        if(race == null || ability == null){
            return null;
        }

        String jpqr = "select g from RaceAbilityMaster g " +
                "where g.raceMaster = :race " +
                "and g.abilityMaster = :ability ";                
        result = (RaceAbilityMaster)em.createQuery(jpqr).setParameter("race", race).setParameter("ability", ability).getSingleResult();
        return result;
    }

    public List<RaceAbilityMaster> findByRace(RaceMaster race) {


        // 種族が渡されていた無かったらからのリストを渡す
        if(race == null){
            return new ArrayList<RaceAbilityMaster>();
        }

        String jpqr = "select g from RaceAbilityMaster g " +
                "where g.raceMaster = :race ";
        @SuppressWarnings("unchecked")                
        List<RaceAbilityMaster> result = em.createQuery(jpqr).setParameter("race", race).getResultList();
        return result;
    }

}
