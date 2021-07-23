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

import com.vendas.api.entities.Vendedor;
import com.vendas.api.services.VendedorService;
import com.vendas.api.dtos.VendedorDto;
import com.vendas.api.utils.ExceptionUtil;
import com.vendas.api.utils.ConvertUtil;
import com.vendas.api.response.Response;

@RestController
@RequestMapping("/api/vendedor")
@CrossOrigin(origins = "*")
public class VendedorController {
	private static final Logger log = LoggerFactory.getLogger(VendedorController.class);
	
	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<VendedorDto>> buscaId(@PathVariable("id") int id) {
		Response<VendedorDto> response = new Response<VendedorDto>();
		try {
			log.info("Buscando vendedor com ID: {}", id);
			Optional<Vendedor> vendedor = vendedorService.buscaId(id);
			response.setDados(ConvertUtil.Converter(vendedor.get()));			
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
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<Response<List<VendedorDto>>> buscaNome(@PathVariable("nome") String nome) {
		Response<List<VendedorDto>> response = new Response<List<VendedorDto>>();
		 try {
			 log.info("Buscando vendedor por nome: (Nome: {})", nome);
			 Optional<List<Vendedor>> listaVendedores = vendedorService.buscaNome(nome);
			 response.setDados(ConvertUtil.ConverterListaVendedor(listaVendedores.get()));			 
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
   	public ResponseEntity<Response<VendedorDto>> salvar(@Valid @RequestBody VendedorDto vendedorDto, BindingResult result) {
		Response<VendedorDto> response = new Response<VendedorDto>();
         	try {
                log.info("Salvando vendedor... {}", vendedorDto.toString());
                if(result.hasErrors()) {
                	for (int i = 0; i < result.getErrorCount(); i++) {
                		response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                	}
                	log.info("Os campos obrigatórios não foram preenchidos, por favor preencha-los.");
                	return ResponseEntity.ok(response);
                }
                Vendedor vendedor = this.vendedorService.salvar(ConvertUtil.Converter(vendedorDto));
            	response.setDados(ConvertUtil.Converter(vendedor));
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
