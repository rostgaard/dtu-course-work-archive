package ws.travelAgency;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.Calendar;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.lameduck.FlightInformation;
import ws.niceview.BookingType;
import ws.travelAgency.data.Customer;
import ws.travelAgency.data.FlightInfoWrapper;
import ws.travelAgency.data.HotelWrapper;
import ws.travelAgency.data.Itinerary;
import ws.travelAgency.data.Itinerary.ItineraryStatus; 
import ws.travelAgency.data.Status.StatusCode;
import ws.travelAgency.representation.StateRepresentation;

/**
 *
 * @author Andreas
 */
@Path("TravelAgency/{cID}/{itiID}/Status/")
public class TravelAgencyStatusResource {

    @PUT
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response cancel(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID) {
        XMLGregorianCalendar blocking_date = TravelAgencyResource.getCurrentDate();

        Customer customer = TravelAgencyResource.customers.get(customer_ID);
        StateRepresentation stateRep = new StateRepresentation();

        if (customer != null) {
            Itinerary itinerary = customer.getItinerary(itinerary_ID);

            if (itinerary != null) {
                if (itinerary.getStatus() == Itinerary.ItineraryStatus.planning) {
                    customer.removeItinerary(itinerary_ID);
                    stateRep.setStatusInformation("Itinerary succesfully cancelled!");
                } else if (itinerary.getStatus() == Itinerary.ItineraryStatus.booked && itinerary.getStartDate().compare(blocking_date) != DatatypeConstants.LESSER) {
                    if (cancel(customer, itinerary)) {
                        itinerary.setItineraryStatus(Itinerary.ItineraryStatus.planning);
                        TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, stateRep);
                        stateRep.setStatusInformation("Itinerary succesfully cancelled!");
                    } else {
                        itinerary.setItineraryStatus(Itinerary.ItineraryStatus.failedCancel);
                        TravelAgencyResource.addLockedLink(customer_ID, itinerary_ID, stateRep);
                        stateRep.setStatusInformation("Itinerary failed cancelled!");
                    }
                } else if (itinerary.getStatus() == Itinerary.ItineraryStatus.booked && itinerary.getStartDate().compare(blocking_date) == DatatypeConstants.LESSER) {
                    // looking the itinerary, in case it is the day before departure or later
                    itinerary.setItineraryStatus(ItineraryStatus.locked);
                    TravelAgencyResource.addLockedLink(customer_ID, itinerary_ID, stateRep);
                    stateRep.setStatusInformation("Itinerary " + itinerary_ID + " couldn't get cancelled as it has been locked");
                }
                return Response.ok(stateRep).build();
            }
        }
        return Response.noContent().build();
    }

    @GET
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response getStatus(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID) {
        Customer customer = TravelAgencyResource.customers.get(customer_ID);
        StateRepresentation stateRep = new StateRepresentation();

        if (customer != null) {
            Itinerary itinerary = customer.getItinerary(itinerary_ID);
            if (itinerary != null) {
                stateRep.setStatusInformation(itinerary.getStatus().toString());
                return Response.ok(stateRep).build();
            }
        }
        stateRep.setStatusInformation("Couldn't locate the customer " + customer_ID + " and itinerary " + itinerary_ID);
        return Response.ok(stateRep).build();
    }

    public static boolean cancel(Customer cus, Itinerary itinerary) {
        boolean fully_cancelled = true;

        for (FlightInfoWrapper flight : itinerary.getFlights()) {
            if (flight.getStatus().getStatusCode() == StatusCode.confirmed) {
                FlightInformation flightInfo = flight.getFlightInformation();
                try {
                    if (TravelAgencyResource.flight_port.cancelFlight(flightInfo.getBookingNumber(),
                            (int) flightInfo.getPrice(),
                            cus.getCreditCard())) {
                        flight.getStatus().setStatusCode(StatusCode.cancelled);
                    }
                } catch (Exception e) {
                    fully_cancelled = false;
                }
            }
        }

        for (HotelWrapper hotel : itinerary.getHotels()) {
            if (hotel.getStatus().getStatusCode() == StatusCode.confirmed) {
                BookingType hotelBooking = hotel.getHotel();

                try {
                    if (TravelAgencyResource.hotel_port.cancelHotel(hotelBooking.getBookingNumber())) {
                        hotel.getStatus().setStatusCode(StatusCode.cancelled);
                        hotelBooking.setBooked(false);
                    }
                } catch (Exception e) {
                    fully_cancelled = false;
                }
            }
        }

        // TODO: Cancel hotels aswell!
        return fully_cancelled;
    }
}
