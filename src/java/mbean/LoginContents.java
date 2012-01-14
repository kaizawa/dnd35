/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import ejb.PlayerMasterFacade;
import entity.PlayerMaster;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version LoginContents.java
 * @version Created on 2009/03/29, 19:18:15
 * @author ka78231
 */

@ManagedBean
@RequestScoped
public class LoginContents extends BaseBean {
    @EJB
    private PlayerMasterFacade PlayerMasterFacade;

    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public LoginContents() {
    }

    public String charaEditLink_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String charaViewButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String login_action() {

        String username = (String)this.userName;
        String passwd = (String)this.password;

        PlayerMaster player =  PlayerMasterFacade.findByUsername(username);

        if(player == null){
            //error("ログインに失敗しました. ユーザ名が間違っています");
            return null;
        }

        if( !player.getPassword().equals(passwd)){
            //error("ログインに失敗しました. パスワードが間違っています");
            return null;
        }

         getSessionBean().setLoggedIn(true);
         getSessionBean().setPlayerMaster(player);

        return "admin";
    }

    public String register_action() {
        // TODO:アクションを処理します。戻り値は、
        //  ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }
    
}

