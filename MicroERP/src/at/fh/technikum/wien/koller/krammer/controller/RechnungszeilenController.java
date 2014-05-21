package at.fh.technikum.wien.koller.krammer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.presentationmodel.RechnungZeileModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RechnungszeilenController extends AbstractController {
	@FXML
	private TextField bezeichnung;
	@FXML
	private TextField menge;
	@FXML
	private TextField stkpreis;
	@FXML
	private TextField ust;
	
	private RechnungZeileModel rechnungZeileModel;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		rechnungZeileModel = new RechnungZeileModel();
		
	}
	@Override
	public void setModel(Object model) {
		
		if(((RechnungZeileModel)model)!=null)
			rechnungZeileModel = (RechnungZeileModel)model;
		else
			System.out.println("null");
		
		bezeichnung.textProperty().bindBidirectional(rechnungZeileModel.bezeichnungProperty());
		menge.textProperty().bindBidirectional(rechnungZeileModel.mengeProperty());
		stkpreis.textProperty().bindBidirectional(rechnungZeileModel.stkpreisProperty());
		ust.textProperty().bindBidirectional(rechnungZeileModel.ustProperty());
	}
	
	@FXML
	public void onSave() {
		this.close();
	}
	
	@FXML
	public void onCancel() {
		this.close();
	}
	
	
}
