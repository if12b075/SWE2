package at.fh.technikum.wien.koller.krammer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import at.fh.technikum.wien.koller.krammer.presentationmodel.CustomControlModel;

public class CustomControlController extends AbstractController {
	@FXML
	private Label labeltext;
	@FXML
	private ImageView successpicture;
	@FXML
	private TextField searchtextfield;
	
	private CustomControlModel ccm;
	
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		  ccm = new CustomControlModel();
          labeltext.textProperty().bindBidirectional(ccm.labelTextProperty());
          searchtextfield.textProperty().bindBidirectional(ccm.textFieldProperty());
          successpicture.imageProperty().bindBidirectional(ccm.successImageProperty());
          
          searchtextfield.setOnKeyPressed(new EventHandler<KeyEvent>()
  				{
  					@Override
  			        public void handle(KeyEvent ke)
  			        {
  			            if (ke.getCode().equals(KeyCode.ENTER))
  			            {
  			            	onSearchClick();
  			            }
  			        }
  			
  				});
	}

	@Override
	public void setModel(Object model) {
		ccm.setModel((CustomControlModel)model);
	}
	
	public CustomControlModel getModel() {
		return this.ccm;
	}

	@FXML
	public void onSearchClick() {
		try {
			showDialog(
					"/at/fh/technikum/wien/koller/krammer/view/MicroERPSucheView.fxml",ccm.getSearchModel(),
					"Kontakt hinzufuegen");
		} catch (IOException e) {
			//TODO
		}
		
	}
	
	@FXML
	public void onDeleteClick() {
		System.out.println("delete");
		ccm.setOk(false);
	}	

}
