package com.vendas.api.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.api.entities.Venda;
import com.vendas.api.services.VendaService;
import com.vendas.api.dtos.VendaDto;
import com.vendas.api.utils.ExceptionUtil;
import com.vendas.api.utils.ConvertUtil;
import com.vendas.api.response.Response;

@RestController
@RequestMapping("/api/venda")
@CrossOrigin(origins = "*")
public class VendaController {
	private static final Logger log = LoggerFactory.getLogger(VendaController.class);
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping(value = "/vendedor/{vendedorId}")
	public ResponseEntity<Response<List<VendaDto>>> buscaIdVendedor(@Valid @PathVariable("vendedorId") int vendedorId, BindingResult result) {
		Response<List<VendaDto>> response = new Response<List<VendaDto>>();
		try {
			log.info("Buscando vendas realizadas pelo vendedor... (ID do Vendedor: {})", vendedorId);
			if(result.hasErrors()) {
            	for (int i = 0; i < result.getErrorCount(); i++) {
            		response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
            	}
            	log.info("Os campos obrigatórios não foram preenchidos, por favor preencha-los.");
            	return ResponseEntity.ok(response);
            }
		    Optional<List<Venda>> listaVendas = vendaService.buscaIdVendedor(vendedorId);
		    response.setDados(ConvertUtil.ConverterListaVenda(listaVendas.get()));
		    return ResponseEntity.ok(response);
		} catch (ExceptionUtil e) {
			log.info("Ouve inconsistência nos dados: {}", e.getMessage());
			response.adicionarErro(e.getMensagem());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			log.error("Ocorreu um erro na aplicação: {}", e.getMessage());
			response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
			 return ResponseEntity.status(500).body(response);
		}
	}
	
	@PostMapping
   	public ResponseEntity<Response<VendaDto>> salvar(@RequestBody VendaDto vendaDto) {
		Response<VendaDto> response = new Response<VendaDto>();
         	try {
 
                	log.info("Salvando venda realizada pelo vendedor... {}", vendaDto.toString());
                	Venda venda = this.vendaService.salvar(ConvertUtil.Converter(vendaDto));
                	response.setDados(ConvertUtil.Converter(venda));
                	return ResponseEntity.ok(response);
 
         	} catch (ExceptionUtil e) {
                	log.info("Ouve inconsistência nos dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
 
   	}

}
