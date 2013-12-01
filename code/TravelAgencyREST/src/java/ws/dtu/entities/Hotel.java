/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.entities;

import javax.xml.bind.annotation.XmlElement;


public class Hotel {
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String address;
}
