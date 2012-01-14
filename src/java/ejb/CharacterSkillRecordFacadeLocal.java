/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import entity.CharacterSkillRecord;
import entity.SkillMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterSkillRecordFacadeLocal {

    void create(CharacterSkillRecord characterSkillRecord);

    void edit(CharacterSkillRecord characterSkillRecord);

    void remove(CharacterSkillRecord characterSkillRecord);

    CharacterSkillRecord find(Object id);

    List<CharacterSkillRecord> findAll();
    
    List<CharacterSkillRecord> findByCharacter(CharacterRecord characterRecord);    
    
    CharacterSkillRecord findByCharacterAndSkill(CharacterRecord characterRecord, SkillMaster skill);        

}
