/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.PlayerMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface PlayerMasterFacadeLocal {

    void create(PlayerMaster playerMaster);

    void edit(PlayerMaster playerMaster);

    void remove(PlayerMaster playerMaster);

    PlayerMaster find(Object id);

    List<PlayerMaster> findAll();

    public PlayerMaster findByUsername(String username);

}
