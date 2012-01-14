/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.RaceMaster;
import entity.RaceSaveMaster;
import entity.SaveMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface RaceSaveMasterFacadeLocal {

    public List<RaceSaveMaster> findByRace(RaceMaster race);

    void create(RaceSaveMaster raceSaveMaster);

    void edit(RaceSaveMaster raceSaveMaster);

    void remove(RaceSaveMaster raceSaveMaster);

    RaceSaveMaster find(Object id);

    List<RaceSaveMaster> findAll();

    RaceSaveMaster findByRaceAndSave(RaceMaster race, SaveMaster save);

}
