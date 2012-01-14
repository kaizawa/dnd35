/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterRecord;
import entity.CharacterSaveRecord;
import entity.SaveMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterSaveRecordFacadeLocal {

    public List<CharacterSaveRecord> findByCharacter(CharacterRecord characterRecord);

    void create(CharacterSaveRecord characterSaveRecord);

    void edit(CharacterSaveRecord characterSaveRecord);

    void remove(CharacterSaveRecord characterSaveRecord);

    CharacterSaveRecord find(Object id);

    List<CharacterSaveRecord> findAll();
    
    public CharacterSaveRecord findByCharacterAndSave(CharacterRecord characterRecord, SaveMaster save);

}
