/*
 * ClassListPageContents.java
 *
 * Created on 2009/01/05, 23:14:53
 */

package mbean;



import ejb.RaceMasterFacade;
import entity.RaceMaster;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

@ManagedBean
@RequestScoped
public class RaceListPageContents extends BaseBean{
    @EJB
    private RaceMasterFacade raceMasterFacade;

    private HtmlDataTable raceTable = new HtmlDataTable();

    public HtmlDataTable getRaceTable() {
        return raceTable;
    }

    public void setRaceTable(HtmlDataTable hdt) {
        this.raceTable = hdt;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public RaceListPageContents() {
    }


    @PostConstruct
    public void init() {
        List<RaceMaster> racemasterlist = raceMasterFacade.findAll();
        getApplicationBean().setRaceMasterList(racemasterlist);
    }

    public String editRaceLink_action() {
        int raceid = raceTable.getRowIndex();

        RaceMaster racemaster = getApplicationBean().getRaceMasterList().get(raceid);

         getSessionBean().setRaceMaster(racemaster);
        return "EditRacePageContents";
    }

    public String newRaceButton_action() {
        if( getSessionBean().loggedIn){
            RaceMaster raceMaster = new RaceMaster();
             getSessionBean().setRaceMaster(raceMaster);
            return "EditRacePageContents";
        } else {
            return "LoginContents";
        }
    }
}