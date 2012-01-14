/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import entity.CharacterSkillGrowthRecord;
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
public class CharacterSkillGrowthRecordFacade implements CharacterSkillGrowthRecordFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CharacterSkillGrowthRecord characterSkillGrowthRecord) {
        em.persist(characterSkillGrowthRecord);
    }

    public void edit(CharacterSkillGrowthRecord characterSkillGrowthRecord) {
        em.merge(characterSkillGrowthRecord);
    }

    public void remove(CharacterSkillGrowthRecord characterSkillGrowthRecord) {
        em.remove(em.merge(characterSkillGrowthRecord));
    }

    public CharacterSkillGrowthRecord find(Object id) {
        return em.find(entity.CharacterSkillGrowthRecord.class, id);
    }

    public List<CharacterSkillGrowthRecord> findAll() {
        @SuppressWarnings("unchecked")
        List<CharacterSkillGrowthRecord> result = em.createQuery("select object(o) from CharacterSkillGrowthRecord as o").getResultList();
        return result;
    }

    public List<CharacterSkillGrowthRecord> findByCharacterAndLevel(CharacterRecord characterRecord, Integer lv) {


        //キャラクターが渡されていた無かったら null を返す        
        if (lv == null || characterRecord == null) {
            return null;
        }
        
        String jpqr = "select g from CharacterSkillGrowthRecord g " +
                "where g.characterRecord = :chara " +
                "and g.characterSkillGrowthRecordPK.characterLevel = :level " +
                "order by g.characterLevel, g.skillId";
        @SuppressWarnings("unchecked")        
        List<CharacterSkillGrowthRecord>  result = (List<CharacterSkillGrowthRecord>) em.createQuery(jpqr).setParameter("chara", characterRecord).setParameter("level", lv).getResultList();
        return result;
    }

    public List<CharacterSkillGrowthRecord> findByCharacter(CharacterRecord characterRecord) {


        // Lv や キャラクターが渡されていた無かったら空のリストを渡す
        if(characterRecord == null ){
            return new ArrayList<CharacterSkillGrowthRecord>();
        }

        String jpqr = "select g from CharacterSkillGrowthRecord g " +
                "where g.characterRecord = :chara " +
                "order by g.characterSkillGrowthRecordPK.characterLevel, g.characterSkillGrowthRecordPK.skillId";
        @SuppressWarnings("unchecked")                
        List<CharacterSkillGrowthRecord>  result = em.createQuery(jpqr).setParameter("chara", characterRecord).getResultList();
        return result;
    }

}
