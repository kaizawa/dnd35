/*
 * RequestBean1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
 
package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * <p>Request scope data bean for your application.  Create properties
 *  here to represent data that should be made available across different
 *  pages in the same HTTP request, so that the page bean classes do not
 *  have to be directly linked to each other.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 *
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class RequestBean {

    /**
     * <p>Construct a new request data bean instance.</p>
     */
    public RequestBean() {
    }
    

}
