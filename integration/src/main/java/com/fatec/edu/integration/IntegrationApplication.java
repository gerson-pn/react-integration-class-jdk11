package com.fatec.edu.integration;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.fatec.edu.integration.entidades.Cliente;
import com.fatec.edu.integration.repositorios.ClienteRepositorio;

@SpringBootApplication
public class IntegrationApplication {

	public static void main(String[] args) {
		// SpringApplication.run(IntegrationApplication.class, args);
		Map<String, Object> appConfiguration = new HashMap<>();
		appConfiguration.put("server.port", "5555");

		SpringApplication app = new SpringApplication(IntegrationApplication.class);
		app.setDefaultProperties(appConfiguration);
		app.run(args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public ClienteRepositorio repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2002, 05, 15);

			for (int i = 0; i < 2; i++) {
				Cliente cliente = new Cliente();
				cliente.setNome("Pedro Alcântara de Bragança e Bourbon");
				cliente.setDataCadastro(Calendar.getInstance().getTime());
				cliente.setDataNascimento(calendario.getTime());
				cliente.setNomeSocial("Dom Pedro");
				repositorio.save(cliente);
				Cliente cliente2 = new Cliente();
				cliente2.setNome("Teresa Cristina de Bourbon-Duas Sicílias");
				cliente2.setDataCadastro(Calendar.getInstance().getTime());
				cliente2.setDataNascimento(calendario.getTime());
				cliente2.setNomeSocial("Mãe dos Brasileiros");
				repositorio.save(cliente2);
				Cliente cliente3 = new Cliente();
				cliente3.setNome("Isabel Cristina Leopoldina Augusta Gonzaga de Bourbon e Bragança");
				cliente3.setDataCadastro(Calendar.getInstance().getTime());
				cliente3.setDataNascimento(calendario.getTime());
				cliente3.setNomeSocial("Pricesa Isabel");
				repositorio.save(cliente3);
				Cliente cliente4 = new Cliente();
				cliente4.setNome("Leopoldina Teresa Gonzaga de Bragança e Bourbon-Duas Sicílias");
				cliente4.setDataCadastro(Calendar.getInstance().getTime());
				cliente4.setDataNascimento(calendario.getTime());
				cliente4.setNomeSocial("Pricesa Leopoldina");
				repositorio.save(cliente4);
			}
		}
	}
}
