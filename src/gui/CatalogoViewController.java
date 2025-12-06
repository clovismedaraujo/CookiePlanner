package gui;

import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
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
		if (entity.getNomeCookie() == null || entity.getNomeCookie().trim().isEmpty() || entity.getPrecoCookie() == 0) {
	        Utils.showAlert("Erro de Cadastro", "Dados Incompletos", "Por favor, preencha o Nome e o Preço corretamente.", AlertType.ERROR);
	        return;
	    }
		
		service.salvarNoCatalogo(entity);
		
		Utils.showAlert("Sucesso!", null, "Cookie '" + entity.getNomeCookie() + "' registrado no catálogo.", AlertType.INFORMATION);
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
