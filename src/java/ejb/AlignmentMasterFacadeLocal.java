/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.AlignmentMaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ka78231
 */
@Local
public interface AlignmentMasterFacadeLocal {

    void create(AlignmentMaster alignmentMaster);

    void edit(AlignmentMaster alignmentMaster);

    void remove(AlignmentMaster alignmentMaster);

    AlignmentMaster find(Object id);

    List<AlignmentMaster> findAll();

}
