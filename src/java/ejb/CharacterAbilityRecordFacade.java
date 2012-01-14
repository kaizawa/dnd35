/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterAbilityRecord;
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
public class CharacterAbilityRecordFacade implements CharacterAbilityRecordFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CharacterAbilityRecord characterAbilityRecord) {
        em.persist(characterAbilityRecord);
    }

    public void edit(CharacterAbilityRecord characterAbilityRecord) {
        em.merge(characterAbilityRecord);
    }

    public void remove(CharacterAbilityRecord characterAbilityRecord) {
        em.remove(em.merge(characterAbilityRecord));
    }

    public CharacterAbilityRecord find(Object id) {
        return em.find(entity.CharacterAbilityRecord.class, id);
    }

    public List<CharacterAbilityRecord> findAll() {
        @SuppressWarnings("unchecked")        
        List<CharacterAbilityRecord> result = em.createQuery("select object(o) from CharacterAbilityRecord as o").getResultList();
        return result;
    }

    public List<CharacterAbilityRecord> findByCharacter(CharacterRecord characterRecord) {


        // キャラクターが渡されていた無かったら空のリストを渡す
        if(characterRecord == null ){
            return new ArrayList<CharacterAbilityRecord>();
        }

        String jpqr = "select g from CharacterAbilityRecord g " +
                "where g.characterRecord = :chara " +
                "order by g.characterAbilityRecordPK.abilityId";
        @SuppressWarnings("unchecked")
        List<CharacterAbilityRecord> result = em.createQuery(jpqr).setParameter("chara", characterRecord).getResultList();
        return result;
    }

}
