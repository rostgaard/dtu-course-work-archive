package com.dtu.cameraapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author s123673 (Jan-Eric)
 *
 */
public class WebServiceConnection {
	
	private static final String BASE_URL = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/SmartHomeSecurity/rest";
	
	public static Event invokeAddEventWebServer(String mac, float value, EventType eventType) {
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
	
	/**

	* Takes a video file and camera id, converts the file to a byte array and sends

	* the video ad a byte array as a http post.

	* @param File	The video file that has to be send to the server

	* @param id The camera id, that is used in order to identify the source of the video

	* @return the http response code

	*/
	public static int invokeGetApp(String mac, EventType eventType){
		final String url = BASE_URL + "/apps/getApp?mac={mac}&eventType={eventType}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("mac", String.valueOf(mac));
		parameters.put("eventType", String.valueOf(eventType));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return (restTemplate.getForObject(url, App.class, parameters)).getId();
	}

}
