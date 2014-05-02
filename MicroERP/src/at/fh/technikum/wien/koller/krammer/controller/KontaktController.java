package at.fh.technikum.wien.koller.krammer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.presentationmodel.KontaktModel;
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
	
	private KontaktModel kontaktModel;
	
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		kontaktModel = new KontaktModel();
		
		personpane.disableProperty().bind(kontaktModel.disableEditPersonBinding());
		firmapane.disableProperty().bind(kontaktModel.disableEditFirmaBinding());
		
		personvorname.textProperty().bindBidirectional(kontaktModel.vornameProperty());
		personnachname.textProperty().bindBidirectional(kontaktModel.nachnameProperty());
	    firmaname.textProperty().bindBidirectional(kontaktModel.firmennameProperty());
	    firmauid.textProperty().bindBidirectional(kontaktModel.UIDProperty());
	}


	@Override
	public void setModel(Object model) {
		KontaktModel km = (KontaktModel)model;
		kontaktModel.setNachname(km.getNachname());
		kontaktModel.setVorname(km.getVorname());
	}
	
	
	
	
	
}
