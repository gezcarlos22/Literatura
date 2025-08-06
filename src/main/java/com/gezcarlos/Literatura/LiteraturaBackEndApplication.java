package com.gezcarlos.Literatura;

<<<<<<< HEAD
import com.gezcarlos.Literatura.principal.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class LiteraturaBackEndApplication implements CommandLineRunner{

	@Autowired
	Principal principal;
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaBackEndApplication {
>>>>>>> e65ddec91a6f3d4754b0b55cc86159c9a47de97e

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaBackEndApplication.class, args);
	}

<<<<<<< HEAD
	@Override
	public void run(String... args) throws Exception {
		principal.muestraMenu();
	}
=======
>>>>>>> e65ddec91a6f3d4754b0b55cc86159c9a47de97e
}
