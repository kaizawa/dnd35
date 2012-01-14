/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import entity.CharacterSaveRecord;
import entity.SaveMaster;
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
public class CharacterSaveRecordFacade implements CharacterSaveRecordFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CharacterSaveRecord characterSaveRecord) {
        em.persist(characterSaveRecord);
    }

    public void edit(CharacterSaveRecord characterSaveRecord) {
        em.merge(characterSaveRecord);
    }

    public void remove(CharacterSaveRecord characterSaveRecord) {
        em.remove(em.merge(characterSaveRecord));
    }

    public CharacterSaveRecord find(Object id) {
        return em.find(entity.CharacterSaveRecord.class, id);
    }

    public List<CharacterSaveRecord> findAll() {
        @SuppressWarnings("unchecked")
        List<CharacterSaveRecord> result = em.createQuery("select object(o) from CharacterSaveRecord as o").getResultList();
        return result;
    }
    
    public CharacterSaveRecord findByCharacterAndSave(CharacterRecord characterRecord, SaveMaster save) {
        CharacterSaveRecord result;

        // save や キャラクターが渡されていた無かったらnullを渡す
        if(characterRecord == null || save == null){
            return null;
        }

        String jpqr = "select g from CharacterSaveRecord g " +
                "where g.characterRecord = :chara " +
                "and g.saveMaster = :save";
        result = (CharacterSaveRecord) em.createQuery(jpqr).setParameter("chara", characterRecord).setParameter("save", save).getSingleResult();
        return result;
    }

    public List<CharacterSaveRecord> findByCharacter(CharacterRecord characterRecord) {


        // キャラクターが渡されていた無かったら空のリストを渡す
        if(characterRecord == null ){
            return new ArrayList<CharacterSaveRecord>();
        }

        String jpqr = "select g from CharacterSaveRecord g " +
                "where g.characterRecord = :chara " +
                "order by g.characterSaveRecordPK.saveId";
        @SuppressWarnings("unchecked")
        List<CharacterSaveRecord> result = em.createQuery(jpqr).setParameter("chara", characterRecord).getResultList();
        return result;        
    }

}
