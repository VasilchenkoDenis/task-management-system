package com.denisvasilchenko.tms;

import com.denisvasilchenko.tms.model.User;
import com.denisvasilchenko.tms.security.service.JwtService;
import com.denisvasilchenko.tms.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsApplication.class, args);}

		@Bean
		public CommandLineRunner commandLineRunner(
				UserService userService,
				JwtService jwtService
	) {
			return args -> {
				User user = User.builder()
						.email("default_user@mail.test")
						.password("default-user-password")
						.username("Default User")
						.build();
				try {
					user=userService.save(user);
				} catch (Exception ignored){}
				System.out.println("Demo user token: " + jwtService.generateToken(user));
			};
		}
	}

