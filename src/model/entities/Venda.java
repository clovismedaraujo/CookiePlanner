package model.entities;

import java.time.LocalDateTime;

public class Venda {
	
	private String nomeCookie;
	private int quantidadeVendida;
	private double precoUnitario;
	private LocalDateTime dataVenda;
	
	public Venda() {
		this.dataVenda = LocalDateTime.now();
	}

	public Venda(String nomeCookie, int quantidadeVendida, double precoUnitario) {
		this.nomeCookie = nomeCookie;
		this.quantidadeVendida = quantidadeVendida;
		this.precoUnitario = precoUnitario;
		this.dataVenda = LocalDateTime.now();
	}

	public String getNomeCookie() {
		return nomeCookie;
	}

	public void setNomeCookie(String nomeCookie) {
		this.nomeCookie = nomeCookie;
	}

	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValorTotal() {
		return quantidadeVendida * precoUnitario;
	}

	@Override
	public String toString() {
		return nomeCookie + " | Qtd: " + quantidadeVendida + " | Total: " + getValorTotal();
	}
}