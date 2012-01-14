/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterGrowthRecord;
import entity.CharacterRecord;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterGrowthRecordFacadeLocal {

    List<CharacterGrowthRecord> findByCharacter(CharacterRecord characterRecord);
    
    CharacterGrowthRecord findByCharacterAndLevel(CharacterRecord characterRecord, Integer lv);

    void create(CharacterGrowthRecord characterGrowthRecord);

    void edit(CharacterGrowthRecord characterGrowthRecord);

    void remove(CharacterGrowthRecord characterGrowthRecord);

    CharacterGrowthRecord find(Object id);

    List<CharacterGrowthRecord> findAll();

}
