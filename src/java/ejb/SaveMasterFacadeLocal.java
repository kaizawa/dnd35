/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SaveMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface SaveMasterFacadeLocal {

    void create(SaveMaster saveMaster);

    void edit(SaveMaster saveMaster);

    void remove(SaveMaster saveMaster);

    SaveMaster find(Object id);

    List<SaveMaster> findAll();

}
