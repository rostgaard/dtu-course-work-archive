package ws.travelAgency;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
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


import ws.lameduck.FlightInformations;
import ws.lameduck.FlightInformation;
import ws.travelAgency.representation.FlightRepresentation;
import ws.travelAgency.data.Customer;
import ws.travelAgency.data.FlightInfoWrapper;
import ws.travelAgency.data.Itinerary;
import ws.travelAgency.data.Status.StatusCode;
import ws.travelAgency.representation.StateRepresentation;

/**
 *
 * @author Andreas
 */
@Path("TravelAgency/{cID}/{itiID}/Flights/")
public class TravelAgencyFlightsResource {

    @PUT
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    @Consumes(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response addFlight(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID, FlightInformation flight_Info) {

        Customer customer = TravelAgencyResource.customers.get(customer_ID);

        Itinerary itinerary = customer.getItinerary(itinerary_ID);

        FlightInfoWrapper flight = new FlightInfoWrapper();
        flight.getStatus().setStatusCode(StatusCode.unconfirmed);
        flight.setFlightInformation(flight_Info);

        itinerary.addFlight(flight);

        StateRepresentation stateRep = new StateRepresentation();

        TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, stateRep);
        stateRep.setStatusInformation("Flight added to booking");
        return Response.ok(stateRep).build();
    }

    @GET
    @Produces(TravelAgencyResource.MEDIATYPE_ITINERARYPROCESS)
    public Response getFlights(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID, @QueryParam("startAirport") String startAirport,
            @QueryParam("endAirport") String endAirport,
            @QueryParam("day") String day, @QueryParam("month") String month,
            @QueryParam("year") String year) {

        StateRepresentation stateRep = new StateRepresentation();
        TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, stateRep);

        XMLGregorianCalendar date = new XMLGregorianCalendarImpl();
        date.setDay(Integer.parseInt(day));
        date.setMonth(Integer.parseInt(month));
        date.setYear(Integer.parseInt(year));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<FlightInformations> future = executor.submit(new GetFlights(startAirport, endAirport, date));
        FlightRepresentation flightRepresentation = new FlightRepresentation();

        try { // Call Web Service Operation
            FlightInformations flightInformations = future.get(5, TimeUnit.SECONDS);
            if (flightInformations != null) {
                List<FlightInformation> result = flightInformations.getFlightInformation();

                TravelAgencyResource.addPlanningLinks(customer_ID, itinerary_ID, flightRepresentation);
                for (FlightInformation flightInformation : result) {
                    flightRepresentation.addFlightInformation(flightInformation);
                }
            }

            return Response.ok(flightRepresentation).build();

        } catch (Exception ex) {
            // A timeout occured, meaning we return an empty list
            return Response.ok(flightRepresentation).build();
        }
    }

    private class GetFlights implements Callable<FlightInformations> {

        String startAirport;
        String endAirport;
        XMLGregorianCalendar date;

        public GetFlights(String startAirport, String endAirport, XMLGregorianCalendar date) {
            this.startAirport = startAirport;
            this.endAirport = endAirport;
            this.date = date;
        }

        @Override
        public FlightInformations call() {
            try {
                FlightInformations flightInformations = TravelAgencyResource.flight_port.getFlight(startAirport,
                        endAirport,
                        date);
                return flightInformations;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
