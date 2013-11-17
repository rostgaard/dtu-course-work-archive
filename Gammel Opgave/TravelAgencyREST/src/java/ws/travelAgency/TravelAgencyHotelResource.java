package ws.travelAgency;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.niceview.BookingType;
import ws.travelAgency.data.Customer;
import ws.travelAgency.data.Itinerary;

import ws.niceview.ReturnHotelList;
import ws.travelAgency.data.HotelWrapper;
import ws.travelAgency.data.Status.StatusCode;
import ws.travelAgency.representation.HotelRepresentation;
import ws.travelAgency.representation.StateRepresentation;

/**
 *
 * @author Andreas
 */
@Path("TravelAgency/{cID}/{itiID}/Hotels/")
public class TravelAgencyHotelResource {

    @PUT
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    @Consumes(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response addHotel(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID, BookingType hotelType) {

        Customer customer = TravelAgencyResource.customers.get(customer_ID);

        Itinerary itinerary = customer.getItinerary(itinerary_ID);

        HotelWrapper hotel = new HotelWrapper();
        hotel.getStatus().setStatusCode(StatusCode.unconfirmed);
        hotel.setHotel(hotelType);
       
        itinerary.addHotel(hotel);

        StateRepresentation stateRep = new StateRepresentation();

        TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, stateRep);
        stateRep.setStatusInformation("Hotel added to booking");
        return Response.ok(stateRep).build();
    }

    @GET
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response getHotels(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID, @QueryParam("city") String city, @QueryParam("arrivalDay") String arrivalDay,
            @QueryParam("arrivalMonth") String arrivalMonth, @QueryParam("arrivalYear") String arrivalYear,
            @QueryParam("departureDay") String departureDay, @QueryParam("departureMonth") String departureMonth,
            @QueryParam("departureYear") String departureYear) {
        StateRepresentation stateRep = new StateRepresentation();
        TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, stateRep);

        XMLGregorianCalendar arrivalDate = new XMLGregorianCalendarImpl();
        arrivalDate.setDay(Integer.parseInt(arrivalDay));
        arrivalDate.setMonth(Integer.parseInt(arrivalMonth));
        arrivalDate.setYear(Integer.parseInt(arrivalYear));

        XMLGregorianCalendar departureDate = new XMLGregorianCalendarImpl();
        departureDate.setDay(Integer.parseInt(departureDay));
        departureDate.setMonth(Integer.parseInt(departureMonth));
        departureDate.setYear(Integer.parseInt(departureYear));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ReturnHotelList> future = executor.submit(new GetHotels(city, arrivalDate, departureDate));
        HotelRepresentation hotelRepresentation = new HotelRepresentation();
        TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, hotelRepresentation);

        try { // Call Web Service Operation

            ReturnHotelList hotelList = future.get(5, TimeUnit.SECONDS);
            if (hotelList != null) {
                List<BookingType> hotels = hotelList.getHotels();

                for (BookingType hotelInformation : hotels) {
                    hotelRepresentation.addHotelBooking(hotelInformation);
                }
            }

            return Response.ok(hotelRepresentation).build();

        } catch (Exception ex) {
            stateRep.setStatusInformation("An error occured trying to find hotels!");
            return Response.ok(stateRep).build();
        }
    }

    private class GetHotels implements Callable<ReturnHotelList> {

        String city;
        XMLGregorianCalendar arrivalDate;
        XMLGregorianCalendar departureDate;

        public GetHotels(String city, XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate) {
            this.city = city;
            this.arrivalDate = arrivalDate;
            this.departureDate = departureDate;
        }

        @Override
        public ReturnHotelList call() {
            try {
                ReturnHotelList flightInformations = TravelAgencyResource.hotel_port.getHotels(city,
                        arrivalDate,
                        departureDate);
                return flightInformations;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
