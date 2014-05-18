package at.fh.technikum.wien.koller.krammer.presentationmodel;

import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RechnungZeileModel {
	private long id;
	private StringProperty bezeichnung = new SimpleStringProperty();
	private FloatProperty stkpreis = new SimpleFloatProperty();
	private IntegerProperty menge = new SimpleIntegerProperty();
	private IntegerProperty ust = new SimpleIntegerProperty();
	private FloatProperty brutto = new SimpleFloatProperty();
	private FloatProperty netto = new SimpleFloatProperty();
	
	
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
	
	
	public final FloatProperty stkpreisProperty() {
		return stkpreis;
	}
	public final void setStkpreis(Float stkpreis) {
		this.stkpreis.set(stkpreis);
	}
	public final Float getStkpreis() {
		return this.stkpreis.get();
	}
	
	public final IntegerProperty mengeProperty() {
		return menge;
	}
	public final void setMenge(Integer menge) {
		this.menge.set(menge);
	}
	public final Integer getMenge() {
		return this.menge.get();
	}
	
	public final IntegerProperty ustProperty() {
		return ust;
	}
	public final void setUst(Integer ust) {
		this.ust.set(ust);;
	}
	public final Integer getUst() {
		return this.ust.get();
	}

	public final FloatProperty bruttoProperty() {
		return brutto;
	}
	public final void setBrutto(Float brutto) {
		this.brutto.set(brutto);
	}
	public final Float getBrutto() {
		return this.brutto.get();
	}

	public final FloatProperty nettoProperty() {
		return netto;
	}
	public final void setNetto(Float netto) {
		this.netto.set(netto);
	}
	public final Float getNetto() {
		return this.netto.get();
	}

	public void setModel(Rechnungszeile rz) {
		this.setBezeichnung(rz.getBezeichnung());
		this.setId(rz.getId());
		this.setMenge(rz.getMenge());
		this.setStkpreis(rz.getStueckpreis());
		this.setUst(rz.getUst());
		float netto = rz.getMenge() * rz.getStueckpreis();
		this.setNetto(netto);
		float brutto = this.getNetto() + (this.getNetto() * rz.getUst() / 100);
		this.setBrutto(brutto);
	}
	
}
