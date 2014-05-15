package at.fh.technikum.wien.koller.krammer.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.presentationmodel.CustomControlModel;
import at.fh.technikum.wien.koller.krammer.presentationmodel.SearchModel;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
	private CustomControlModel ccm;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		sm = new SearchModel();

		searchname.textProperty().bindBidirectional(sm.searchnameProperty());
	}

	@Override
	public void setModel(Object model) {
		SearchModel search = (SearchModel) model;
		sm.setSearchname(search.getSearchname());
		sm.setIsFirma(search.getIsFirma());

		if (sm.getIsFirma() != null) {
			if (sm.getIsFirma())
				searchfirmaradio.setSelected(true);
			else
				searchpersonradio.setSelected(true);
		}
		System.out.println(sm.getIsChangeable());
		this.searchpersonradio.setDisable(true);

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
				this.ccm.setKontaktid(k.getId());
				this.ccm.setTextField(kl.get(0).toString());
				this.ccm.setOk(true);
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
			this.ccm.setKontaktid(k.getId());
			this.ccm.setTextField(searchlist.getSelectionModel()
					.getSelectedItem());
			this.ccm.setOk(true);
		}
		this.close();

	}

	@FXML
	public void onCancelClick() {
		this.close();
	}

	public CustomControlModel getCcm() {
		return ccm;
	}

	public void setCcm(CustomControlModel ccm) {
		this.ccm = ccm;
	}

}
