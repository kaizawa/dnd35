/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.AbilityMaster;
import entity.RaceAbilityMaster;
import entity.RaceMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface RaceAbilityMasterFacadeLocal {

    public List<RaceAbilityMaster> findByRace(RaceMaster race);

    public RaceAbilityMaster findByRaceAndAbility(RaceMaster race, AbilityMaster ability);

    void create(RaceAbilityMaster raceAbilityMaster);

    void edit(RaceAbilityMaster raceAbilityMaster);

    void remove(RaceAbilityMaster raceAbilityMaster);

    RaceAbilityMaster find(Object id);

    List<RaceAbilityMaster> findAll();

}
