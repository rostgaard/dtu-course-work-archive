package com.example.prototypemoniterapp;

//import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.datatypes.App;
import com.example.datatypes.Event;
import com.example.datatypes.EventType;

public class WebServiceConnection {
	
	private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/PrototypeTwo/rest";
//	private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/WebPrototype/rest";
//	private static final String BASE_URL = "http://10.16.170.22:8080/Prototype1/rest";
	
	public static List<App> invokeGetAppsFromWebServer() {
		final String url = BASE_URL + "/apps/getAllApps";
		RestTemplate restTemplate = new RestTemplate(true);
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		App[] apps = restTemplate.getForObject(url, App[].class);
		return Arrays.asList(apps);
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
}
