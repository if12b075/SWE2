package at.fh.technikum.wien.koller.krammer.filter;

import java.util.Date;

public class RechnungFilter {
	private Date vonDatum;
	private Date bisDatum;
	private double vonBetrag;
	private double bisBetrag;
	private KontaktFilter kontaktFilter;

	public RechnungFilter(Date vonDatum, Date bisDatum, long vonBetrag,
			long bisBetrag, KontaktFilter kontaktFilter) {
		this.vonDatum = vonDatum;
		this.bisDatum = bisDatum;
		this.vonBetrag = vonBetrag;
		this.bisBetrag = bisBetrag;
		this.kontaktFilter = kontaktFilter;
	}
	
	public RechnungFilter() {
		this.vonDatum = null;
		this.bisDatum = null;
		this.vonBetrag = 0;
		this.bisBetrag = 0;
		this.kontaktFilter = null;
	}

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

	public double getVonBetrag() {
		return vonBetrag;
	}

	public void setVonBetrag(double vonBetrag) {
		this.vonBetrag = vonBetrag;
	}

	public double getBisBetrag() {
		return bisBetrag;
	}

	public void setBisBetrag(double bisBetrag) {
		this.bisBetrag = bisBetrag;
	}

	public KontaktFilter getKontaktFilter() {
		return kontaktFilter;
	}

	public void setKontaktFilter(KontaktFilter kontaktFilter) {
		this.kontaktFilter = kontaktFilter;
	}

}
