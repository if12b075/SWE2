package at.fh.technikum.wien.koller.krammer.presentationmodel;

import at.fh.technikum.wien.koller.krammer.filter.KontaktFilter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchModel {
	private StringProperty searchname = new SimpleStringProperty();
	private Boolean isFirma = null;
	private Boolean isChangeable;
	private long kontaktid;
	private CustomControlModel ccm;
	
	public SearchModel() {
		
	}
	
	public final StringProperty searchnameProperty() {
		return searchname;
	}
	
	public String getSearchname() {
		return searchname.get();
	}

	public void setSearchname(String searchname) {
		this.searchname.set(searchname);
	}

	public Boolean getIsFirma() {
		return isFirma;
	}

	public Boolean getIsChangeable() {
		return isChangeable;
	}

	public void setIsChangeable(Boolean isChangeable) {
		this.isChangeable = isChangeable;
	}

	public void setIsFirma(Boolean isFirma) {
		this.isFirma = isFirma;
	}
	
	public void setModel(SearchModel sm) {
		this.setSearchname(sm.getSearchname());
		this.isFirma = sm.getIsFirma();
		this.isChangeable = sm.getIsChangeable();
		if(!isChangeable)
			this.isFirma = true;
			
		
	}
	
	public long getKontaktid() {
		return kontaktid;
	}

	public void setKontaktid(long kontaktid) {
		this.kontaktid = kontaktid;
	}

	public void setModel(KontaktFilter kf) {
		this.setSearchname(kf.getName());
		this.isFirma = kf.getIsFirma();
	}
	
	public KontaktFilter getKontaktFilter() {
		KontaktFilter kf = new KontaktFilter();

		kf.setName(getSearchname());
		kf.setIsFirma(isFirma);
		
		return kf;
	}

	public CustomControlModel getCcm() {
		return ccm;
	}

	public void setCcm(CustomControlModel ccm) {
		this.ccm = ccm;
	}
	
	
}
