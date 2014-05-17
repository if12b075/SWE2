package at.fh.technikum.wien.koller.krammer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.presentationmodel.CustomControlModel;
import at.fh.technikum.wien.koller.krammer.presentationmodel.KontaktModel;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
import at.fh.technikum.wien.koller.krammer.view.CustomControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class KontaktController extends AbstractController{
	@FXML
	private TitledPane firmapane;
	@FXML
	private TitledPane personpane;
	@FXML
	private TextField firmaname;
	@FXML
	private TextField firmauid;
	@FXML
	private TextField personvorname;
	@FXML
	private TextField personnachname;
	@FXML
	private TextField personsuffix;
	@FXML
	private TextField persontitel;
	@FXML
	private TextField persongeburtstag;
	@FXML
	private TextField wohnaddr1;
	@FXML
	private TextField wohnaddr2;
	@FXML
	private TextField wohnort;
	@FXML
	private TextField wohnplz;
	@FXML
	private TextField rechnungaddr1;
	@FXML
	private TextField rechnungaddr2;
	@FXML
	private TextField rechnungort;
	@FXML
	private TextField rechnungplz;
	@FXML
	private TextField lieferaddr1;
	@FXML
	private TextField lieferaddr2;
	@FXML
	private TextField lieferort;
	@FXML
	private TextField lieferplz;
	@FXML
	private CustomControl customcontrol;
	
	private KontaktModel kontaktModel;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		kontaktModel = new KontaktModel();
		
		
		personpane.disableProperty().bind(kontaktModel.disableEditPersonBinding());
		firmapane.disableProperty().bind(kontaktModel.disableEditFirmaBinding());
		
		personvorname.textProperty().bindBidirectional(kontaktModel.vornameProperty());
		personnachname.textProperty().bindBidirectional(kontaktModel.nachnameProperty());
		personsuffix.textProperty().bindBidirectional(kontaktModel.suffixProperty());
		persontitel.textProperty().bindBidirectional(kontaktModel.titelProperty());
		persongeburtstag.textProperty().bindBidirectional(kontaktModel.geburtstagProperty());
		
		firmaname.textProperty().bindBidirectional(kontaktModel.firmennameProperty());
	    firmauid.textProperty().bindBidirectional(kontaktModel.UIDProperty());
		
		wohnaddr1.textProperty().bindBidirectional(kontaktModel.wohnadress1Property());
		wohnaddr2.textProperty().bindBidirectional(kontaktModel.wohnadress2Property());
		wohnort.textProperty().bindBidirectional(kontaktModel.wohnortProperty());
		wohnplz.textProperty().bindBidirectional(kontaktModel.wohnplzProperty());
		
		rechnungaddr1.textProperty().bindBidirectional(kontaktModel.rechnungadress1Property());
		rechnungaddr2.textProperty().bindBidirectional(kontaktModel.rechnungadress2Property());
		rechnungort.textProperty().bindBidirectional(kontaktModel.rechnungortProperty());
	    rechnungplz.textProperty().bindBidirectional(kontaktModel.rechnungplzProperty());
	    
	    lieferaddr1.textProperty().bindBidirectional(kontaktModel.lieferadress1Property());
	    lieferaddr2.textProperty().bindBidirectional(kontaktModel.lieferadress2Property());
	    lieferort.textProperty().bindBidirectional(kontaktModel.lieferortProperty());
	    lieferplz.textProperty().bindBidirectional(kontaktModel.lieferplzProperty());
	}

	@Override
	public void setModel(Object model) {
		kontaktModel.setModel((KontaktModel) model);
		
		customcontrol.getAc().setModel(kontaktModel.getCcm());
	}
	
	@FXML
	public void onKontaktSaveClick() {
		if(kontaktModel.validate()) {
			if(kontaktModel.isUpdate()) {
				if(kontaktModel.isFirma()) {
					if(MERPProxyFactory.updateFirma(kontaktModel.getFirmaToSave()))
						System.out.println("Erfolgreich");
				} else {
					kontaktModel.setCcm((CustomControlModel)customcontrol.getAc().getModel());
					if(MERPProxyFactory.updatePerson(kontaktModel.getPersonToSave()))
						System.out.println("Erfolgreich");
				}
			} else {
				if(kontaktModel.isFirma()) {
					if(MERPProxyFactory.createFirma(kontaktModel.getFirmaToSave()))
						System.out.println("Erfolgreich");
				} else {
					kontaktModel.setCcm((CustomControlModel)customcontrol.getAc().getModel());
					if(MERPProxyFactory.createPerson(kontaktModel.getPersonToSave()))
						System.out.println("Erfolgreich");
				}
			}
			
			this.close();
		} else {
			System.out.println("Bitte alle Felder korrekt ausfuellen!");
		}
		
	}
	
	@FXML
	public void onKontaktCancelClick() {
		this.close();
	}

}
