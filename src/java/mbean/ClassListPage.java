/*
 * ClassListPage.java
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
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class ClassListPage extends BaseBean {

    @EJB
    private ClassMasterFacade classMasterFacade;

    

    private HtmlDataTable classTable = new HtmlDataTable();

    public HtmlDataTable getClassTable() {
        return classTable;
    }

    public void setClassTable(HtmlDataTable hdt) {
        this.classTable = hdt;
    }
    private HtmlDataTable classTable1 = new HtmlDataTable();

    public HtmlDataTable getClassTable1() {
        return classTable1;
    }

    public void setClassTable1(HtmlDataTable hdt) {
        this.classTable1 = hdt;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public ClassListPage() {
    }

    @PostConstruct
    public void init() {
        List<ClassMaster> classmasterlist = classMasterFacade.findAll();
        getApplicationBean().setClassMasterList(classmasterlist);
    }
    
    public String editClassLink_action() {
        int classid = classTable.getRowIndex();

        ClassMaster classmaser = getApplicationBean().getClassMasterList().get(classid);

        getSessionBean().setClassMaster(classmaser);
        return "EditClassPage";
    }

    public String createClassButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        ClassMaster classMaster = new ClassMaster();

        getSessionBean().setClassMaster(classMaster);
        return "EditClassPage";
    }

    public String newClassButton_action() {
        if(getSessionBean().loggedIn){
            ClassMaster classMaster = new ClassMaster();
            getSessionBean().setClassMaster(classMaster);
            return "EditClassPage";
        } else {
            return "LoginPage";
        }
    }


}