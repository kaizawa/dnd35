/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.RaceMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface RaceMasterFacadeLocal {

    void create(RaceMaster raceMaster);

    void edit(RaceMaster raceMaster);

    void remove(RaceMaster raceMaster);

    RaceMaster find(Object id);

    List<RaceMaster> findAll();

}
