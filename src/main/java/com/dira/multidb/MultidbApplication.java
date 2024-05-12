package com.dira.multidb;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MultidbApplication {

	// // run standard spring boot
	// public static void main(String[] args) {
	// 	SpringApplication.run(MultidbApplication.class, args);
	// }

	
	// run with custom aplication context
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MultidbApplication.class);

		// add custom settings aplication
		// contoh mematikan text banner
		// application.setBannerMode(Banner.Mode.OFF);

		// run application context
		ConfigurableApplicationContext applicationContext = application.run(args);

		// additional commands
		// contoh melihat bean (object yang dikelola didalam container spring) "database1DataSourceProperties"
		DataSourceProperties database1DataSourceProperties = applicationContext.getBean("database1DataSourceProperties", DataSourceProperties.class);
		System.out.println(database1DataSourceProperties);
	}

}
