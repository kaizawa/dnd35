/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.DiceMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface DiceMasterFacadeLocal {

    void create(DiceMaster diceMaster);

    void edit(DiceMaster diceMaster);

    void remove(DiceMaster diceMaster);

    DiceMaster find(Object id);

    List<DiceMaster> findAll();

}
