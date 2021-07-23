package com.vendas.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vendas.api.entities.Vendedor;
import com.vendas.api.repositories.VendedorRepository;
import com.vendas.api.utils.ExceptionUtil;

@Service
public class VendedorService {
	private static Logger log = LoggerFactory.getLogger(VendedorService.class);
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	public Optional<Vendedor> buscaId(int id) throws ExceptionUtil {
		log.info("Buscando Vendedor (ID:{})", id);
		
		Optional<Vendedor> vendedor = vendedorRepository.findById(id);
		
		if(!vendedor.isPresent()) {
			log.info("O vendedor procurado não existe... (ID:{})", id);
			throw new ExceptionUtil("O vendedor procurado não existe... (ID:{})", id);
		}
		return vendedor;
	}
	
	public Optional<List<Vendedor>> buscaNome(String nome) throws ExceptionUtil {
		log.info("Buscando Vendedores com o Nome: {}", nome);
		
		Optional<List<Vendedor>> listaVendedores = Optional.ofNullable(vendedorRepository.findByNome(nome));
		
		if(!listaVendedores.isPresent() || listaVendedores.get().size() < 1) {
			log.info("Nenhum resultado encontrado... (Nome:{})", nome);
			throw new ExceptionUtil("Nenhum resultado encontrado... (Nome:{})", nome);
		}
		return listaVendedores;
	}
	
	public Vendedor salvar(Vendedor vendedor) throws ExceptionUtil {
		log.info("Salvando... {}", vendedor);
		
		if (vendedor.getId() > 0)
			buscaId(vendedor.getId());
		try {
			return vendedorRepository.save(vendedor);
		} catch (DataIntegrityViolationException e) {
			log.info("Este usuário já está cadastrado...");
			throw new ExceptionUtil("Este usuário já está cadastrado...");
		}
	}
}
