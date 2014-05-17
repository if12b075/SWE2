package at.fh.technikum.wien.koller.krammer.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;
import at.fh.technikum.wien.koller.krammer.presentationmodel.SearchModel;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SearchController extends AbstractController {
	@FXML
	private TextField searchname;
	@FXML
	private RadioButton searchfirmaradio;
	@FXML
	private RadioButton searchpersonradio;
	@FXML
	private ListView<String> searchlist;
	@FXML
	private ImageView savesearch;

	private SearchModel sm;
	private List<Kontakt> kl;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		sm = new SearchModel();
		
		searchname.setOnKeyPressed(new EventHandler<KeyEvent>()
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
		sm = (SearchModel) model;
		if (sm.getIsFirma() != null) {
			if (sm.getIsFirma())
				searchfirmaradio.setSelected(true);
			else
				searchpersonradio.setSelected(true);
		}

		this.searchpersonradio.setDisable(true);
		searchname.textProperty().bindBidirectional(sm.searchnameProperty());
		onSearchClick();
	}

	@FXML
	void onSearchListClick() {
		if (searchlist.getSelectionModel().getSelectedItem() != null)
			savesearch.setVisible(true);

		else
			savesearch.setVisible(false);

	}

	@FXML
	public void onSearchClick() {
		KontaktFilter kf = sm.getKontaktFilter();

		kl = MERPProxyFactory.getKontaktFilter(kf);
		List<String> kontakte = new ArrayList<String>();
		if (kl != null) {

			if (kl.size() == 1) {
				Kontakt k = kl.get(0);

				sm.getCcm().setKontaktid(k.getId());
				if (k.isFirma()) {
					Firma f = MERPProxyFactory.getFirmaById(k.getId());
					sm.getCcm().setTextField(f.getName());
				} else {
					Person p = MERPProxyFactory.getPersonById(k.getId());
					sm.getCcm().setTextField(
							p.getNachname() + " " + p.getVorname());
				}

				sm.getCcm().setOk(true);
				this.close();
			} else {
				for (int i = 0; i < kl.size(); i++) {
					kontakte.add(kl.get(i).toString());

					ObservableList<String> items = FXCollections
							.observableArrayList(kontakte);
					searchlist.setItems(items);
				}
			}

		} else
			System.out
					.println("Tut uns leid die Kontakte konnten nicht geladen werden, bitte überprüfen Sie ob der Server gestartet wurde");

	}

	@FXML
	public void onSaveClick() {
		if (searchlist.getSelectionModel().getSelectedItem() != null) {
			Kontakt k = kl.get(searchlist.getSelectionModel()
					.getSelectedIndex());

			sm.getCcm().setKontaktid(k.getId());
			if (k.isFirma()) {
				Firma f = MERPProxyFactory.getFirmaById(k.getId());
				sm.getCcm().setTextField(f.getName());
			} else {
				Person p = MERPProxyFactory.getPersonById(k.getId());
				sm.getCcm()
						.setTextField(p.getNachname() + " " + p.getVorname());
			}
			sm.getCcm().setOk(true);
		}
		this.close();

	}

	@FXML
	public void onCancelClick() {
		this.close();
	}

}
