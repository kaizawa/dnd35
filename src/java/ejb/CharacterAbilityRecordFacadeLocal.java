/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterAbilityRecord;
import entity.CharacterRecord;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterAbilityRecordFacadeLocal {

    public List<CharacterAbilityRecord> findByCharacter(CharacterRecord characterRecord);

    void create(CharacterAbilityRecord characterAbilityRecord);

    void edit(CharacterAbilityRecord characterAbilityRecord);

    void remove(CharacterAbilityRecord characterAbilityRecord);

    CharacterAbilityRecord find(Object id);

    List<CharacterAbilityRecord> findAll();

}
