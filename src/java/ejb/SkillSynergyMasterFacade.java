/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SkillMaster;
import entity.SkillSynergyMaster;
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
public class SkillSynergyMasterFacade implements SkillSynergyMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(SkillSynergyMaster skillSynergyMaster) {
        em.persist(skillSynergyMaster);
    }

    public void edit(SkillSynergyMaster skillSynergyMaster) {
        em.merge(skillSynergyMaster);
    }

    public void remove(SkillSynergyMaster skillSynergyMaster) {
        em.remove(em.merge(skillSynergyMaster));
    }

    public SkillSynergyMaster find(Object id) {
        return em.find(entity.SkillSynergyMaster.class, id);
    }

    public List<SkillSynergyMaster> findAll() {
        @SuppressWarnings("unchecked")
        List<SkillSynergyMaster> result = em.createQuery("select object(o) from SkillSynergyMaster as o").getResultList();
        return result;
    }

    public List<SkillSynergyMaster> findBySkill(SkillMaster skill) {


        // skill が渡されていた無かったらnullを渡す
        if( skill == null){
            return new ArrayList<SkillSynergyMaster>();
        }

        String jpqr = "select g from SkillSynergyMaster g " +
                "where g.skillMaster = :skill";
        @SuppressWarnings("unchecked")
        List<SkillSynergyMaster> result = em.createQuery(jpqr).setParameter("skill", skill).getResultList();
        return result;
    }
}
