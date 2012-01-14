/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SkillMaster;
import entity.SkillSynergyMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface SkillSynergyMasterFacadeLocal {

    public List<SkillSynergyMaster> findBySkill(SkillMaster skill);

    void create(SkillSynergyMaster skillSynergyMaster);

    void edit(SkillSynergyMaster skillSynergyMaster);

    void remove(SkillSynergyMaster skillSynergyMaster);

    SkillSynergyMaster find(Object id);

    List<SkillSynergyMaster> findAll();

}
