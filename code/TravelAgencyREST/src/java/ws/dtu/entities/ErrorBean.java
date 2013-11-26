/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peter
 */
@XmlRootElement(name = "error")
public class ErrorBean {

    private String errorMessage;
    private int statusCode;

    public ErrorBean() {
        
    }
    
    public ErrorBean(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
