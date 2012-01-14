/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.GenderMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface GenderMasterFacadeLocal {

    void create(GenderMaster genderMaster);

    void edit(GenderMaster genderMaster);

    void remove(GenderMaster genderMaster);

    GenderMaster find(Object id);

    List<GenderMaster> findAll();

}
