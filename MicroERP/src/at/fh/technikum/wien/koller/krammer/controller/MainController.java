package at.fh.technikum.wien.koller.krammer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.presentationmodel.KontaktModel;
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
	
	private KontaktModel km;
	private List<Kontakt> kl;

	@FXML
	public void onKontaktHinzufuegen() throws IOException {
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPKontaktView.fxml",
				"Kontakt hinzufuegen");
	}
	
	public void onKontaktBearbeitenClick() throws IOException {
		if(kontaktlist.getSelectionModel().getSelectedItem() != null) {
			Kontakt k = kl.get(kontaktlist.getSelectionModel().getSelectedIndex());
			km.setModel(k);
			km.setUpdate(true);
		}
		
		showDialog(
				"/at/fh/technikum/wien/koller/krammer/view/MicroERPKontaktView.fxml",km,
				"Kontakt hinzufuegen");
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
	public void onKontaktList() {
		if(kontaktlist.getSelectionModel().getSelectedItem() != null) {
			kontaktbearbeiten.setVisible(true);
			kontaktloeschen.setVisible(true);
		} else {
			kontaktbearbeiten.setVisible(false);
			kontaktloeschen.setVisible(false);
		}
		
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		km = new KontaktModel();
		kontaktname.setOnKeyPressed(new EventHandler<KeyEvent>()
				{
					@Override
			        public void handle(KeyEvent ke)
			        {
			            if (ke.getCode().equals(KeyCode.ENTER))
			            {
			            	onKontaktSearch();
			            }
			        }
			
				});
		/*
		 * List<Kontakt> kl = MERPProxy.getAlleKontakte(); List<Rechnung> rl =
		 * MERPProxy.getAlleRechnungen();
		 * 
		 * List<String> kontakte = new ArrayList<String>(); List<String>
		 * rechnungen = new ArrayList<String>(); if(kl != null) { for(int
		 * i=0;i<kl.size();i++) { kontakte.add(kl.get(i).toString());
		 * 
		 * ObservableList<String> items =
		 * FXCollections.observableArrayList(kontakte);
		 * kontaktlist.setItems(items); } } else { System.out.println(
		 * "Tut uns leid die Kontakte konnten nicht geladen werden, bitte überprüfen Sie ob der Server gestartet wurde"
		 * ); }
		 * 
		 * if(rl != null) { for(int i=0;i<rl.size();i++) {
		 * rechnungen.add(rl.get(i).toString());
		 * 
		 * ObservableList<String> items2 =
		 * FXCollections.observableArrayList(rechnungen);
		 * rechnungslist.setItems(items2); } } else { System.out.println(
		 * "Tut uns leid die Rechnungen konnten nicht geladen werden, bitte überprüfen Sie ob der Server gestartet wurde"
		 * ); }
		 */

	}

}
