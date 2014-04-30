package at.fh.technikum.wien.koller.krammer.models;

public abstract class Kontakt extends AbstractDatabaseObject {
	protected Adresse wohnadresse;
	protected Adresse lieferadresse;
	protected Adresse rechnungsadresse;

	public Kontakt() {
		super();
	}

	public Adresse getWohnadresse() {
		return wohnadresse;
	}

	public void setWohnadresse(Adresse wohnadresse) {
		this.wohnadresse = wohnadresse;
	}

	public Adresse getLieferadresse() {
		return lieferadresse;
	}

	public void setLieferadresse(Adresse lieferadresse) {
		this.lieferadresse = lieferadresse;
	}

	public Adresse getRechnungsadresse() {
		return rechnungsadresse;
	}

	public void setRechnungsadresse(Adresse rechnungsadresse) {
		this.rechnungsadresse = rechnungsadresse;
	}

	public abstract String toString();
	public abstract boolean isFirma();
}
