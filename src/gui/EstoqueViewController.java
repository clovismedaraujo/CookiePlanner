package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	
	// CORREÇÃO CRÍTICA: O nome deve ser tableViewCookies para corresponder ao FXML
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
		// Configura as colunas da TableView para a entidade Estoque
		TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCookie"));
		TableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		// Adiciona a ação do botão (se ainda não estiver no FXML)
		registraEstoqueButton.setOnAction(event -> onRegistraEstoqueButtonAction());
	}
	
	private void updateTableView() {
		obslistEstoque = FXCollections.observableArrayList(estoqueService.getListaEstoque());
		tableViewCookies.setItems(obslistEstoque); // Usa tableViewCookies
	}
	
	@FXML
	public void onRegistraEstoqueButtonAction() {
		if (nomeCookieComboBox.getValue() == null || quantidadeCookieTxtField.getText().isEmpty()) {
			System.err.println("Selecione um cookie e digite a quantidade.");
			return;
		}
		
		String nome = nomeCookieComboBox.getValue().getNomeCookie();
		Integer quantidade = Utils.tryParseToInt(quantidadeCookieTxtField.getText());
		
		if (quantidade == null || quantidade <= 0) {
			System.err.println("Quantidade inválida.");
			return;
		}
		
		estoqueService.registrarEntrada(nome, quantidade);
		
		updateTableView();
		
		nomeCookieComboBox.getSelectionModel().clearSelection();
		quantidadeCookieTxtField.clear();
	}
}