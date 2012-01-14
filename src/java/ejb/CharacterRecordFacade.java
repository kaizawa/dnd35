/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CampaignMaster;
import entity.CharacterRecord;
import java.lang.Exception;
import java.lang.Exception;
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
public class CharacterRecordFacade implements CharacterRecordFacadeLocal {

    @PersistenceContext
    private EntityManager em;

    public void create(CharacterRecord characterRecord) {
        em.persist(characterRecord);
    }

    public void edit(CharacterRecord characterRecord) {
        em.merge(characterRecord);
    }

    public void remove(CharacterRecord characterRecord) {
        em.remove(em.merge(characterRecord));
    }

    public CharacterRecord find(Object id) {
        return em.find(entity.CharacterRecord.class, id);
    }

    public List<CharacterRecord> findAll() {
        @SuppressWarnings("unchecked")                                            
        List<CharacterRecord> result = em.createQuery("select object(o) from CharacterRecord as o").getResultList();
        return result;
    }

    public List<CharacterRecord> findByCampaignId(Integer id) {

        // キャンペーンが指定されていなかったら
        if (id == null) {
            // 空を返す場合
            //return new ArrayList<CharacterRecord>();
            // キャンペーン設定が無いキャラクターをリストする場合
            String jpqr = "select b from CharacterRecord b " +
                    "where b.campaignId is null " +
                    "order by b.id";
            @SuppressWarnings("unchecked")
            List<CharacterRecord> result = em.createQuery(jpqr).getResultList();
            return result;
        }

        //JPQL では Object として受け取る。
        //下のselect b の b は Bookmark のオブジェクトで受け取ることを意味する。
        //また where 句で使われている列名はデータベースの列名でなく、エンティティ
        //のフィールド名なので注意。
        CampaignMaster key = em.find(CampaignMaster.class, id);        
        String jpqr = "select b from CharacterRecord b " +
                "where b.campaignId = :id " +
                "order by b.id";
        @SuppressWarnings("unchecked")
        List<CharacterRecord> result = em.createQuery(jpqr).setParameter("id", key).getResultList();
        return result;
    }
}
