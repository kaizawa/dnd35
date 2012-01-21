/*
 * ClassListPageContents.java
 *
 * Created on 2009/01/05, 23:14:53
 */

package mbean;

import ejb.ClassMasterFacade;
import ejb.ClassSkillMasterFacade;
import entity.ClassMaster;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;



@ManagedBean
@RequestScoped
public class AdminPageContents extends BaseBean {
    @EJB
    private ClassSkillMasterFacade classSkillMasterFacade;
    @EJB
    private ClassMasterFacade classMasterFacade;

    private HtmlDataTable classTable = new HtmlDataTable();

    public HtmlDataTable getClassTable() {
        return classTable;
    }

    public void setClassTable(HtmlDataTable hdt) {
        this.classTable = hdt;
    }
    
    @PostConstruct
    public void init() {
        List<ClassMaster> classmasterlist = classMasterFacade.findAll();
        getApplicationBean().setClassMasterList(classmasterlist);
    }    

    public String editClassButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        int classid = classTable.getRowIndex();

        ClassMaster classmaser = getApplicationBean().getClassMasterList().get(classid);

        getSessionBean().setClassMaster(classmaser);
        return "EditClassPageContents";
    }

    public String createClassButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        ClassMaster classMaster = new ClassMaster();

        getSessionBean().setClassMaster(classMaster);
        return "EditClassPageContents";
    }

    public String classListLink_action() {
        if(getSessionBean().loggedIn){
            return "ClassListPageContents";
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