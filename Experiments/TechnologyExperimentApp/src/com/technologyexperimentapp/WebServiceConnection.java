package com.technologyexperimentapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class WebServiceConnection {
	
	private static final String BASE_URL = "http://192.168.87.111:8080/Assignment4/rest/events";
	
	public static Event invokeAddEventWebServer(int id, float value) {
		final String url = BASE_URL + "/addEvent?id={id}&value={value}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("id", String.valueOf(id));
		parameters.put("value", String.valueOf(value));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Event.class, parameters);
	}
	
	public static Event invokeAwaitEventWebServer(int id) {
		final String url = BASE_URL + "/awaitEvent?id={id}";
		final Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("id", String.valueOf(id));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate.getForObject(url, Event.class, parameters);
	}

}
