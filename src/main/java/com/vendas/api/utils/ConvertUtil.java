package com.vendas.api.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import com.vendas.api.dtos.VendaDto;
import com.vendas.api.dtos.VendedorDto;
import com.vendas.api.entities.Venda;
import com.vendas.api.entities.Vendedor;
 
public class ConvertUtil {
 
   	public static Venda Converter(VendaDto vendaDto) throws ParseException {
 
         	Venda venda = new Venda();
 
         	if (vendaDto.getId() != null && vendaDto.getId() != "")
                	venda.setId(Integer.parseInt(vendaDto.getId()));
 
         	venda.setValor(Double.parseDouble(vendaDto.getValor()));
         	venda.setDataVenda(new SimpleDateFormat("dd/MM/yyyy").parse(vendaDto.getDataVenda()));
 
         	Vendedor vendedor = new Vendedor();
         	vendedor.setId(Integer.parseInt(vendaDto.getVendedorId()));
 
         	venda.setVendedor(vendedor);
 
         	return venda;
 
   	}
   	
   	public static VendaDto Converter(Venda venda) {
 
         	VendaDto vendaDto = new VendaDto();
         	
         	vendaDto.setId(String.valueOf(venda.getId()));
         	vendaDto.setValor(String.valueOf(venda.getValor()));
         	vendaDto.setDataVenda(venda.getDataVenda().toString());
         	vendaDto.setVendedorId(String.valueOf(venda.getVendedor().getId()));
 
         	return vendaDto;
 
   	}
   	
   	public static List<VendaDto> ConverterListaVenda(List<Venda> lista){
         	
         	List<VendaDto> lst = new ArrayList<VendaDto>(lista.size());
         	
         	for (Venda cartao : lista) {
                	lst.add(Converter(cartao));
         	}
         	
         	return lst;
         	
   	}
 
   	public static Vendedor Converter(VendedorDto vendedorDto) {
 
         	Vendedor vendedor = new Vendedor();
 
         	if (vendedorDto.getId() != null && vendedorDto.getId() != "")
                	vendedor.setId(Integer.parseInt(vendedorDto.getId()));
 
         	vendedor.setNome(vendedorDto.getNome());
         	vendedor.setTotalVenda(Integer.parseInt(vendedorDto.getTotalVenda()));
         	vendedor.setMediaVenda(Double.parseDouble(vendedorDto.getMediaVenda()));
 
         	return vendedor;
 
   	}
   	
   	public static VendedorDto Converter(Vendedor vendedor) {
 
         	VendedorDto vendedorDto = new VendedorDto();
 
         	vendedorDto.setId(String.valueOf(vendedor.getId()));
         	vendedorDto.setNome(vendedor.getNome());
         	vendedorDto.setTotalVenda(String.valueOf(vendedorDto.getTotalVenda()));
         	vendedorDto.setMediaVenda(String.valueOf(vendedorDto.getMediaVenda()));
         	return vendedorDto;
 
   	}
   	
   	public static List<VendedorDto> ConverterListaVendedor(List<Vendedor> lista){
     	
     	List<VendedorDto> lst = new ArrayList<VendedorDto>(lista.size());
     	
     	for (Vendedor vendedor : lista) {
            	lst.add(Converter(vendedor));
     	}
     	
     	return lst;
     	
	}

}
