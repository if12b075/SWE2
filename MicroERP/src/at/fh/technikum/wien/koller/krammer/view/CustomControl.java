package at.fh.technikum.wien.koller.krammer.view;

import java.io.IOException;

import at.fh.technikum.wien.koller.krammer.presentationmodel.CustomControlModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CustomControl extends HBox{
	@FXML
	private Label labeltext;
	@FXML
	private ImageView successpicture;
	@FXML
	private TextField searchtextfield;
	
	private CustomControlModel ccm;
	
	public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomFieldKontaktKnuepfen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
            ccm = new CustomControlModel();
            labeltext.textProperty().bindBidirectional(ccm.labelTextProperty());
            searchtextfield.textProperty().bindBidirectional(ccm.textFieldProperty());
            successpicture.imageProperty().bindBidirectional(ccm.successImageProperty());
            
            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
	
	public void setModel(CustomControlModel cm) {
		ccm.setModel(cm);
	}

	@FXML
	public void onSearchClick() {
		System.out.println("Suche");
		ccm.setOk(true);
		
	}
	
	@FXML
	public void onDeleteClick() {
		System.out.println("delete");
		ccm.setOk(false);
	}
}
