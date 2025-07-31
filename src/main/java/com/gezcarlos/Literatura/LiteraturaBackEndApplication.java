package com.gezcarlos.Literatura;

import com.gezcarlos.Literatura.principal.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class LiteraturaBackEndApplication implements CommandLineRunner{

	@Autowired
	Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.muestraMenu();
	}
}
