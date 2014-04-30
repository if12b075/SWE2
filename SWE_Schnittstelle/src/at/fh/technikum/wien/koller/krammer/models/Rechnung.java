package at.fh.technikum.wien.koller.krammer.models;

import java.util.ArrayList;
import java.util.Date;

public class Rechnung extends AbstractDatabaseObject {
	private long rechnungsnummer;
	private Date datum;
	private Date faelligkeitsdatum;
	private String kommentar;
	private String nachricht;
	private long kontaktid;
	private ArrayList<Rechnungszeile> rechnungszeilen;
	private Date bezahltAm;

	public Rechnung() {
		super();
	}

	public Rechnung(int rechnungsnummer, Date datum, Date faelligkeitsdatum,
			String kommentar, String nachricht, long kontaktid,
			ArrayList<Rechnungszeile> rechnungszeilen, long id) {
		super();
		setId(id);
		this.rechnungsnummer = rechnungsnummer;
		this.datum = datum;
		this.faelligkeitsdatum = faelligkeitsdatum;
		this.kommentar = kommentar;
		this.nachricht = nachricht;
		this.kontaktid = kontaktid;
		this.rechnungszeilen = rechnungszeilen;
	}

	public long getRechnungsnummer() {
		return rechnungsnummer;
	}

	public void setRechnungsnummer(long rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getFaelligkeitsdatum() {
		return faelligkeitsdatum;
	}

	public void setFaelligkeitsdatum(Date faelligkeitsdatum) {
		this.faelligkeitsdatum = faelligkeitsdatum;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}

	public long getKontaktid() {
		return kontaktid;
	}

	public void setKontaktid(long kontaktid) {
		this.kontaktid = kontaktid;
	}

	public ArrayList<Rechnungszeile> getRechnungszeilen() {
		return rechnungszeilen;
	}

	public void setRechnungszeilen(ArrayList<Rechnungszeile> rechnungszeilen) {
		this.rechnungszeilen = rechnungszeilen;
	}
	
	public Date getBezahltAm() {
		return bezahltAm;
	}

	public void setBezahltAm(Date bezahltAm) {
		this.bezahltAm = bezahltAm;
	}

	@Override
	public String toString() {
		return "Rechnung: " + this.rechnungsnummer + " " + this.datum + " " + this.nachricht;
	}
	
	

}
