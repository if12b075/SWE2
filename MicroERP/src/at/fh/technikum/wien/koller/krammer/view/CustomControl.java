package at.fh.technikum.wien.koller.krammer.view;

import java.io.IOException;

import javafx.beans.property.StringProperty;
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
	
	public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomFieldKontaktKnuepfen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
	
    public String getLabelText() {
        return labelTextProperty().get();
    }
    
    public void setLabelText(String value) {
    	labelTextProperty().set(value);
    }
    
    public StringProperty labelTextProperty() {
        return labeltext.textProperty();                
    }
    
    public String getTextField() {
        return textFieldProperty().get();
    }
    
    public void setTextField(String value) {
    	textFieldProperty().set(value);
    }
    
    public StringProperty textFieldProperty() {
        return searchtextfield.textProperty();                
    }

	@FXML
	public void onSearchClick() {
		
	}
	
	@FXML
	public void onDeleteClick() {
		
	}
}
