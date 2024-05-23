package com.manager.state;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.manager.state.model.Server;
import com.manager.state.model.Status;
import com.manager.state.repo.ServerRepo;

@SpringBootApplication
public class StateApplication {

	public static void main(String[] args) {
		SpringApplication.run(StateApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(ServerRepo serverRepo){
	// 	return args->{
	// 		serverRepo.save(new Server(null, "192.168.1.160","Ubntu Linux","16 GB","Persional PC","http://localhost:8080/server/image/server1.png",Status.SERVER_UP));
	// 		serverRepo.save(new Server(null, "192.168.1.58","Fedora Linux","16 GB","Dell PC","http://localhost:8080/server/image/server2.png",Status.SERVER_DOWN));
	// 		serverRepo.save(new Server(null, "192.168.1.21","MS 2008","32 GB","Web Server","http://localhost:8080/server/image/server3.png",Status.SERVER_UP));
	// 		serverRepo.save(new Server(null, "192.168.1.14","Red Linux","64 GB","Mail Server","http://localhost:8080/server/image/server1.png",Status.SERVER_DOWN));
	// 	};
	// }

	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin"," Access-Control-Allow-Origin","Content-Type",
		"Accept","Jwt-Token","Authorization","Origin, Accept","X-Requested-With", 
		"Access-Control-Request-Method","Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin","COntent-Type","Accept","Jwt-Token","Authorization",
		"Access-Control-Allow-Origin","Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
