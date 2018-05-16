package com.saurin.demojwt;

import com.saurin.demojwt.model.Account;
import com.saurin.demojwt.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.stream.Stream;

@SpringBootApplication
@EnableAuthorizationServer
public class DemoJwtApplication {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoJwtApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(AccountRepository accountRepository){
		return args -> {
			Stream.of("saurin,password")
					.map(tpl -> tpl.split(","))
					.forEach(tpl -> accountRepository.save(new Account(tpl[0],
							(bCryptPasswordEncoder.encode(tpl[1])), true)));
		};
	}
}
