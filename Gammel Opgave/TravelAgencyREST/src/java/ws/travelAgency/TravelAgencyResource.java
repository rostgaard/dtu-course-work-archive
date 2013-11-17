package ws.travelAgency;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.lameduck.Flight;
import ws.lameduck.FlightInformation;
import ws.lameduck.LameDuckWSDLPortType;
import ws.lameduck.LameDuckWSDLService;
import ws.niceview.BookingType;
import ws.niceview.NiceViewWSDLPortType;
import ws.niceview.NiceViewWSDLService;
import ws.niceview.ReturnHotelList;
import ws.travelAgency.data.Customer;
import ws.travelAgency.data.FlightInfoWrapper;
import ws.travelAgency.data.HotelWrapper;
import ws.travelAgency.data.Itinerary;
import ws.travelAgency.representation.ItineraryRepresentation;
import ws.travelAgency.representation.Representation;
import ws.travelAgency.representation.Link; 
import ws.travelAgency.representation.StateRepresentation;
import ws.travelAgency.data.Status;

/**
 * REST Web Service
 *
 * @author Andreas
 */
@Path("TravelAgency/{cID}/{itiID}")
public class TravelAgencyResource {
  
    static final String REL = "http://travelagency.ws/relations/";

    static final String BOOKING_REL = REL + "booking";
    static final String STATUS_REL = REL + "status";
    static final String FLIGHT_REL = REL + "flights";
    static final String HOTEL_REL = REL + "hotels";
    static final String SELF_REL = REL + "self";

    static final String BASE_URI = "http://localhost:8080/TravelAgencyREST/resources/TravelAgency";

    static final String MEDIATYPE_ITINERARYPROCESS = "application/itineraryprocess+xml";
    
    static LameDuckWSDLService flight_service = new LameDuckWSDLService();
    static LameDuckWSDLPortType flight_port = flight_service.getLameDuckWSDLPort();

    static NiceViewWSDLService hotel_service = new NiceViewWSDLService();
    static NiceViewWSDLPortType hotel_port = hotel_service.getNiceViewWSDLPort();

    static HashMap<String, Customer> customers = new HashMap<String, Customer>();

    public static void reset() {
        customers.clear();
        Customer.reset();
        updateCustomers();
    }

    public static void updateCustomers() {
        if (customers.isEmpty()) {
            Customer c1 = new Customer("Tick Joachim", 50408824, 2, 11);
            Itinerary i1 = new Itinerary();
            i1.setID(0);

            XMLGregorianCalendar date1 = new XMLGregorianCalendarImpl();
            date1.setMonth(10);
            date1.setDay(2);
            date1.setYear(2012);

            XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
            date2.setMonth(9);
            date2.setDay(24);
            date2.setYear(2012);


            ReturnHotelList returned_hotels = hotel_port.getHotels("New York", date2, date1);
            List<BookingType> hotels = returned_hotels.getHotels();
            if (hotels.size() > 0) {
                HotelWrapper hotelWrapper = new HotelWrapper();
                hotelWrapper.setHotel(hotels.get(0));
                Status status = new Status();
                status.setStatusCode(Status.StatusCode.unconfirmed);
                hotelWrapper.setStatus(status);
                i1.addHotel(hotelWrapper);
            }


            FlightInformation flightInfo = new FlightInformation();
            flightInfo.setAirlineReservationService("SAS");
            flightInfo.setBookingNumber(123);
            flightInfo.setPrice(100);

            Flight flight = new Flight();

            flight.setStartAirport("Copenhagen");
            flight.setEndAirport("Stockholm");
            flight.setCarrier("SAS");

            

            flight.setLanding(date1);
            flight.setLiftOff(date2);

            flightInfo.setFlight(flight);

            FlightInfoWrapper flightWrapper = new FlightInfoWrapper();
            flightWrapper.setFlightInformation(flightInfo);

            Status status = new Status();
            status.setStatusCode(Status.StatusCode.unconfirmed);
            flightWrapper.setStatus(status);

            i1.addFlight(flightWrapper);

            c1.addItinerary(i1);

            customers.put("" + c1.getId(), c1);

            Customer c2 = new Customer("Anne Strandberg", 50408816, 5, 9);
            customers.put("" + c2.getId(), c2);
            Customer c3 = new Customer("Klinkby Poul",50408817,3,10);
            customers.put("" + c3.getId(), c3);
            Customer c4 = new Customer("Donovan Jasper",50408818,6,9);
            customers.put("" + c4.getId(), c4);
            Customer c5 = new Customer("Dirach Anne-Louise",50408819,1,10);
            customers.put("" + c5.getId(), c5);
            Customer c6 = new Customer("Brorson Bodil",50408820,7,11);
            customers.put("" + c6.getId(), c6);
            Customer c7 = new Customer("Bruhn Brigitte",50408821,2,10);
            customers.put("" + c7.getId(), c7);
            Customer c8 = new Customer("Bech Camilla",50408822,7,9);
            customers.put("" + c8.getId(), c8);
            Customer c9 = new Customer("Tobiasen Inge",50408823,9,10);
            customers.put("" + c9.getId(), c9);
            Customer c10 = new Customer("Thor-Jensen Claus",50408825,5,9);
            customers.put("" + c10.getId(), c10);
            
            
            
        }
    }

    public TravelAgencyResource() {
        updateCustomers();
    }

