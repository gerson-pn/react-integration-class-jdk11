package com.fatec.edu.integration.modelos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatec.edu.integration.entidades.Cliente;

@Component
public class ClienteSelecionador {
	public Cliente selecionar(List<Cliente> clientes, long id) {
		Cliente selecionado = null;
		for (Cliente cliente : clientes) {
			if (cliente.getId() == id) {
				selecionado = cliente;
			}
		}
		return selecionado;
	}
}