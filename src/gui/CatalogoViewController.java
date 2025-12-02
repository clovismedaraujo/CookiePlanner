package gui;

import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Cookie;
import model.services.CatalogoService;

public class CatalogoViewController {
	
	private Cookie entity;
	
	private CatalogoService service;
	
	@FXML
	private TextField nomeCookieField;

	@FXML
	private TextField valorCookieField;

	@FXML
	private Button registraCatalogoButton;
	
	
	public void setCookie(Cookie entity) {
		this.entity = entity;
	}
	
	public void setCatalogoService(CatalogoService service) {
		this.service = service;
	}
	
	@FXML
	public void onRegistraCatalagoButtonAction() {
		entity = getFormData();
		service.salvarNoCatalogo(entity);
		for (Cookie cookie : service.listaCatalogo) {
		System.out.println(cookie);
		}
	}
	
	private Cookie getFormData() {
		Cookie obj = new Cookie();
		obj.setNomeCookie(nomeCookieField.getText());
		obj.setPrecoCookie(Utils.tryParseToDouble(valorCookieField.getText()));
		return obj;
	}

	public void updateFormData() {
		nomeCookieField.setText(String.valueOf(entity.getNomeCookie()));
		valorCookieField.setText(String.valueOf(entity.getPrecoCookie()));
	}

}
