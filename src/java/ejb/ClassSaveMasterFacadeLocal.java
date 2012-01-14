/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ClassMaster;
import entity.ClassSaveMaster;
import entity.SaveMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface ClassSaveMasterFacadeLocal {

    public ClassSaveMaster findByClassAndSave(ClassMaster classMaster, SaveMaster save);

    public List<ClassSaveMaster> findByClass(ClassMaster classMaster);

    void create(ClassSaveMaster classSaveMaster);

    void edit(ClassSaveMaster classSaveMaster);

    void remove(ClassSaveMaster classSaveMaster);

    ClassSaveMaster find(Object id);

    List<ClassSaveMaster> findAll();

}
