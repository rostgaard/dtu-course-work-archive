package web.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dto.model.App;
import dto.model.AppList;
import eao.model.Conversion;
import eao.model.SensorDataEAO;
import entity.model.AppEntity;
import enums.EventType;

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
	
	/*
	 * @Author s124255
	 */
	@GET
	@Path("/getDevices")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getDevices(){
		List<App> apps = this.getAllEventList();
		Set<String> macAddr = new HashSet<>();
		List<String> device = new ArrayList<String>();
		
		for(App app : apps) {
			macAddr.add(app.getMac());
		}
		
		for(String dev : macAddr){
			device.add(dev);
		}
		
		return device;

	}
	
	@GET
	@Path("/updateApp")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public App updateApp(@QueryParam("mac") String mac, @QueryParam("eventType") EventType eventType, @QueryParam("status") boolean status) {
		return Conversion.convertAppEntity(eao.update(mac, eventType, status));
	}
	

}
