package at.fh.technikum.wien.koller.krammer.models;

public class Rechnungszeile extends AbstractDatabaseObject {
	private long rechnungid;
	private String bezeichnung;
	private float stueckpreis;
	private int menge;
	private int ust;

	public Rechnungszeile() {
		super();
	}

	public Rechnungszeile(String bezeichnung, float stueckpreis, int menge,
			int ust, long id) {
		super();
		setId(id);
		this.bezeichnung = bezeichnung;
		this.stueckpreis = stueckpreis;
		this.menge = menge;
		this.ust = ust;
	}

	public long getRechnungid() {
		return rechnungid;
	}

	public void setRechnungid(long rechnungid) {
		this.rechnungid = rechnungid;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public float getStueckpreis() {
		return stueckpreis;
	}

	public void setStueckpreis(float stueckpreis) {
		this.stueckpreis = stueckpreis;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public int getUst() {
		return ust;
	}

	public void setUst(int ust) {
		this.ust = ust;
	}

	@Override
	public String toString() {
		return this.bezeichnung
				+ "    "
				+ " Menge: "
				+ this.menge
				+ "     StkPreis: "
				+ this.stueckpreis
				+ " €     "
				+ this.ust
				+ "% Ust "
				+ "    Netto: "
				+ (this.menge)
				* (this.stueckpreis)
				+ "     Brutto: "
				+ ((this.menge) * (this.stueckpreis) + ((this.menge)
						* (this.stueckpreis) * this.ust / 100));
	}

}
