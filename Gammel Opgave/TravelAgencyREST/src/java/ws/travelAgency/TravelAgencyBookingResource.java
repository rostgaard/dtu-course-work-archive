package ws.travelAgency;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.List;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ws.niceview.HotelType;
import ws.travelAgency.data.Customer;
import ws.travelAgency.data.FlightInfoWrapper;
import ws.travelAgency.data.HotelWrapper;
import ws.travelAgency.data.Itinerary;
import ws.travelAgency.data.Status.StatusCode;
import ws.travelAgency.representation.StateRepresentation;

/**
 *
 * @author Andreas
 */
@Path("TravelAgency/{cID}/{itiID}/Booking/")
public class TravelAgencyBookingResource {

    @PUT
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response book(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID) {
        Customer customer = TravelAgencyResource.customers.get(customer_ID);

        if (customer != null) {
            CreditCardInfoType creditCardInfo = customer.getCreditCard();
            Itinerary itinerary = customer.getItinerary(itinerary_ID);
          
            StateRepresentation stateRep = new StateRepresentation();
         
            try {
                if (itinerary != null) {
                    List<HotelWrapper> hotels = itinerary.getHotels();
                    for (HotelWrapper hotelWrapper : hotels) {
                        int bookingNumber = hotelWrapper.getHotel().getBookingNumber();
                        HotelType hotel = hotelWrapper.getHotel().getHotel();
                        if (hotel.isCreditCardGuarantee()) {
                            TravelAgencyResource.hotel_port.bookHotel(bookingNumber, creditCardInfo);
                        } else {
                            // TODO: Shouldn't there simply be one not taking a credit card?
                            TravelAgencyResource.hotel_port.bookHotel(bookingNumber, null);
                        }
                        hotelWrapper.getStatus().setStatusCode(StatusCode.confirmed);
                    }
                    List<FlightInfoWrapper> flights = itinerary.getFlights();
                    for (FlightInfoWrapper flightInfoWrapper : flights) {
                        int bookingNumber = flightInfoWrapper.getFlightInformation().getBookingNumber();
                        TravelAgencyResource.flight_port.bookFlight(bookingNumber, creditCardInfo);
                        flightInfoWrapper.getStatus().setStatusCode(StatusCode.confirmed);
                    }
                }
            } catch (Exception e) {
                // Clean up!
                if (TravelAgencyStatusResource.cancel(customer, itinerary)) {
                    // Cleaned up!
                    TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, stateRep);
                    stateRep.setStatusInformation("Booking failed, all previous bookings cancelled");
                    itinerary.setItineraryStatus(Itinerary.ItineraryStatus.planning);
                    return Response.ok(stateRep).build();
                } else {
                    // Some parts still booked!
                    TravelAgencyResource.addLockedLink(customer_ID, itinerary_ID, stateRep);
                    stateRep.setStatusInformation("Booking failed, some bookings have no been cancelled");
                    itinerary.setItineraryStatus(Itinerary.ItineraryStatus.failedCancel);
                    return Response.ok(stateRep).build();
                }
            }
          
            TravelAgencyResource.addBookingStateLink(customer_ID, itinerary_ID, stateRep);
            stateRep.setStatusInformation("Booking succesfull");
            itinerary.setItineraryStatus(Itinerary.ItineraryStatus.booked);
            return Response.ok(stateRep).build();
        }


        return Response.status(Status.NO_CONTENT).
                entity("Error: Customer was not found").build();
    }
}
