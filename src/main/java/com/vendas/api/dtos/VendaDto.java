package com.vendas.api.dtos;

import javax.validation.constraints.NotEmpty;

public class VendaDto {
	private String id;
	
	@NotEmpty(message = "Por Favor, insira um valor.")
	private String valor;
	
	@NotEmpty(message = "Por Favor, insira uma data de venda.")
	private String dataVenda;
	
	@NotEmpty(message = "Por Favor, informe o ID do Vendedor que realizou a venda.")
	private String vendedorId;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public String getVendedorId() {
		return vendedorId;
	}
	
	public void setVendedorId(String vendedorId) {
		this.vendedorId = vendedorId;
	}
	
	@Override
   	public String toString() {
		return "Venda[" + "id=" + id + ","
				+ "valor=" + valor + ","
				+ "dataVenda=" + dataVenda + ","
				+ "vendedorId=" + vendedorId + "]";
	}
}
