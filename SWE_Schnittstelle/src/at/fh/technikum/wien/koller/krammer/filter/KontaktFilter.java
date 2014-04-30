package at.fh.technikum.wien.koller.krammer.filter;

public class KontaktFilter {
	private String name;
	private Boolean isFirma;
	
	public KontaktFilter(String name, Boolean isFirma) {
		this.name = name;
		this.isFirma = isFirma;
	}
	
	public KontaktFilter() {
		this.name = null;
		this.isFirma = null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsFirma() {
		return isFirma;
	}
	public void setIsFirma(Boolean isFirma) {
		this.isFirma = isFirma;
	}
}
