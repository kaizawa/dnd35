/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ReligionMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface ReligionMasterFacadeLocal {

    void create(ReligionMaster religionMaster);

    void edit(ReligionMaster religionMaster);

    void remove(ReligionMaster religionMaster);

    ReligionMaster find(Object id);

    List<ReligionMaster> findAll();

}
