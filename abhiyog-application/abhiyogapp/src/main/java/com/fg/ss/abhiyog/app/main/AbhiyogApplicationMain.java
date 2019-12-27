package com.fg.ss.abhiyog.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fg.ss.abhiyog.common.config.FileStorageProperties;

@SpringBootApplication
@EntityScan(basePackages = {"com.fg.ss.abhiyog"}) 
@ComponentScan("com.fg.ss.abhiyog")
@EnableJpaRepositories("com.fg.ss.abhiyog")
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class AbhiyogApplicationMain {

	 public static void main(String[] args) {
	        SpringApplication.run(AbhiyogApplicationMain.class, args);
	    }
}
