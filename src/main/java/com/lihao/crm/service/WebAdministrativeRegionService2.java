package com.lihao.crm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.lihao.crm.entity.SysUser;

import reactor.core.publisher.Mono;

@Service
public class WebAdministrativeRegionService2 {

	private final WebClient webClient;

	public WebAdministrativeRegionService2(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:8080").build();
	}

	public Mono<SysUser> someRestCall(String name) {
//		return webClient.get().uri("/admin/loadAllSysUser").retrieve().bodyToMono(SysUser.class);
		return webClient.get().uri("/admin/loadAllSysUser").retrieve().bodyToMono(SysUser.class);
	}

}
