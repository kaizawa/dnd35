/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import entity.ClassMaster;
import entity.RaceMaster;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version AdminPageSubHeader.java
 * @version Created on 2009/04/05, 20:05:41
 * @author ka78231
 */


@ManagedBean
@RequestScoped
public class AdminPageSubHeader extends BaseBean{

    public AdminPageSubHeader() {
    }


    public String newClassLink_action() {
        if(getSessionBean().loggedIn){
            ClassMaster classMaster = new ClassMaster();
            getSessionBean().setClassMaster(classMaster);
            return "EditClassPageContents";
        } else {
            return "LoginContents";
        }
    }

    public String classListLink_action() {
        if(getSessionBean().loggedIn){
            return "ClassListPageContents";
        } else {
            return "LoginContents";
        }
    }
    String selectedHandle;

    public void setSelectedHandle(String selectedHandle) {
        this.selectedHandle = selectedHandle;
    }

    public void raceDropDown_processValueChange(ValueChangeEvent vce) {
        // ドロップダウンを使った ページ遷移のサンプル.. ヘッダー(JSPF)では使えなかった。。。なぜかエラー
        //String handle = (String) raceDropDown.getValue();
        //moveToPage(handle);
    }

    public String newRaceLink_action() {
        if(getSessionBean().loggedIn){
            RaceMaster raceMaster = new RaceMaster();
            getSessionBean().setRaceMaster(raceMaster);
            return "EditRacePageContents";
        } else {
            return "LoginContents";
        }
    }

    public String raceListLink_action() {
        if(getSessionBean().loggedIn){
            return "RaceListPageContents";
        } else {
            return "LoginContents";
        }
    }
}
