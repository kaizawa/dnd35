/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ClassMaster;
import entity.ClassSkillMaster;
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
public class ClassSkillMasterFacade implements ClassSkillMasterFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(ClassSkillMaster classSkillMaster) {
        em.persist(classSkillMaster);
    }

    public void edit(ClassSkillMaster classSkillMaster) {
        em.merge(classSkillMaster);
    }

    public void remove(ClassSkillMaster classSkillMaster) {
        em.remove(em.merge(classSkillMaster));
    }

    public ClassSkillMaster find(Object id) {
        return em.find(entity.ClassSkillMaster.class, id);
    }

    public List<ClassSkillMaster> findAll() {
        @SuppressWarnings("unchecked")                                                        
        List<ClassSkillMaster> result = em.createQuery("select object(o) from ClassSkillMaster as o").getResultList();
        return result;
    }

    public List<ClassSkillMaster> findByClass(ClassMaster classMaster) {


        // キャラクターが渡されていた無かったら空のリストを渡す
        if(classMaster == null ){
            return new ArrayList<ClassSkillMaster>();
        }

        String jpqr = "select g from ClassSkillMaster g " +
                "where g.classMaster = :cls " +
                "order by g.classSkillMasterPK.skillId";
        @SuppressWarnings("unchecked")
        List<ClassSkillMaster> result = em.createQuery(jpqr).setParameter("cls", classMaster).getResultList();
        return result;
    }

}
