/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ClassMaster;
import entity.ClassSkillMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface ClassSkillMasterFacadeLocal {

    List<ClassSkillMaster> findByClass(ClassMaster classMaster);

    void create(ClassSkillMaster classSkillMaster);

    void edit(ClassSkillMaster classSkillMaster);

    void remove(ClassSkillMaster classSkillMaster);

    ClassSkillMaster find(Object id);

    List<ClassSkillMaster> findAll();

}
