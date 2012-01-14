/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.BonusRankMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface BonusRankMasterFacadeLocal {

    void create(BonusRankMaster bonusRankMaster);

    void edit(BonusRankMaster bonusRankMaster);

    void remove(BonusRankMaster bonusRankMaster);

    BonusRankMaster find(Object id);

    List<BonusRankMaster> findAll();

}
