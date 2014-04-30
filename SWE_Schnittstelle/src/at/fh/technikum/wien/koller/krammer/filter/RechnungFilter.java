package at.fh.technikum.wien.koller.krammer.filter;

import java.util.Date;

public class RechnungFilter {
	private Date vonDatum;
	private Date bisDatum;
	private long vonBetrag;
	private long bisBetrag;
	private KontaktFilter kontaktFilter;
	
	public Date getVonDatum() {
		return vonDatum;
	}
	public void setVonDatum(Date vonDatum) {
		this.vonDatum = vonDatum;
	}
	public Date getBisDatum() {
		return bisDatum;
	}
	public void setBisDatum(Date bisDatum) {
		this.bisDatum = bisDatum;
	}
	public long getVonBetrag() {
		return vonBetrag;
	}
	public void setVonBetrag(long vonBetrag) {
		this.vonBetrag = vonBetrag;
	}
	public long getBisBetrag() {
		return bisBetrag;
	}
	public void setBisBetrag(long bisBetrag) {
		this.bisBetrag = bisBetrag;
	}
	public KontaktFilter getKontaktFilter() {
		return kontaktFilter;
	}
	public void setKontaktFilter(KontaktFilter kontaktFilter) {
		this.kontaktFilter = kontaktFilter;
	}
	
}
