package model.entities;

public class Estoque {

	private String nomeCookie;
	private int quantidade;

	public Estoque() {
	}

	public Estoque(String nomeCookie, int quantidade) {
		this.nomeCookie = nomeCookie;
		this.quantidade = quantidade;
	}

	public String getNomeCookie() {
		return nomeCookie;
	}

	public void setNomeCookie(String nomeCookie) {
		this.nomeCookie = nomeCookie;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return nomeCookie + " (" + quantidade + ")";
	}
}
