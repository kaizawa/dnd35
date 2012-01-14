/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterRecordFacadeLocal {

    public List<CharacterRecord> findByCampaignId(Integer id);

    void create(CharacterRecord characterRecord);

    void edit(CharacterRecord characterRecord);

    void remove(CharacterRecord characterRecord);

    CharacterRecord find(Object id);

    List<CharacterRecord> findAll();

}
