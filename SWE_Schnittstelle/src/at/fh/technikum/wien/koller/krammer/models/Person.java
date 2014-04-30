package at.fh.technikum.wien.koller.krammer.models;

import java.util.Date;

public class Person extends Kontakt {
	private String titel;
	private String vorname;
	private String nachname;
	private String suffix;
	private Date geburtstag;
	private long firmaid;

	public Person() {
		super();
	}

	public Person(String vorname, String nachname, Date geburtstag,
			long firmaid, Adresse wohnadresse, long id) {
		super();
		setId(id);
		setWohnadresse(wohnadresse);
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtstag = geburtstag;
		this.firmaid = firmaid;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	public long getFirmaid() {
		return firmaid;
	}

	public void setFirmaid(long firmaid) {
		this.firmaid = firmaid;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String toString() {
		return this.titel + " " + this.suffix + " " + this.vorname + " " +this.nachname;
	}

	@Override
	public boolean isFirma() {
		return false;
	}

}
