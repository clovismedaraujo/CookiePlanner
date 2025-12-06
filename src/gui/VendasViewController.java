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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Cookie;
import model.services.CatalogoService;
import model.services.VendaService;

public class VendasViewController implements Initializable {
	
	private VendaService vendaService = new VendaService();
	private CatalogoService catalogoService = new CatalogoService();
	
	@FXML
	private TextField quantidadeCookieTxtField;
	
	@FXML
	private Label receitaTotalLabel;
	
	@FXML
	private ComboBox<Cookie> nomeCookieComboBox;
	
	private ObservableList<Cookie> obslistCatalogo;
	
	@FXML
	private Button registraVendaButton;
	
	private void updateReceitaTotal() {
	    double total = vendaService.calcularReceitaTotal();
	    String formattedTotal = String.format("R$ %.2f", total); 
	    receitaTotalLabel.setText(formattedTotal);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadCatalogoComboBox();
		updateReceitaTotal();
	}
	
	private void loadCatalogoComboBox() {
		// Carrega os cookies do Catálogo para seleção
		obslistCatalogo = FXCollections.observableArrayList(catalogoService.getListaCatalogo());
		nomeCookieComboBox.setItems(obslistCatalogo);
	}
	
	
	@FXML
	public void onRegistraVendaButtonAction() {

		if (nomeCookieComboBox.getValue() == null || quantidadeCookieTxtField.getText().isEmpty()) {
			Utils.showAlert("Erro de Venda", "Dados Incompletos", "Por favor, selecione um cookie e digite a quantidade.", AlertType.ERROR);
			return;
		}

		Cookie cookieSelecionado = nomeCookieComboBox.getValue();
		Integer quantidade = Utils.tryParseToInt(quantidadeCookieTxtField.getText());
		
		if (quantidade == null || quantidade <= 0) {
			Utils.showAlert("Erro de Venda", "Quantidade Inválida", "A quantidade deve ser um número inteiro positivo.", AlertType.ERROR);
			return;
		}

		boolean sucesso = vendaService.registrarVenda(cookieSelecionado, quantidade);

		if (sucesso) {
			Utils.showAlert("Sucesso!", null, "Venda de " + cookieSelecionado.getNomeCookie() + " registrada com sucesso! Estoque atualizado.", AlertType.INFORMATION);

			nomeCookieComboBox.getSelectionModel().clearSelection();
			quantidadeCookieTxtField.clear();
			
			updateReceitaTotal();
		} else {

			Utils.showAlert("Falha na Venda", "Estoque Insuficiente", "Não há estoque suficiente de " + cookieSelecionado.getNomeCookie() + " para registrar esta venda.", AlertType.WARNING);
		}
	}
}