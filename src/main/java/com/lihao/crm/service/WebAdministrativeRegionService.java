package com.lihao.crm.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebAdministrativeRegionService {

	private final RestTemplate restTemplate;

	public WebAdministrativeRegionService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getProvince(String appkey) {
		String uri = "http://api.jisuapi.com/area/province?appkey=" + appkey;
		return restTemplate.getForObject(uri, String.class);
	}
	
	public String getCity(String appkey, int provinceId) {
		String uri = String.format("http://api.jisuapi.com/area/city?parentid=%d&appkey=%s",provinceId, appkey);
		return restTemplate.getForObject(uri, String.class);
	}

}
