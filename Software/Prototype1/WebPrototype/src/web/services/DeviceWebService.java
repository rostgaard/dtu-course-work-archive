package web.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dto.model.Device;
import eao.model.Conversion;
import eao.model.DevicesEAO;
import entity.model.DeviceEntity;

/**
 * 
 * @author s124255
 *
 */
@LocalBean
@Stateless
@Path("/devices")
public class DeviceWebService {
	@EJB DevicesEAO eao;
	
	
	@GET
	@Path("/addDeviceName")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Device addDevice(@QueryParam("mac") String mac, @QueryParam("name") String name) {	
		DeviceEntity deviceEntity = eao.addDeviceName(mac, name);

		Device device = Conversion.convertDeviceEntity(deviceEntity);
				
		return device;
	}
	
	@GET
	@Path("/getDevice")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Device getDeviceName(@QueryParam("mac") String mac) {		
		DeviceEntity deviceEntity = eao.getDevice(mac);
		if (deviceEntity == null) {
			return null;
		}

		return Conversion.convertDeviceEntity(deviceEntity);
	}

}
