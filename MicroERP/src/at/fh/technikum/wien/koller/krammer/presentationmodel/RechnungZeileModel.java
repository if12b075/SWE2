package at.fh.technikum.wien.koller.krammer.presentationmodel;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;
import at.fh.technikum.wien.koller.krammer.observer.RechnungszeilenObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RechnungZeileModel extends RechnungszeilenObservable{
	private long id;
	private StringProperty bezeichnung = new SimpleStringProperty();
	private StringProperty stkpreis = new SimpleStringProperty();
	private StringProperty menge = new SimpleStringProperty();
	private StringProperty ust = new SimpleStringProperty();
	private boolean isUpdate = false;
	private long rechnungsId;

	public RechnungZeileModel() {
		rechnungsId = 0;
		id= 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

	public long getRechnungsId() {
		return rechnungsId;
	}

	public void setRechnungsId(long rechnungsId) {
		this.rechnungsId = rechnungsId;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
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
		this.setRechnungsId(rz.getRechnungid());
	}

	public void setModel(RechnungZeileModel rzm) {
		if(rzm != null) {
			this.setBezeichnung(rzm.getBezeichnung());
			this.setId(rzm.getId());
			this.setMenge(rzm.getMenge());
			this.setStkpreis(rzm.getStkpreis());
			this.setUst(rzm.getUst());
			this.setUpdate(rzm.isUpdate());
			this.setRechnungsId(rzm.getRechnungsId());
		}
		
	}
	
	public Rechnungszeile getRechnungszeileToSave() {
		Rechnungszeile rz = new Rechnungszeile();
		
		rz.setBezeichnung(this.getBezeichnung());
		rz.setMenge(Integer.valueOf(this.getMenge()));
		rz.setStueckpreis(Float.valueOf(this.getStkpreis()));
		rz.setUst(Integer.valueOf(this.getUst()));
		rz.setId(this.getId());
		rz.setRechnungid(this.getRechnungsId());
		
		return rz;
	}
	
	public boolean validate() {
		boolean isValid = true;
		
		if(Helper.isNullOrEmpty(this.getBezeichnung())) {
			isValid = false;
			this.setBezeichnung("Bitte geben Sie eine Bezeichnung ein");
		}
		
		if (!Helper.isNullOrEmpty(this.getMenge())) {
			try {
				Integer.parseInt(this.getMenge());
			} catch (Exception e) {
				isValid = false;
				this.setMenge("Bitte geben Sie eine gueltige Zahl ein");
			}
		} else {
			isValid = false;
			this.setMenge("Bitte geben Sie eine Menge ein");
		}
		
		if (!Helper.isNullOrEmpty(this.getUst())) {
			try {
				Integer.parseInt(this.getUst());
			} catch (Exception e) {
				isValid = false;
				this.setUst("Bitte geben Sie eine gueltige Zahl ein");
			}
		} else {
			isValid = false;
			this.setUst("Bitte geben Sie eine Ust ein");
		}
		
		if (!Helper.isNullOrEmpty(this.getStkpreis())) {
			try {
				Float.parseFloat(this.getStkpreis());
			} catch (Exception e) {
				isValid = false;
				this.setStkpreis("Bitte geben Sie eine gueltige Zahl ein");
			}
		} else {
			isValid = false;
			this.setStkpreis("Bitte geben Sie einen Stueckpreis ein");
		}
			
		return isValid;
	}

	@Override
	public void Benachrichtige() {
		for(int i=0;i<super.observers.size();i++) {
			super.observers.get(i).Update(this);
		}
	}

}
