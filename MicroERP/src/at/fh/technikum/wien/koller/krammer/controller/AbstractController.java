package at.fh.technikum.wien.koller.krammer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AbstractController implements Initializable {
	private Stage stage;
	private Object model;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {

	}

	private void show(String resource, Object model, String title, Modality m)
			throws IOException {
		FXMLLoader fl = new FXMLLoader(getClass().getResource(resource));
		Parent root = (Parent)fl.load();

		Stage newStage = new Stage();
		newStage.initModality(m);
		newStage.initOwner(stage);
		Scene scene = new Scene(root);

		AbstractController controller = (AbstractController) fl.getController();
		controller.setModel(model);
		controller.setStage(newStage);
		

		newStage.setScene(scene);
		newStage.show();
	}

	public void show(String resource, Object model, String title)
			throws IOException {
		show(resource, model, title, Modality.NONE);
	}

	public void showDialog(String resource, Object model, String title)
			throws IOException {
		show(resource, model, title, Modality.WINDOW_MODAL);
	}

	public void show(String resource, String title) throws IOException {
		show(resource, null, title, Modality.NONE);
	}

	public void showDialog(String resource, String title) throws IOException {
		show(resource, null, title, Modality.WINDOW_MODAL);
	}

	public void setModel(Object model) {
		this.model = model;
	}
	
	public Object getModel() {
		return this.model;
	}

}
