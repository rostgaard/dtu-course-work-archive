package web.services;

import java.util.ArrayList;
import java.util.List;

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
import dto.model.EventType;
import eao.model.Conversion;
import eao.model.SensorDataEAO;
import entity.model.AppEntity;

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

}
