package at.fh.technikum.wien.koller.krammer.view;

import java.io.IOException;

import at.fh.technikum.wien.koller.krammer.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class CustomControl extends HBox{
	private AbstractController ac;
	
	public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomFieldKontaktKnuepfen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(fxmlLoader.getController());
        
        try {
            fxmlLoader.load();           
            ac = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public AbstractController getAc() {
		return ac;
	}

	public void setAc(AbstractController ac) {
		this.ac = ac;
	}
	
	
}
