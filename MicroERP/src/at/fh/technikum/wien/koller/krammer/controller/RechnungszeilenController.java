package at.fh.technikum.wien.koller.krammer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.presentationmodel.RechnungZeileModel;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
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
		
		
		rechnungZeileModel = (RechnungZeileModel)model;
		
		
		bezeichnung.textProperty().bindBidirectional(rechnungZeileModel.bezeichnungProperty());
		menge.textProperty().bindBidirectional(rechnungZeileModel.mengeProperty());
		stkpreis.textProperty().bindBidirectional(rechnungZeileModel.stkpreisProperty());
		ust.textProperty().bindBidirectional(rechnungZeileModel.ustProperty());
	}
	
	@FXML
	public void onSave() {
		if(rechnungZeileModel.validate()) {
			if(rechnungZeileModel.isUpdate()) {
				if(MERPProxyFactory.updateRechnungszeile(rechnungZeileModel.getRechnungszeileToSave()))
					System.out.println("Erfolgreich");
				else
					System.out.println("Error");
			} else {
				if(MERPProxyFactory.createRechnungszeile(rechnungZeileModel.getRechnungszeileToSave()))
					System.out.println("Erfolgreich");
				else
					System.out.println("Error");
			}
			rechnungZeileModel.Benachrichtige();
			rechnungZeileModel.clear();
			this.close();
		}
		
	}
	
	@FXML
	public void onCancel() {
		this.close();
	}
	
	
}
