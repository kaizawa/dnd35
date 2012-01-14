/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import entity.CharacterSkillRecord;
import entity.SkillMaster;
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
public class CharacterSkillRecordFacade implements CharacterSkillRecordFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CharacterSkillRecord characterSkillRecord) {
        em.persist(characterSkillRecord);
    }

    public void edit(CharacterSkillRecord characterSkillRecord) {
        em.merge(characterSkillRecord);
    }

    public void remove(CharacterSkillRecord characterSkillRecord) {
        em.remove(em.merge(characterSkillRecord));
    }

    public CharacterSkillRecord find(Object id) {
        return em.find(entity.CharacterSkillRecord.class, id);
    }

    public List<CharacterSkillRecord> findAll() {
        @SuppressWarnings("unchecked")
        List<CharacterSkillRecord> result = em.createQuery("select object(o) from CharacterSkillRecord as o").getResultList();
        return result;
    }

    public List<CharacterSkillRecord> findByCharacter(CharacterRecord characterRecord) {


        // キャラクターが渡されていた無かったら空のリストを渡す
        if(characterRecord == null ){
            return new ArrayList<CharacterSkillRecord>();
        }

        String jpqr = "select g from CharacterSkillRecord g " +
                "where g.characterRecord = :chara " +
                "order by g.characterSkillRecordPK.skillId";
        @SuppressWarnings("unchecked")                        
        List<CharacterSkillRecord>  result = em.createQuery(jpqr).setParameter("chara", characterRecord).getResultList();
        return result;        
    }

    public CharacterSkillRecord findByCharacterAndSkill(CharacterRecord characterRecord, SkillMaster skill) {
        CharacterSkillRecord result;

        // skill や キャラクターが渡されていた無かったらnullを渡す
        if(characterRecord == null || skill == null){
            return null;
        }

        String jpqr = "select g from CharacterSkillRecord g " +
                "where g.characterRecord = :chara " +
                "and g.skillMaster = :skill";
        result = (CharacterSkillRecord) em.createQuery(jpqr).setParameter("chara", characterRecord).setParameter("skill", skill).getSingleResult();
        return result;
    }

}
