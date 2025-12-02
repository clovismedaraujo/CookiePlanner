package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.entities.Cookie;

public class EstoqueViewController {
	@FXML
	private TextField quantidadeCookieTxtField;
	
	@FXML
	private ComboBox<Cookie> nomeCookieComboBox;
	
	@FXML
	private Button registraEstoqueButton;
	
	@FXML
	private TableView<Cookie> tableViewCookies;
	
	@FXML
	private TableColumn<Cookie, String> TableColumnNome;
	
	@FXML
	private TableColumn<Cookie, Integer> TableColumnQuantidade;
	

}

