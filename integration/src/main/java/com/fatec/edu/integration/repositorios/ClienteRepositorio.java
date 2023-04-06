package com.fatec.edu.integration.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.edu.integration.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}