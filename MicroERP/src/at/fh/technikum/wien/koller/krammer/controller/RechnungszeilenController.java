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
		
		bezeichnung.textProperty().bindBidirectional(rechnungZeileModel.bezeichnungProperty());
		menge.textProperty().bindBidirectional(rechnungZeileModel.mengeProperty());
		stkpreis.textProperty().bindBidirectional(rechnungZeileModel.stkpreisProperty());
		ust.textProperty().bindBidirectional(rechnungZeileModel.ustProperty());
	}
	@Override
	public void setModel(Object model) {
		rechnungZeileModel.setModel((RechnungZeileModel) model);
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
