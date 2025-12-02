package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Cookie;

public class CatalogoService {

	public List<Cookie> listaCatalogo = new ArrayList<>();

	public List<Cookie> getListaCatalogo() {
		return listaCatalogo;
	}

	public void setListaCatalogo(List<Cookie> listaCatalogo) {
		this.listaCatalogo = listaCatalogo;
	}

	Cookie cookie = new Cookie("Tradicional", 7);
	Cookie cookie2 = new Cookie("Double Chocolate", 7);
	Cookie cookie3 = new Cookie("Ovomaltine", 7);
	Cookie cookie4 = new Cookie("Nutella", 7);

	public CatalogoService() {

		listaCatalogo.add(cookie);
		listaCatalogo.add(cookie2);
		listaCatalogo.add(cookie3);
		listaCatalogo.add(cookie4);
	}

	public void salvarNoCatalogo(Cookie obj) {
		listaCatalogo.add(obj);
	}

}
