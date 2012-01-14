/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SkillMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface SkillMasterFacadeLocal {

    void create(SkillMaster skillMaster);

    void edit(SkillMaster skillMaster);

    void remove(SkillMaster skillMaster);

    SkillMaster find(Object id);

    List<SkillMaster> findAll();

}
