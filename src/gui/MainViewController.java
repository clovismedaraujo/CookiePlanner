package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainViewController {
	@FXML
	private Button catalogoButton;

	@FXML
	private Button estoqueButton;

	@FXML
	private Button vendasButton;

	@FXML
	private void abrirCatalogo(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("CatalogoView.fxml"));

			Scene scene = new Scene(root);

			Stage catalogoStage = new Stage();
			
			catalogoStage.setScene(scene);

			catalogoStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao carregar a tela de Catálogo: " + e.getMessage());
		}
	}

	@FXML
	private void abrirEstoque(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("EstoqueView.fxml"));

			Scene scene = new Scene(root);

			Stage catalogoStage = new Stage();

			catalogoStage.setScene(scene);

			catalogoStage.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao carregar a tela de Estoque: " + e.getMessage());
		}
	}
	
	@FXML
	private void abrirVendas(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("VendasView.fxml"));

			Scene scene = new Scene(root);

			Stage catalogoStage = new Stage();
		
			catalogoStage.setScene(scene);

			catalogoStage.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao carregar a tela de Estoque: " + e.getMessage());
		}
	}
}
