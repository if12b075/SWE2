package at.fh.technikum.wien.koller.krammer.models;


public class Firma extends Kontakt {
	private String name;
	private String uid;

	public Firma() {
		super();
	}

	public Firma(String name, String uid, Adresse wohnadresse, long id) {
		super();
		setId(id);
		setWohnadresse(wohnadresse);
		this.name = name;
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Firma: " + this.name;
	}

	@Override
	public boolean isFirma() {
		return true;
	}

}
