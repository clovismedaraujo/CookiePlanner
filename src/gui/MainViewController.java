package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.entities.Cookie;
import model.services.CatalogoService;

public class MainViewController {
	@FXML
	private Button catalogoButton;

	@FXML
	private Button estoqueButton;

	@FXML
	private Button vendasButton;


	private void loadView(String absolutName) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(absolutName));

			Scene scene = new Scene(root);

			Stage subMenuStage = new Stage();
			
			subMenuStage.setScene(scene);

			subMenuStage.show();
			subMenuStage.setResizable(false);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao carregar a tela: " + e.getMessage());
		}
	}
	
	private void loadCatalogoView(Cookie obj, String absolutName) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			
			CatalogoViewController controller = loader.getController();
			controller.setCookie(obj);
			controller.setCatalogoService(new CatalogoService());
			controller.updateFormData();

			Stage subMenuStage = new Stage();
			
			
			
			subMenuStage.setScene(scene);
			subMenuStage.show();
			subMenuStage.setResizable(false);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao carregar a tela: " + e.getMessage());
		}
	}
	
	@FXML
	public void onCatalogoButtonAction() {
		Cookie obj = new Cookie();
		loadCatalogoView(obj, "CatalogoView.fxml");
	}
	
	@FXML
	public void onEstoqueButtonAction() {
		loadView("EstoqueView.fxml");
	}
	
	@FXML
	public void onVendasButtonAction() {
		loadView("VendasView.fxml");
	}
	


}
