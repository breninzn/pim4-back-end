package br.com.pim.hotelaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
@EnableCaching
public class HotelariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelariaApplication.class, args);
	}

}
