package com.vendas.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.api.entities.Venda;

@Transactional(readOnly = true)
public interface VendaRepository extends JpaRepository<Venda, Integer> {
	@Query("SELECT vd FROM Venda vd WHERE vd.vendedor.id = :vendedorId")
	List<Venda> findByVendedorId(@Param("vendedorId") int vendedorId);
}
