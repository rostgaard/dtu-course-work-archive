package com.example.prototypemoniterapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.datatypes.App;
import com.example.datatypes.Device;
import com.example.datatypes.Event;
import com.example.datatypes.EventType;

/**
 * 
 * @author s103459 (Peter), s103470 (Nicolai P)
 *
 */

public class WebServiceConnection {
	
	private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/SmartHomeSecurity/rest";
	
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
	
	public static List<App> invokeGetAppByMac(String mac){
		final String url = BASE_URL + "/apps/getApps?mac={mac}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		App[] apps = restTemplate.getForObject(url, App[].class, parameters);
		return Arrays.asList(apps);
	}
	
	public static int invokeGetLatestVideoID(int id) {
		final String url = BASE_URL + "/video/getLatest?id={id}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("id", String.valueOf(id));
		RestTemplate restTemplate = new RestTemplate(true);
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return Integer.parseInt(restTemplate.getForObject(url, String.class, parameters));
	}
	
	public static Device invokeGetDeviceByMac(String mac) {
		final String url = BASE_URL + "/devices/getDevice?mac={mac}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		RestTemplate restTemplate = new RestTemplate(true);
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Device.class, parameters);
	}
}
