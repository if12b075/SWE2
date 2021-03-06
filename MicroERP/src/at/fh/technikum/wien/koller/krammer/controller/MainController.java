package at.fh.technikum.wien.koller.krammer.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.filter.RechnungFilter;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.pdf.RechnungPDFCreator;
import at.fh.technikum.wien.koller.krammer.presentationmodel.KontaktModel;
import at.fh.technikum.wien.koller.krammer.presentationmodel.RechnungModel;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController extends AbstractController {
	@FXML
	private ImageView kontaktloeschen;
	@FXML
	private ImageView kontaktbearbeiten;
	@FXML
	private ImageView kontakthinzufuegen;
	@FXML
	private ListView<String> kontaktlist;
	@FXML
	private ListView<String> rechnungslist;
	@FXML
	private TextField kontaktname;
	@FXML
	private Button kontaktsearchbutton;
	@FXML
	private RadioButton searchfirmaradio;
	@FXML
	private RadioButton searchpersonradio;
	@FXML
	private TextField betrageurovon;
	@FXML
	private TextField betragcentvon;
	@FXML
	private TextField betrageurobis;
	@FXML
	private TextField betragcentbis;
	@FXML
	private TextField rechnungvon;
	@FXML
	private TextField rechnungbis;
	@FXML
	private TextField rechnungname;
	@FXML
	private RadioButton rechnungfirmaradio;
	@FXML
	private RadioButton rechnungpersonradio;
	@FXML
	private ImageView rechnungloeschen;
	@FXML
	private ImageView rechnungbearbeiten;
	@FXML
	private ImageView rechnunghinzufuegen;
	@FXML
	private ImageView rechnungdrucken;

	private KontaktModel km;
	private RechnungModel rm;
	private List<Kontakt> kl;
	private List<Rechnung> rl;

	@FXML
	public void onKontaktHinzufuegen() throws IOException {
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPKontaktView.fxml",
				"Kontakt hinzufuegen");
	}
	
	@FXML
	public void onRechnunghinzufuegenClick() throws IOException {
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPRechnungView.fxml",
				"Rechnung hinzufuegen");
	}

	public void onKontaktBearbeitenClick() throws IOException {
		if (kontaktlist.getSelectionModel().getSelectedItem() != null) {
			Kontakt k = kl.get(kontaktlist.getSelectionModel()
					.getSelectedIndex());
			km.setModel(k);
			km.setUpdate(true);
		}

		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPKontaktView.fxml",
				km, "Kontakt bearbeiten");
	}

	@FXML
	public void onKontaktSearch() {
		Boolean radioFilter = null;
		if (searchfirmaradio.isSelected())
			radioFilter = new Boolean(true);
		else if (searchpersonradio.isSelected())
			radioFilter = new Boolean(false);

		KontaktFilter kf = new KontaktFilter(kontaktname.getText(), radioFilter);
		kl = MERPProxyFactory.getKontaktFilter(kf);
		List<String> kontakte = new ArrayList<String>();
		if (kl != null) {
			for (int i = 0; i < kl.size(); i++) {
				kontakte.add(kl.get(i).toString());

				ObservableList<String> items = FXCollections
						.observableArrayList(kontakte);
				kontaktlist.setItems(items);
			}
		} else
			System.out
					.println("Tut uns leid die Kontakte konnten nicht geladen werden, bitte überprüfen Sie ob der Server gestartet wurde");

		kontakthinzufuegen.setVisible(true);
	}

	@FXML
	public void onRechnungSearchClick() {
		
		if(validate()) {
			Boolean radioFilter = null;
			if (rechnungfirmaradio.isSelected())
				radioFilter = new Boolean(true);
			else if (rechnungpersonradio.isSelected())
				radioFilter = new Boolean(false);

			KontaktFilter kf = new KontaktFilter(rechnungname.getText(),
					radioFilter);
			RechnungFilter rf = new RechnungFilter();
			
			Date rechbis = null;
			if (rechnungbis.getText().length()>0) {
				try {
					SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
					rechbis = new Date(sdfToDate.parse(rechnungbis.getText()).getTime());
				} catch (ParseException e) {
					rechnungbis.setText("Format: dd/MM/yyyy");
				}
			}
			
			
			Date rechvon = null;
			if (rechnungvon.getText().length()>0) {
				try {
					SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
					rechvon = new Date(sdfToDate.parse(rechnungvon.getText()).getTime());
				} catch (ParseException e) {
					rechnungvon.setText("Format: dd/MM/yyyy");
				}
			}
			

			double betragvon = 0;
			if (!Helper.isNullOrEmpty(betrageurovon.getText()))
				betragvon = Long.parseLong(betrageurovon.getText());
			if (!Helper.isNullOrEmpty(betragcentvon.getText())) {
				if(betragcentvon.getText().length() < 2)
					betragvon = betragvon + (Double.parseDouble(betragcentvon.getText()) / 10);
				else
					betragvon = betragvon + (Double.parseDouble(betragcentvon.getText()) / 100);
			}
			
			double betragbis = 0;
			if (!Helper.isNullOrEmpty(betrageurobis.getText()))
				betragbis = Long.parseLong(betrageurobis.getText());
			if (!Helper.isNullOrEmpty(betragcentbis.getText())) {
				if(betragcentbis.getText().length() < 2)
					betragbis = betragbis + (Double.parseDouble(betragcentbis.getText()) / 10);
				else
					betragbis = betragbis + (Double.parseDouble(betragcentbis.getText()) / 100);
			}
			
			rf.setBisBetrag(betragbis);
			rf.setVonBetrag(betragvon);
			rf.setBisDatum(rechbis);
			rf.setVonDatum(rechvon);
			rf.setKontaktFilter(kf);
			
			////////////////////////////////////////////////////////////////////////
			//						TODO                                          //
			///////////////////////////////////////////////////////////////////////
			
			//rl = MERPProxyFactory.getAlleRechnungen();
			rl = MERPProxyFactory.getRechnungFilter(rf);
			List<String> rechnungen = new ArrayList<String>();
			if (rl != null) {
				for (int i = 0; i < rl.size(); i++) {
					rechnungen.add(rl.get(i).toString());
				}
					rechnungslist.getItems().clear();
					ObservableList<String> items = FXCollections
							.observableArrayList(rechnungen);
					rechnungslist.setItems(items);
				
			} else
				System.out
						.println("Tut uns leid die Kontakte konnten nicht geladen werden, bitte überprüfen Sie ob der Server gestartet wurde");

			
			rechnunghinzufuegen.setVisible(true);
			
		} else
			System.out.println("Bitte Felder korrekt ausfuellen");
	}

	@FXML
	public void onKontaktList() {
		if (kontaktlist.getSelectionModel().getSelectedItem() != null) {
			kontaktbearbeiten.setVisible(true);
			kontaktloeschen.setVisible(true);
		} else {
			kontaktbearbeiten.setVisible(false);
			kontaktloeschen.setVisible(false);
		}

	}
	
	@FXML
	public void onRechnungListClick() {
		if (rechnungslist.getSelectionModel().getSelectedItem() != null) {
			rechnungbearbeiten.setVisible(true);
			rechnungloeschen.setVisible(true);
			rechnungdrucken.setVisible(true);
		} else {
			rechnungbearbeiten.setVisible(false);
			rechnungloeschen.setVisible(false);
			rechnungdrucken.setVisible(false);
		}
	}
	
	@FXML
	public void onRechnungChange() throws IOException {
		rm = new RechnungModel();
		if (rechnungslist.getSelectionModel().getSelectedItem() != null) {
			Rechnung r = MERPProxyFactory.getRechnungById(rl.get(rechnungslist.getSelectionModel()
					.getSelectedIndex()).getId());
			
			rm.setModel(r);
			rm.setUpdate(true);
		}
	
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPRechnungView.fxml",
				rm, "Rechnung bearbeiten");
	}
	
	@FXML
	public void onRechnungDruck() {
		if (rechnungslist.getSelectionModel().getSelectedItem() != null) {
			Rechnung r = MERPProxyFactory.getRechnungById(rl.get(rechnungslist.getSelectionModel()
					.getSelectedIndex()).getId());
			
			Kontakt k = new Firma();
			k.setId(r.getKontaktid());
			
			Kontakt k2 = MERPProxyFactory.getKontaktById(k);
			
			RechnungPDFCreator.createPDF(r,k2);
			
		}
	}
	
	@FXML
	public void onKontaktLoeschen() {
		if (kontaktlist.getSelectionModel().getSelectedItem() != null) {
			Kontakt k = kl.get(kontaktlist.getSelectionModel()
					.getSelectedIndex());

			if(MERPProxyFactory.deleteKontakt(k)) {
				kl.remove(kontaktlist.getSelectionModel()
					.getSelectedIndex());
				List<String> kontakte = new ArrayList<String>();
				if (kl != null) {
					for (int i = 0; i < kl.size(); i++) {
						kontakte.add(kl.get(i).toString());

						ObservableList<String> items = FXCollections
								.observableArrayList(kontakte);
						kontaktlist.setItems(items);
					}
				
				}
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		km = new KontaktModel();
		rm = new RechnungModel();
		kontaktname.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					onKontaktSearch();
				}
			}

		});
		
		rechnungname.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					onRechnungSearchClick();
				}
			}

		});
	}

	public boolean validate() {
		boolean valid = true;

		if (!Helper.isNullOrEmpty(betrageurovon.getText())) {
			try {
				Double.parseDouble(betrageurovon.getText());
			} catch (Exception e) {
				valid = false;
				betrageurovon.setText("Nur Zahl");
			}
		}
		if (!Helper.isNullOrEmpty(betrageurobis.getText())) {
			try {
				Double.parseDouble(betrageurobis.getText());
			} catch (Exception e) {
				valid = false;
				betrageurobis.setText("Nur Zahl");
			}
		}
		if (!Helper.isNullOrEmpty(betragcentbis.getText())) {
			try {
				if(betragcentbis.getText().length() > 2) {
					valid = false;
					betragcentbis.setText("Nur 2 zahlen");
				}
				Double.parseDouble(betragcentbis.getText());
			} catch (Exception e) {
				valid = false;
				betragcentbis.setText("Nur Zahl");
			}
		}
		if (!Helper.isNullOrEmpty(betragcentvon.getText())) {
			try {
				if(betragcentvon.getText().length() > 2){
					valid = false;
					betragcentvon.setText("nur 2 zahlen");
				} else
					Double.parseDouble(betragcentvon.getText());
			} catch (Exception e) {
				valid = false;
				betragcentvon.setText("Nur Zahl");
			}
		}
		if (rechnungvon.getText().length()>0) {
			try {
				SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
				sdfToDate.parse(rechnungvon.getText());
			} catch (ParseException e) {
				valid = false;
				rechnungvon.setText("Format: dd/MM/yyyy");
			}
		}
		if (rechnungbis.getText().length()>0) {
			try {
				SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
				sdfToDate.parse(rechnungbis.getText());
			} catch (ParseException e) {
				valid = false;
				rechnungbis.setText("Format: dd/MM/yyyy");
			}
		}

		return valid;
	}

}