    @POST
    @Produces(MEDIATYPE_ITINERARYPROCESS)
    public Response createItinerary(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID) {
        StateRepresentation stateRep = new StateRepresentation();
        Customer customer = customers.get(customer_ID);
        if (customer != null) {
            Itinerary iti = customer.getItinerary(itinerary_ID);
            if (iti != null) {
                // No links return as creation failed,
                // i.e. an itinerary already existed.
                return Response.status(Response.Status.CONFLICT).
                        entity("Itinerary already created").build();
            } else {
                Itinerary itinerary = new Itinerary();
                itinerary.setID(Integer.parseInt(itinerary_ID));

                customer.addItinerary(itinerary);

                addPlanningLinks(customer_ID, itinerary_ID, stateRep);
                stateRep.setStatusInformation("Itinerary succesfully created!");
                return Response.ok(stateRep).build(); 
            }
        }
        // TODO: Should maybe be moved to another location!
        return Response.status(Response.Status.NOT_FOUND).
                entity("Customer is not in the system").build();
    }

    @GET
    @Produces(MEDIATYPE_ITINERARYPROCESS)
    public Response getItinerary(@PathParam("cID") String customer_ID,
            @PathParam("itiID") String itinerary_ID) {
        // Will be used in case that no itinerary is found!
        StateRepresentation stateRep = new StateRepresentation();
        XMLGregorianCalendar blocking_date = getCurrentDate(); 

        Customer customer = customers.get(customer_ID);
        if (customer == null) {
            stateRep.setStatusInformation("Customer with ID " + customer_ID + " not found");
            return Response.ok(stateRep).build(); 
        }

        Itinerary itinerary = customer.getItinerary(itinerary_ID);
        if (itinerary == null) {
            stateRep.setStatusInformation("Itinerary for customer ID " + customer_ID +
                    " and itinerary ID " + itinerary_ID + " was not found");
            addSelfLink(customer_ID, itinerary_ID, stateRep);
            return Response.ok(stateRep).build(); 
        } else if (itinerary.getStatus() == Itinerary.ItineraryStatus.failedCancel && itinerary.getStartDate().compare(blocking_date) == DatatypeConstants.LESSER) {
            customer.removeItinerary(itinerary_ID);
            stateRep.setStatusInformation("Itinerary has been removed");
            return Response.ok(stateRep).build(); 
        }

        ItineraryRepresentation itiRep = new ItineraryRepresentation();
        itiRep.setItinerary(itinerary);

        if (itinerary.getStatus() == Itinerary.ItineraryStatus.planning) {
            addPlanningLinks(customer_ID, itinerary_ID, itiRep);
        } else if (itinerary.getStatus() == Itinerary.ItineraryStatus.booked) {
            addBookingStateLink(customer_ID, itinerary_ID, itiRep);
        } else { // Can only get the itinerary (is in a locked state
            addLockedLink(customer_ID, itinerary_ID, itiRep);
        }
        return Response.ok(itiRep).build();
    }

    // Link management (methods to add links to representtations)
    static void addStatusLink(String cid, String oid, Representation representation) {
        Link link = new Link();
        link.setURI(String.format("%s/%s/%s/Status/", BASE_URI, cid, oid));
        link.setRel(STATUS_REL);
        link.setMediaType(MEDIATYPE_ITINERARYPROCESS);
        representation.addLink(link);
    }

    static void addBookingLink(String cid, String oid, Representation representation) {
        Link link = new Link();
        link.setURI(String.format("%s/%s/%s/Booking/", BASE_URI,cid,oid));
        link.setRel(BOOKING_REL);
        link.setMediaType(MEDIATYPE_ITINERARYPROCESS);
        representation.addLink(link);
    }

    static void addSelfLink(String cid, String oid, Representation representation) {
        Link link = new Link();
        link.setURI(String.format("%s/%s/%s/", BASE_URI,cid,oid));
        link.setRel(SELF_REL);
        link.setMediaType(MEDIATYPE_ITINERARYPROCESS);
        representation.addLink(link);
    }

    static void addFlightLink(String cid, String oid, Representation representation) {
        Link link = new Link();
        link.setURI(String.format("%s/%s/%s/Flights/", BASE_URI,cid,oid));
        link.setRel(FLIGHT_REL);
        link.setMediaType(MEDIATYPE_ITINERARYPROCESS);
        representation.addLink(link);
    }
    
    static void addHotelLink(String cid, String oid, Representation representation) {
        Link link = new Link();
        link.setURI(String.format("%s/%s/%s/Hotels/", BASE_URI,cid,oid));
        link.setRel(HOTEL_REL);
        link.setMediaType(MEDIATYPE_ITINERARYPROCESS);
        representation.addLink(link);
    }

    static void addLockedLink(String cid, String oid, Representation representation) {
        addSelfLink(cid, oid, representation);
    }
    static void addBookingStateLink(String cid, String oid, Representation representation) {
        addSelfLink(cid, oid, representation);
        addStatusLink(cid, oid, representation);
    }

    static void addPlanningLinks(String cid, String oid, Representation representation) {
        addBookingLink(cid, oid, representation);
        addHotelLink(cid, oid, representation);
        addFlightLink(cid, oid, representation);
        addStatusLink(cid, oid, representation);
        addSelfLink(cid, oid, representation);
    }



    static XMLGregorianCalendar getCurrentDate() {
        XMLGregorianCalendar day = new XMLGregorianCalendarImpl();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        day.setYear(cal.get(Calendar.YEAR));
        day.setMonth(cal.get(Calendar.MONTH)+1);
        day.setDay(cal.get(Calendar.DAY_OF_MONTH));
        
        return day;
    }
}
