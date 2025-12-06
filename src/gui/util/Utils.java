package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils {
	
	public static Double tryParseToDouble(String str) {
		return Double.parseDouble(str);
	}
	
	public static Integer tryParseToInt(String str) {
		return Integer.parseInt(str);
	}
	
	public static void showAlert(String title, String header, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
