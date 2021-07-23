package com.vendas.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.api.entities.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
	@Transactional(readOnly = true)
	List<Vendedor> findByNome(String nome);
}
