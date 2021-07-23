package com.vendas.api.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "venda")
public class Venda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@Column(name = "data_Venda", nullable = false)
	private Date dataVenda;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private Vendedor vendedor;
	
	public int getId() {
     	return id;
	}
	
	public void setId(int id) {
     	this.id = id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Date getDataVenda() {
     	return dataVenda;
	}
	
	public void setDataVenda(Date dataVenda) {
     	this.dataVenda = dataVenda;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	@PreUpdate
	public void preUpdate() {
		dataVenda = new Date();
	}
	@PrePersist
	public void prePersist() {
		dataVenda = new Date();
	}
	
	@Override
   	public String toString() {
		return "Venda[" + "id=" + id + ","
				+ "valor=" + valor + ","
				+ "dataVenda=" + dataVenda + ","
				+ "vendedor=" + vendedor + "]";
	}
}
