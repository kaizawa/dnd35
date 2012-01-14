/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ClassMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface ClassMasterFacadeLocal {

    void create(ClassMaster classMaster);

    void edit(ClassMaster classMaster);

    void remove(ClassMaster classMaster);

    ClassMaster find(Object id);

    List<ClassMaster> findAll();

}
