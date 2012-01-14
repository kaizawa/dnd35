/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.faces.bean.ManagedProperty;

/**
 *
 * @author kaizawa
 */
class BaseBean {
    
    @ManagedProperty(value="#{sessionBean}")
    private SessionBean sessionBean;

    @ManagedProperty(value="#{applicationBean}")    
    private ApplicationBean applicationBean;

    @ManagedProperty(value="#{requestBean}")    
    private RequestBean requestBean;    
    

    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public void setApplicationBean(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
    }

    public RequestBean getRequestBean() {
        return requestBean;
    }

    public void setRequestBean(RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}
