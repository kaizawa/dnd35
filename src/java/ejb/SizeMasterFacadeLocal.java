/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SizeMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface SizeMasterFacadeLocal {

    void create(SizeMaster sizeMaster);

    void edit(SizeMaster sizeMaster);

    void remove(SizeMaster sizeMaster);

    SizeMaster find(Object id);

    List<SizeMaster> findAll();

}
