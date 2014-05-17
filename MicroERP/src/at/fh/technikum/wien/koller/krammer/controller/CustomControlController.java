package at.fh.technikum.wien.koller.krammer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void onDeleteClick() {
		System.out.println("delete");
		ccm.setOk(false);
	}	

}
