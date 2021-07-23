package com.vendas.api.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class VendedorDto {
	private String id;
	
	@NotEmpty(message = "Por Favor, insira um nome.")
	@Length(min = 5, max = 50, message = "Por Favor, insira um nome dentre 5 a 50 caracteres.")
	private String nome;
	
	private String totalVenda;
	private String mediaVenda;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTotalVenda() {
		return totalVenda;
	}
	
	public void setTotalVenda(String totalVenda) {
		this.totalVenda = totalVenda;
	}
	
	public String getMediaVenda() {
		return mediaVenda;
	}
	
	public void setMediaVenda(String mediaVenda) {
		this.mediaVenda = mediaVenda;
	}
	
	@Override
	public String toString() {
		return "Vendedor[" + "id=" + id + ","
				+ "nome=" + nome + ","
				+ "totalVenda=" + totalVenda + ","
				+ "mediaVenda=" + mediaVenda + "]";
	}
}
