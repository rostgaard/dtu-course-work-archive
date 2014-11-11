package com.example.prototypeapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class WebServiceConnection {
	
	//private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/TechnologyExperimentService/rest/events";
	private static final String BASE_URL = "http://se-se2-e14-glassfish-c.compute.dtu.dk:8080/TechnologyExperiment/rest/events";
	
	public static Event invokeAddEventWebServer(String macAddress, float value, EventType eventType) {
		final String url = BASE_URL + "/addEvent?macAddress={macAddress}&value={value}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("macAddress", String.valueOf(macAddress));
		parameters.put("value", String.valueOf(value));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Event.class, parameters);
	}
	
	public static Event invokeAwaitEventWebServer(String macAddress, EventType eventType) {
		final String url = BASE_URL + "/awaitEvent?macAddress={macAddress}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("macAddress", String.valueOf(macAddress));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Event.class, parameters);
	}
	
	public static void invokeAddAppToDatabase(String macAddress, EventType eventType){
		final String url = BASE_URL + "/addAppToDB?macAddress={macAddress}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("macAddress", String.valueOf(macAddress));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		restTemplate.getForObject(url, Event.class, parameters);
	}

}
