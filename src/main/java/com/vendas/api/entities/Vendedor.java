package com.vendas.api.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "vendedor")
public class Vendedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "total_Venda", nullable = true)
	private int totalVenda;
	
	@Column(name = "media_Venda", nullable = true)
	private double mediaVenda;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Venda> vendas;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTotalVenda() {
		return totalVenda;
	}
	
	public void setTotalVenda(int totalVenda) {
		this.totalVenda = totalVenda;
	}
	
	public double getMediaVenda() {
		return mediaVenda;
	}
	
	public void setMediaVenda(double mediaVenda) {
		this.mediaVenda = mediaVenda;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}
	
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	
	@Override
	public String toString() {
		return "Vendedor[" + "id=" + id + ","
				+ "nome=" + nome + ","
				+ "totalVenda=" + totalVenda + ","
				+ "mediaVenda=" + mediaVenda + "]";
	}
}
