/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterGrowthRecord;
import entity.CharacterRecord;
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
public class CharacterGrowthRecordFacade implements CharacterGrowthRecordFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CharacterGrowthRecord characterGrowthRecord) {
        em.persist(characterGrowthRecord);
    }

    public void edit(CharacterGrowthRecord characterGrowthRecord) {
        em.merge(characterGrowthRecord);
    }

    public void remove(CharacterGrowthRecord characterGrowthRecord) {
        em.remove(em.merge(characterGrowthRecord));
    }

    public CharacterGrowthRecord find(Object id) {
        return em.find(entity.CharacterGrowthRecord.class, id);
    }

    public List<CharacterGrowthRecord> findAll() {
        @SuppressWarnings("unchecked")                
        List<CharacterGrowthRecord> result = em.createQuery("select object(o) from CharacterGrowthRecord as o").getResultList();
        return result;
    }

    //キャラクターとレベルから、ひとつのレコードを返す
    public CharacterGrowthRecord findByCharacterAndLevel(CharacterRecord characterRecord, Integer lv) {
        CharacterGrowthRecord result;

        //キャラクターが渡されていた無かったら null を返す        
        if (lv == null || characterRecord == null) {
            return null;
        }
        
        String jpqr = "select g from CharacterGrowthRecord g " +
                "where g.characterRecord = :chara " +
                "and g.characterGrowthRecordPK.characterLevel = :level " +
                "order by g.characterLevel";
        result = (CharacterGrowthRecord) em.createQuery(jpqr).setParameter("chara", characterRecord).setParameter("level", lv).getSingleResult();
        return result;
    }

    // キャラクターからレベル毎のレコードのリストを返す    
    public List<CharacterGrowthRecord> findByCharacter(CharacterRecord characterRecord) {


        // Lv や キャラクターが渡されていた無かったら空のリストを渡す
        if(characterRecord == null ){
            return new ArrayList<CharacterGrowthRecord>();
        }

        String jpqr = "select g from CharacterGrowthRecord g " +
                "where g.characterRecord = :chara " +
                "order by g.characterGrowthRecordPK.characterLevel";
        @SuppressWarnings("unchecked")                        
        List<CharacterGrowthRecord> result = em.createQuery(jpqr).setParameter("chara", characterRecord).getResultList();
        return result;
    }    

}
