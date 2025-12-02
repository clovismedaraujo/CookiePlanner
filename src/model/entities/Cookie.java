package model.entities;

public class Cookie {

	private String nomeCookie;
	private double precoCookie;

	public Cookie(String nomeCookie, double precoCookie) {
		this.nomeCookie = nomeCookie;
		this.precoCookie = precoCookie;
	}

	public Cookie() {
	}
	
	
	public String getNomeCookie() {
		return nomeCookie;
	}

	public void setNomeCookie(String nomeCookie) {
		this.nomeCookie = nomeCookie;
	}

	public double getPrecoCookie() {
		return precoCookie;
	}

	public void setPrecoCookie(double precoCookie) {
		this.precoCookie = precoCookie;
	}

	@Override
	public String toString() {
		return "Cookie [nomeCookie=" + nomeCookie + ", precoCookie=" + precoCookie + "]";
	}
	

}
