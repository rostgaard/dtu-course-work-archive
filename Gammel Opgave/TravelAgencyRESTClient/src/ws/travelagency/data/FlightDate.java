/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.data;

/**
 *
 * @author Andreas
 */
public class FlightDate {

    String startAirport;
    String endAirport;
    int day;
    int month;
    int year;

    public FlightDate() {

    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public void setEndAirport(String endAirport) {
        this.endAirport = endAirport; 
    }

    public String getEndAirport() {
        return endAirport; 
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year; 
    }
}
