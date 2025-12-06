package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Cookie;
import model.entities.Estoque;
import model.services.CatalogoService;
import model.services.EstoqueService;

public class EstoqueViewController implements Initializable {
	
	private EstoqueService estoqueService = new EstoqueService();
	private CatalogoService catalogoService = new CatalogoService();
	
	@FXML
	private TextField quantidadeCookieTxtField;
	
	@FXML
	private ComboBox<Cookie> nomeCookieComboBox;
	
	private ObservableList<Cookie> obslistCatalogo;
	private ObservableList<Estoque> obslistEstoque;
	
	@FXML
	private Button registraEstoqueButton;

	@FXML
	private TableView<Estoque> tableViewCookies; 
	
	@FXML
	private TableColumn<Estoque, String> TableColumnNome;
	
	@FXML
	private TableColumn<Estoque, Integer> TableColumnQuantidade;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadCatalogoComboBox();
		initializeTableView();
		updateTableView();
	}
	
	private void loadCatalogoComboBox() {
		obslistCatalogo = FXCollections.observableArrayList(catalogoService.getListaCatalogo());
		nomeCookieComboBox.setItems(obslistCatalogo);
	}
	
	private void initializeTableView() {

		TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCookie"));
		TableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		registraEstoqueButton.setOnAction(event -> onRegistraEstoqueButtonAction());
	}
	
	private void updateTableView() {
		obslistEstoque = FXCollections.observableArrayList(estoqueService.getListaEstoque());
		tableViewCookies.setItems(obslistEstoque);
	}
	
	@FXML
	public void onRegistraEstoqueButtonAction() {
		if (nomeCookieComboBox.getValue() == null || quantidadeCookieTxtField.getText().isEmpty()) {
			Utils.showAlert("Erro de Entrada", "Dados Incompletos", "Por favor, selecione um cookie e digite a quantidade.", AlertType.ERROR);
			return;
		}
		
		String nome = nomeCookieComboBox.getValue().getNomeCookie();
		Integer quantidade = Utils.tryParseToInt(quantidadeCookieTxtField.getText());
		
		if (quantidade == null || quantidade <= 0) {
			Utils.showAlert("Erro de Entrada", "Quantidade Inválida", "A quantidade deve ser um número inteiro positivo.", AlertType.ERROR);
			return;
		}
		
		estoqueService.registrarEntrada(nome, quantidade);
		
		updateTableView();
		
		Utils.showAlert("Sucesso!", null, "Entrada de estoque de " + nome + " registrada com sucesso.", AlertType.INFORMATION);
		
		nomeCookieComboBox.getSelectionModel().clearSelection();
		quantidadeCookieTxtField.clear();
	}
}