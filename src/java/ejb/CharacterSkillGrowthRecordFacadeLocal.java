/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import entity.CharacterSkillGrowthRecord;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterSkillGrowthRecordFacadeLocal {

    void create(CharacterSkillGrowthRecord characterSkillGrowthRecord);

    void edit(CharacterSkillGrowthRecord characterSkillGrowthRecord);

    void remove(CharacterSkillGrowthRecord characterSkillGrowthRecord);

    CharacterSkillGrowthRecord find(Object id);

    List<CharacterSkillGrowthRecord> findAll();
    
    List<CharacterSkillGrowthRecord> findByCharacterAndLevel(CharacterRecord characterRecord, Integer lv);    

    List<CharacterSkillGrowthRecord> findByCharacter(CharacterRecord characterRecord);
    

}
