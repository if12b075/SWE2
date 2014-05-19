package at.fh.technikum.wien.koller.krammer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;
import at.fh.technikum.wien.koller.krammer.presentationmodel.RechnungModel;
import at.fh.technikum.wien.koller.krammer.presentationmodel.RechnungZeileModel;
import at.fh.technikum.wien.koller.krammer.view.CustomControl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RechnungController extends AbstractController {
	@FXML
	private TextField datum;
	@FXML
	private TextField faelligkeit;
	@FXML
	private TextField rechnungnr;
	@FXML
	private TextField kommentar;
	@FXML
	private TextArea nachricht;
	@FXML
	private CheckBox bezahlt;
	@FXML
	private TextField bezahltam;
	@FXML
	private ListView<String> rechnungzeilen;
	@FXML
	private ImageView rzhinzufuegen;
	@FXML
	private ImageView rzloeschen;
	@FXML
	private ImageView rzbearbeiten;
	@FXML
	private CustomControl customcontrol;

	private RechnungModel rechnungModel;
	private RechnungZeileModel rechnungZeileModel;
	private ObservableList<String> data;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		rechnungModel = new RechnungModel();
		rechnungZeileModel = new RechnungZeileModel();

		bezahlt.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (bezahlt.isSelected())
					bezahltam.setDisable(false);
				else
					bezahltam.setDisable(true);
			}
		});

		datum.textProperty().bindBidirectional(rechnungModel.datumProperty());
		faelligkeit.textProperty().bindBidirectional(
				rechnungModel.faelligkeitProperty());
		bezahltam.textProperty().bindBidirectional(
				rechnungModel.bezahltamProperty());
		nachricht.textProperty().bindBidirectional(
				rechnungModel.nachrichtProperty());
		kommentar.textProperty().bindBidirectional(
				rechnungModel.kommentarProperty());
		rechnungnr.textProperty().bindBidirectional(
				rechnungModel.rechnungnrProperty());

	}

	@Override
	public void setModel(Object model) {
		rechnungModel.setModel((RechnungModel) model);
		if (rechnungModel.isBezahlt())
			bezahlt.setSelected(true);

		data = FXCollections.observableArrayList(rechnungModel.getRechnungszeilen());

		rechnungzeilen.setItems(data);
	}
	
	@FXML
	public void onRechnungszeileClick() {
		if (rechnungzeilen.getSelectionModel().getSelectedItem() != null) {
			rzbearbeiten.setVisible(true);
			rzloeschen.setVisible(true);
		} else {
			rzbearbeiten.setVisible(false);
			rzloeschen.setVisible(false);
		}
	}

	@FXML
	public void onCancelClick() {
		this.close();
	}

	@FXML
	public void onSaveClick() {
		this.close();
	}

	@FXML
	public void onRzChange() throws IOException {
		int auswahl = rechnungzeilen.getSelectionModel().getSelectedIndex();
		
		Rechnungszeile rz = rechnungModel.getRechnungszeilen2().get(auswahl);
		rechnungZeileModel.setModel(rz);
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPRechnungszeileView.fxml",rechnungZeileModel,
				"Rechnungszeile bearbeiten");
	}

	@FXML
	public void onRzDelete() {

	}

	@FXML
	public void onRzAdd() throws IOException {
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPRechnungszeileView.fxml",
				"Rechnungszeile hinzufuegen");
	}

}
