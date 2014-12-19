package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
 * @author s124255 (Nicolai K)
 *
 */
@LocalBean
@Stateless
@Path("/devices")
public class DeviceWebService {
	@EJB DevicesEAO eao;
	
	
	/**
	 * @author s124255
	 * @param mac
	 * The mac address of new device to be added.
	 * Typically in the format XX:XX:XX:XX:XX:XX
	 * @param name
	 * A String representation of the name belonging to the mac
	 * @return The JSON object representing the newly created device, if null is returned the mac already exists
	 */
	@GET
	@Path("/addDeviceName")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Device addDevice(@QueryParam("mac") String mac, @QueryParam("name") String name) {	
		DeviceEntity deviceEntity = eao.addDeviceName(mac, name);
		
		if(deviceEntity == null){
			return null;
		}

		Device device = Conversion.convertDeviceEntity(deviceEntity);
				
		return device;
	}
	
	/**
	 * @author s124255
	 * @param mac The mac address of the device to lookup
	 * @return The JSON object representing the device;
	 * Returns null if no device is found
	 */
	@GET
	@Path("/getDevice")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Device getDeviceName(@QueryParam("mac") String mac) {
		if(mac == "undefined" || mac == null){
			return null;
		}
		DeviceEntity deviceEntity = eao.getDevice(mac);
		if (deviceEntity == null) {
			return null;
		}

		return Conversion.convertDeviceEntity(deviceEntity);
	}
	
	/**
	 * @author s124255
	 * @param mac The mac address of the device which name should be updated
	 * @param name A String representation of the new name 
	 */
	@PUT
	@Path("/updateDeviceName")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void updateDeviceName(@QueryParam("mac") String mac, @QueryParam("name") String name) {
		Conversion.convertDeviceEntity(eao.updateName(mac, name));
	}
	
	/**
	 * @author s124255
	 * @return A JSON object containing the complete list of device data objects with names
	 */
	@GET
	@Path("/getDeviceList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Device> getDeviceList() {
			List<DeviceEntity> deviceEntities = new ArrayList<DeviceEntity>();
			List<Device> devices = new ArrayList<Device>();
			deviceEntities = eao.getAllDeviceEntitylist();
			devices = Conversion.convertDeviceEntityList(deviceEntities);
			return devices;
	}

}
