package at.fh.technikum.wien.koller.krammer.presentationmodel;

import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RechnungZeileModel {
	private long id;
	private StringProperty bezeichnung = new SimpleStringProperty();
	private StringProperty stkpreis = new SimpleStringProperty();
	private StringProperty menge = new SimpleStringProperty();
	private StringProperty ust = new SimpleStringProperty();

	public RechnungZeileModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public final StringProperty bezeichnungProperty() {
		return bezeichnung;
	}

	public final void setBezeichnung(String bezeichnung) {
		this.bezeichnung.set(bezeichnung);
	}

	public final String getBezeichnung() {
		return this.bezeichnung.get();
	}

	public final StringProperty stkpreisProperty() {
		return stkpreis;
	}

	public final void setStkpreis(String stkpreis) {
		this.stkpreis.set(stkpreis);
	}

	public final String getStkpreis() {
		return this.stkpreis.get();
	}

	public final StringProperty mengeProperty() {
		return menge;
	}

	public final void setMenge(String menge) {
		this.menge.set(menge);
	}

	public final String getMenge() {
		return this.menge.get();
	}

	public final StringProperty ustProperty() {
		return ust;
	}

	public final void setUst(String ust) {
		this.ust.set(ust);
	}

	public final String getUst() {
		return this.ust.get();
	}

	public void setModel(Rechnungszeile rz) {
		this.setBezeichnung(rz.getBezeichnung());
		this.setId(rz.getId());
		this.setMenge(String.valueOf(rz.getMenge()));
		this.setStkpreis(String.valueOf(rz.getStueckpreis()));
		this.setUst(String.valueOf(rz.getUst()));
	}

	public void setModel(RechnungZeileModel rzm) {
		if(rzm != null) {
			this.setBezeichnung(rzm.getBezeichnung());
			this.setId(rzm.getId());
			this.setMenge(rzm.getMenge());
			this.setStkpreis(rzm.getStkpreis());
			this.setUst(rzm.getUst());
		}
		
	}

}
