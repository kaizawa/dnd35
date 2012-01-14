/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.AbilityMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface AbilityMasterFacadeLocal {

    void create(AbilityMaster abilityMaster);

    void edit(AbilityMaster abilityMaster);

    void remove(AbilityMaster abilityMaster);

    AbilityMaster find(Object id);

    List<AbilityMaster> findAll();

}
