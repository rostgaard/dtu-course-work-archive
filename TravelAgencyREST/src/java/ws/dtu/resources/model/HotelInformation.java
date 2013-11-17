/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;

/**
 *
 * @author krc
 */
public class HotelInformation {

    private String location;
    private String name;

    public HotelInformation(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
    
}
