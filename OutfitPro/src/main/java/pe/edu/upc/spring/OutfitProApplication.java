package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OutfitProApplication implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OutfitProApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("preciso2020"));
		System.out.println(passwordEncoder.encode("cliente2020"));
		System.out.println(passwordEncoder.encode("asesor2020"));
	}
}
