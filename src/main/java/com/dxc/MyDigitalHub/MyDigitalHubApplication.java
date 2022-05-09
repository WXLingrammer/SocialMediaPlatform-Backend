package com.dxc.MyDigitalHub;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.dxc.MyDigitalHub.entity.UserAuditor;
import com.dxc.MyDigitalHub.services.FileHandlerServices;

@EnableJpaAuditing(auditorAwareRef="auditorAware")
@SpringBootApplication
public class MyDigitalHubApplication implements CommandLineRunner {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new UserAuditor();
	}
	
	@Resource
	FileHandlerServices fileHandlerService;

	public static void main(String[] args) {
		SpringApplication.run(MyDigitalHubApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception {
//		fileHandlerService.deleteAll();
//		fileHandlerService.init();
	}

}
