package com.vendas.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
 
import com.vendas.api.entities.Venda;
import com.vendas.api.repositories.VendaRepository;
import com.vendas.api.repositories.VendedorRepository;
import com.vendas.api.utils.ExceptionUtil;

@Service
public class VendaService {
	private static final Logger log = LoggerFactory.getLogger(VendaService.class);
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	public Optional<Venda> buscaId(int id) throws ExceptionUtil {
		log.info("Buscando venda... (ID:{})", id);
		 
     	Optional<Venda> venda = vendaRepository.findById(id);

     	if (!venda.isPresent()) {

            	log.info("A venda procurada não existe... (ID:{})", id);
            	throw new ExceptionUtil("A venda procurada não existe... (ID:{})", id);

     	}

     	return venda;

	}
	
	public Optional<List<Venda>> buscaIdVendedor(int vendedorId) throws ExceptionUtil {
		 
     	log.info("Buscando vendas realizadas pelo vendedor... (ID do Vendedor: {})", vendedorId);

     	Optional<List<Venda>> listaVendas = Optional.ofNullable(vendaRepository.findByVendedorId(vendedorId));

     	if (!listaVendas.isPresent() || listaVendas.get().size() < 1) {

            	log.info("Nenhuma venda realizada encontrada para o vendedor buscado (ID do Vendedor: {})", vendedorId);
            	throw new ExceptionUtil("Nenhuma venda realizada encontrada para o vendedor buscado (ID do Vendedor: {})", vendedorId);

     	}

     	return listaVendas;

	}
	
	public Venda salvar(Venda venda) throws ExceptionUtil {
		
     	log.info("Salvando a venda... {}", venda);

     	if (!vendedorRepository.findById(venda.getVendedor().getId()).isPresent()) {

            	log.info("Nenhum vendedor encontrado... (ID do Vendedor: {})", venda.getVendedor().getId());
            	throw new ExceptionUtil("Nenhum vendedor encontrado... (ID do Vendedor: {})", venda.getVendedor().getId());

     	}

     	if (venda.getId() > 0)
            	buscaId(venda.getId());

     	try {
     		
     		    venda.getVendedor().setTotalVenda(+1);
     		    venda.getVendedor().setMediaVenda(venda.getVendedor().getTotalVenda() / 2);
            	return vendaRepository.save(venda);

     	} catch (DataIntegrityViolationException e) {

            	log.info("Essa venda já foi cadastrada...");
            	throw new ExceptionUtil("Essa venda já foi cadastrada...");
            	
     	}

	}

}
