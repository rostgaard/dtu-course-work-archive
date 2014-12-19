package web.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dto.model.App;
import dto.model.AppList;
import dto.model.Event;
import eao.model.Conversion;
import eao.model.SensorDataEAO;
import entity.model.AppEntity;
import enums.EventType;

/**
 * @author Luai s113444
 */

@LocalBean
@Stateless
@Path("/apps")
public class AppWebService {
	@EJB SensorDataEAO eao;

	@GET
	@Path("/addApp")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public App addApp(@QueryParam("mac") String mac, @QueryParam("eventType") EventType eventType) {
		AppEntity appEntity = eao.addApp(mac, eventType);
		if (appEntity == null) {
			return null;
		}

		return Conversion.convertAppEntity(appEntity);	
	}

	@GET
	@Path("/getApp")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public App getApp(@QueryParam("mac") String mac, @QueryParam("eventType") EventType eventType) {		
		AppEntity appEntity = eao.getAppEntity(mac, eventType);
		if (appEntity == null) {
			return null;
		}

		return Conversion.convertAppEntity(appEntity);
	}

	@GET
	@Path("/getAppByID")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public App getApp(@QueryParam("id") int id) {		
		AppEntity appEntity = eao.getAppEntity(id);
		if (appEntity == null) {
			return null;
		}

		return Conversion.convertAppEntity(appEntity);
	}

	@GET
	@Path("/getApps")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getApps(@QueryParam("mac") String mac) {
		return Conversion.convertAppEntityList(eao.getAppEntitylist(mac));
	}

	@GET
	@Path("/getAllApps")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAllEventList() {
		List<AppEntity> appEntities = new ArrayList<AppEntity>();
		List<App> apps = new ArrayList<App>();
		appEntities = eao.getAllAppEntitylist();
		apps = Conversion.convertAppEntityList(appEntities);
		return apps;
	}
	
	@GET
	@Path("/getAppList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public AppList getAppList() {
		List<AppEntity> appEntities = new ArrayList<AppEntity>();
		List<App> apps = new ArrayList<App>();
		appEntities = eao.getAllAppEntitylist();
		apps = Conversion.convertAppEntityList(appEntities);
		AppList appList = new AppList(apps);
		return appList;
	}
	
	@DELETE
	@Path("/deleteApp")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void deleteApp(@QueryParam("id") int id) {
		if (id == 0) {
			List<AppEntity> appEntities = eao.getAllAppEntitylist();
			eao.deleteApps(appEntities);
		} else {
			eao.deleteApp(id);
		}
	}
	
	/*
	 * @Author s124259 s084283
	 */
	@GET
	@Path("/getDeviceCount")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public String getDeviceCount(){
		List<App> apps = this.getAllEventList();
		Set<String> macAddr = new HashSet<>();
		
		for(App app : apps) {
			macAddr.add(app.getMac());
		}
		
		return Integer.toString(macAddr.size());

	}
	
	/**
	 * @author s124255
	 * @return A JSON object containing a list of all the unique mac addresses currently registered with an App
	 */
	@GET
	@Path("/getDevices")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getDevices(){
		List<App> apps = getAllEventList();
		Set<String> uniqueMACAddr = new HashSet<>();
		List<App> uniqueDevices = new ArrayList<App>();
		
		for(App app : apps) {
			uniqueMACAddr.add(app.getMac());
		}
	
		for(String mac : uniqueMACAddr){
			uniqueDevices.add(new App(0, mac, EventType.FLASHLIGHT, true, new ArrayList<Event>()));
		}
		
		return uniqueDevices;
	}
	
	/**
	 * @author s124259
	 */
	@GET
	@Path("/getDevicesWithCamera")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getDevicesWithCamera(){
		
		List<App> apps = getAllEventList();
		List<App> devicesWithCamera = new ArrayList<App>();
		
		for(App app : apps) {
			if(app.getEventType().equals(EventType.STARTVIDEORECORDING))
				devicesWithCamera.add(app);
		}
		return devicesWithCamera;
	}
	
	
	/**
	 * @author s124255
	 * @param mac The mac address of the device which should be updated
	 * @param eventType The enum EventType of which type of event the App to be updated is producing
	 * @param status The new status of the App
	 */
	@PUT
	@Path("/updateApp")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void updateApp(@QueryParam("mac") String mac, @QueryParam("eventType") EventType eventType, @QueryParam("status") boolean status) {
		Conversion.convertAppEntity(eao.update(mac, eventType, status));
	}
	

}
