/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CharacterEquipment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CharacterEquipmentFacadeLocal {

    void create(CharacterEquipment characterEquipment);

    void edit(CharacterEquipment characterEquipment);

    void remove(CharacterEquipment characterEquipment);

    CharacterEquipment find(Object id);

    List<CharacterEquipment> findAll();

}
