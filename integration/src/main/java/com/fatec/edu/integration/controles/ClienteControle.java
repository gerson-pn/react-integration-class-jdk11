package com.fatec.edu.integration.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.edu.integration.entidades.Cliente;
import com.fatec.edu.integration.modelos.AdicionadorLinkCliente;
import com.fatec.edu.integration.modelos.ClienteAtualizador;
import com.fatec.edu.integration.modelos.ClienteSelecionador;
import com.fatec.edu.integration.repositorios.ClienteRepositorio;

@CrossOrigin
@RestController
public class ClienteControle {
	@Autowired
	private ClienteRepositorio repositorio;
	@Autowired
	private ClienteSelecionador selecionador;
	@Autowired
	private AdicionadorLinkCliente adicionadorLink;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> obterClientes() {
		List<Cliente> clientes = repositorio.findAll();
		if (clientes.isEmpty()) {
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(clientes,HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(clientes);
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(clientes, HttpStatus.OK);
			return resposta;
		}
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> obterCliente(@PathVariable long id) {
		List<Cliente> clientes = repositorio.findAll();
		Cliente cliente = selecionador.selecionar(clientes, id);
		if (cliente == null) {
			ResponseEntity<Cliente> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(cliente);
			ResponseEntity<Cliente> resposta = new ResponseEntity<Cliente>(cliente, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cliente/cadastro")
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (cliente.getId() == null) {
			repositorio.save(cliente);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);

	}

	@SuppressWarnings("deprecation")
	@PutMapping("/cliente/atualizar")
	public ResponseEntity<?> atualizarCliente(@RequestBody Cliente atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Cliente cliente = repositorio.getById(atualizacao.getId());
		if (cliente != null) {
			ClienteAtualizador atualizador = new ClienteAtualizador();
			atualizador.atualizar(cliente, atualizacao);
			repositorio.save(cliente);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}

	@SuppressWarnings("deprecation")
	@DeleteMapping("/cliente/excluir")
	public ResponseEntity<?> excluirCliente(@RequestBody Cliente exclusao) {
		Cliente cliente = repositorio.getById(exclusao.getId());
		repositorio.delete(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
