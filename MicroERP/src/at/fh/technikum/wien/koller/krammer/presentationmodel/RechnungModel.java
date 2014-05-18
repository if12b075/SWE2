package at.fh.technikum.wien.koller.krammer.presentationmodel;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RechnungModel {
	private long id;
	private StringProperty datum = new SimpleStringProperty();
	private StringProperty faelligkeit = new SimpleStringProperty();
	private StringProperty rechnungnr = new SimpleStringProperty();
	private StringProperty kommentar = new SimpleStringProperty();
	private StringProperty nachricht = new SimpleStringProperty();
	private StringProperty bezahltam = new SimpleStringProperty();
	private boolean bezahlt;
	private boolean isUpdate;
	private Map<Long, String> rechnungszeilen;
	
	public RechnungModel() {
		rechnungszeilen = new HashMap<Long, String>();
		bezahlt = false;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public final StringProperty datumProperty() {
		return datum;
	}
	
	public String getDatum() {
		return datum.get();
	}

	public void setDatum(String datum) {
		this.datum.set(datum);
	}
	
	public final StringProperty faelligkeitProperty() {
		return faelligkeit;
	}
	
	public String getFaelligkeit() {
		return faelligkeit.get();
	}

	public void setFaelligkeit(String faelligkeit) {
		this.faelligkeit.set(faelligkeit);
	}
	
	public final StringProperty rechnungnrProperty() {
		return rechnungnr;
	}
	
	public String getRechnungnr() {
		return rechnungnr.get();
	}

	public void setRechnungnr(String rechnungnr) {
		this.rechnungnr.set(rechnungnr);
	}
	
	public final StringProperty kommentarProperty() {
		return kommentar;
	}
	
	public String getKommentar() {
		return kommentar.get();
	}

	public void setKommentar(String kommentar) {
		this.kommentar.set(kommentar);
	}
	
	public final StringProperty nachrichtProperty() {
		return nachricht;
	}
	
	public String getNachricht() {
		return nachricht.get();
	}

	public void setNachricht(String nachricht) {
		this.nachricht.set(nachricht);
	}
	
	public final StringProperty bezahltamProperty() {
		return bezahltam;
	}
	
	public String getBezahltam() {
		return bezahltam.get();
	}

	public void setBezahltam(String bezahltam) {
		this.bezahltam.set(bezahltam);
	}
	
	public boolean isBezahlt() {
		return bezahlt;
	}

	public void setBezahlt(boolean bezahlt) {
		this.bezahlt = bezahlt;
	}
	
	

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}



	public Map<Long, String> getRechnungszeilen() {
		return rechnungszeilen;
	}

	public void setRechnungszeilen(Map<Long, String> rechnungszeilen) {
		this.rechnungszeilen = rechnungszeilen;
	}

	public void setModel(Rechnung r) {
		this.setId(r.getId());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(r.getDatum() != null) {
			String formattedDate = formatter.format(r.getDatum());
			this.setDatum(formattedDate);
		} else {
			this.setDatum("");
		}
		
		if(r.getFaelligkeitsdatum() != null) {
			String formattedDate = formatter.format(r.getFaelligkeitsdatum());
			this.setFaelligkeit(formattedDate);
		} else {
			this.setFaelligkeit("");
		}
		
		if(r.getBezahltAm() != null) {
			String formattedDate = formatter.format(r.getBezahltAm());
			this.setBezahltam(formattedDate);
			this.bezahlt = true;
		} else {
			this.setBezahltam("");
			this.bezahlt = false;
		}
		
		this.setKommentar(r.getKommentar());
		this.setRechnungnr(String.valueOf(r.getRechnungsnummer()));
		this.setNachricht(r.getNachricht());
		
		for(int i=0; i < r.getRechnungszeilen().size(); i++)
			this.rechnungszeilen.put(r.getRechnungszeilen().get(i).getId(), r.getRechnungszeilen().get(i).toString());
			
	}
	
	public void setModel(RechnungModel rm) {
		this.setId(rm.getId());
		this.setBezahlt(rm.isBezahlt());
		this.setBezahltam(rm.getBezahltam());
		this.setDatum(rm.getDatum());
		this.setFaelligkeit(rm.getFaelligkeit());
		this.setKommentar(rm.getKommentar());
		this.setNachricht(rm.getNachricht());
		this.setRechnungnr(rm.getRechnungnr());
		this.setRechnungszeilen(rm.getRechnungszeilen());
	}
}
