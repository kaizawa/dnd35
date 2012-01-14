/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.CampaignMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface CampaignMasterFacadeLocal {

    void create(CampaignMaster campaignMaster);

    void edit(CampaignMaster campaignMaster);

    void remove(CampaignMaster campaignMaster);

    CampaignMaster find(Object id);

    List<CampaignMaster> findAll();

}
