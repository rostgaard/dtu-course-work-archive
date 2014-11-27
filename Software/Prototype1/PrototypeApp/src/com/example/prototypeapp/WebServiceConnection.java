package com.example.prototypeapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class WebServiceConnection {
	
	private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/WebPrototype/rest";
	//private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest";
	//private static final String BASE_URL = "http://se-se2-e14-glassfish-c.compute.dtu.dk:8080/TechnologyExperiment/rest";
	//private static final String BASE_URL = "http://10.16.163.41:8080/TechnologyExperiment/rest";
	
	public static Event invokeAddEventWebServer(String mac, int value, EventType eventType) {
		final String url = BASE_URL + "/events/addEventByMac?mac={mac}&value={value}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		parameters.put("value", String.valueOf(value));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Event.class, parameters);
	}
	
	public static Event invokeAwaitEventWebServer(String mac, EventType eventType) {
		final String url = BASE_URL + "/events/awaitEventByMac?mac={mac}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Event.class, parameters);
	}
	
	public static void invokeAddAppToDatabase(String mac, EventType eventType){
		final String url = BASE_URL + "/apps/addApp?mac={mac}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		restTemplate.getForObject(url, App.class, parameters);
	}
	
	public static List<App> invokeGetAppByMac(String mac){
		final String url = BASE_URL + "/apps/getApps?mac={mac}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		App[] apps = restTemplate.getForObject(url, App[].class, parameters);
		return Arrays.asList(apps);
	}

}
