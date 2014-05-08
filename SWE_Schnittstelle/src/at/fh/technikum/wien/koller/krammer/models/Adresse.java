package at.fh.technikum.wien.koller.krammer.models;

public class Adresse extends AbstractDatabaseObject {
	private AdresseEnums adressart;
	private String adrrow1;
	private String adrrow2;
	private int plz;
	private String ort;

	public Adresse(AdresseEnums adrart, String adrrow1, String adrrow2,
			int plz, String ort, long id) {
		super();
		setId(id);
		this.adressart = adrart;
		this.adrrow1 = adrrow1;
		this.adrrow2 = adrrow2;
		this.plz = plz;
		this.ort = ort;
	}
	public Adresse(AdresseEnums adrart, String adrrow1, String adrrow2,
			int plz, String ort) {
		super();
		this.adressart = adrart;
		this.adrrow1 = adrrow1;
		this.adrrow2 = adrrow2;
		this.plz = plz;
		this.ort = ort;
	}

	public Adresse() {
		super();
	}

	public AdresseEnums getAdressart() {
		return adressart;
	}

	public void setAdressart(AdresseEnums adressart) {
		this.adressart = adressart;
	}

	public String getAdrrow1() {
		return adrrow1;
	}

	public void setAdrrow1(String adrrow1) {
		this.adrrow1 = adrrow1;
	}

	public String getAdrrow2() {
		return adrrow2;
	}

	public void setAdrrow2(String adrrow2) {
		this.adrrow2 = adrrow2;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

}
