package org.example.HCDD_412_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Hcdd412ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hcdd412ProjectApplication.class, args);
	}

	@GetMapping("/")
	public String viewRoot() {
		return "redirect:/games/game_list";
	}

	@Bean
	public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder()
	{
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
}
