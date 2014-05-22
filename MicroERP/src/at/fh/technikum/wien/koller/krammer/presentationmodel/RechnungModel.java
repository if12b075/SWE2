package at.fh.technikum.wien.koller.krammer.presentationmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.models.Rechnungszeile;
import at.fh.technikum.wien.koller.krammer.observer.RechnungszeilenObserver;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RechnungModel implements RechnungszeilenObserver{
	private long id;
	private StringProperty datum = new SimpleStringProperty();
	private StringProperty faelligkeit = new SimpleStringProperty();
	private StringProperty rechnungnr = new SimpleStringProperty();
	private StringProperty kommentar = new SimpleStringProperty();
	private StringProperty nachricht = new SimpleStringProperty();
	private StringProperty bezahltam = new SimpleStringProperty();
	private boolean bezahlt;
	private boolean isUpdate;
	private ArrayList<String> rechnungszeilen;
	private ArrayList<Rechnungszeile> rechnungszeilen2;
	
	private CustomControlModel ccm;
	private long kontaktid;
	
	public RechnungModel() {
		rechnungszeilen = new ArrayList<String>();
		rechnungszeilen2 = new ArrayList<Rechnungszeile>();
		bezahlt = false;
		id = 0;
		
		ccm = new CustomControlModel();
		ccm.setIsChangeable(true);
		ccm.setOk(false);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKontaktid() {
		return kontaktid;
	}

	public void setKontaktid(long kontaktid) {
		this.kontaktid = kontaktid;
	}

	public CustomControlModel getCcm() {
		return ccm;
	}

	public void setCcm(CustomControlModel ccm) {
		this.ccm = ccm;
	}

	public final StringProperty datumProperty() {
		return datum;
	}
	
	public String getDatum() {
		return datum.get();
	}

	public void setDatum(String datum) {
		this.datum.set(datum);
	}
	
	public final StringProperty faelligkeitProperty() {
		return faelligkeit;
	}
	
	public String getFaelligkeit() {
		return faelligkeit.get();
	}

	public void setFaelligkeit(String faelligkeit) {
		this.faelligkeit.set(faelligkeit);
	}
	
	public final StringProperty rechnungnrProperty() {
		return rechnungnr;
	}
	
	public String getRechnungnr() {
		return rechnungnr.get();
	}

	public void setRechnungnr(String rechnungnr) {
		this.rechnungnr.set(rechnungnr);
	}
	
	public final StringProperty kommentarProperty() {
		return kommentar;
	}
	
	public String getKommentar() {
		return kommentar.get();
	}

	public void setKommentar(String kommentar) {
		this.kommentar.set(kommentar);
	}
	
	public final StringProperty nachrichtProperty() {
		return nachricht;
	}
	
	public String getNachricht() {
		return nachricht.get();
	}

	public void setNachricht(String nachricht) {
		this.nachricht.set(nachricht);
	}
	
	public final StringProperty bezahltamProperty() {
		return bezahltam;
	}
	
	public String getBezahltam() {
		return bezahltam.get();
	}

	public void setBezahltam(String bezahltam) {
		this.bezahltam.set(bezahltam);
	}
	
	public boolean isBezahlt() {
		return bezahlt;
	}

	public void setBezahlt(boolean bezahlt) {
		this.bezahlt = bezahlt;
	}
	
	

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	public ArrayList<Rechnungszeile> getRechnungszeilen2() {
		return rechnungszeilen2;
	}

	public void setRechnungszeilen2(ArrayList<Rechnungszeile> rechnungszeilen2) {
		this.rechnungszeilen2 = rechnungszeilen2;
	}

	public ArrayList<String> getRechnungszeilen() {
		return rechnungszeilen;
	}

	public void setRechnungszeilen(ArrayList<String> rechnungszeilen) {
		this.rechnungszeilen = rechnungszeilen;
	}

	public void setModel(Rechnung r) {
	
		this.setId(r.getId());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(r.getDatum() != null) {
			String formattedDate = formatter.format(r.getDatum());
			this.setDatum(formattedDate);
		} else {
			this.setDatum("");
		}
		
		if(r.getFaelligkeitsdatum() != null) {
			String formattedDate = formatter.format(r.getFaelligkeitsdatum());
			this.setFaelligkeit(formattedDate);
		} else {
			this.setFaelligkeit("");
		}
		
		if(r.getBezahltAm() != null) {
			String formattedDate = formatter.format(r.getBezahltAm());
			this.setBezahltam(formattedDate);
			this.bezahlt = true;
		} else {
			this.setBezahltam("");
			this.bezahlt = false;
		}
		
		this.setKommentar(r.getKommentar());
		this.setRechnungnr(String.valueOf(r.getRechnungsnummer()));
		this.setNachricht(r.getNachricht());
		
		
		
		for(int i=0; i < r.getRechnungszeilen().size(); i++) {
			this.rechnungszeilen.add(r.getRechnungszeilen().get(i).toString());
			this.rechnungszeilen2.add(r.getRechnungszeilen().get(i));
		}
		
		this.getCcm().setIsChangeable(true);
		
		this.getCcm().setLabelText("Kontakt:");
		
		
		if(r.getKontaktid() != 0) {
			this.getCcm().setKontaktid(r.getKontaktid());
			this.setKontaktid(r.getKontaktid());
			
			
			Kontakt search = new Person();
			search.setId(r.getKontaktid());
			Kontakt k = MERPProxyFactory.getKontaktById(search);
			
			if(k.isFirma()) {
				Firma f = MERPProxyFactory.getFirmaById(k.getId());
				
				this.getCcm().setTextField(f.getName());
				
				
			} else {
				Person p = MERPProxyFactory.getPersonById(k.getId());
				this.getCcm().setTextField(p.getNachname() + " "+ p.getVorname());
			}
			this.getCcm().setOk(true);
			
		}
				
			
	}
	
	public void setModel(RechnungModel rm) {
		if(rm != null) {
			this.setId(rm.getId());
			this.setBezahlt(rm.isBezahlt());
			this.setBezahltam(rm.getBezahltam());
			this.setDatum(rm.getDatum());
			this.setFaelligkeit(rm.getFaelligkeit());
			this.setKommentar(rm.getKommentar());
			this.setNachricht(rm.getNachricht());
			this.setRechnungnr(rm.getRechnungnr());
			this.setRechnungszeilen(rm.getRechnungszeilen());
			this.setRechnungszeilen2(rm.getRechnungszeilen2());
			this.setCcm(rm.getCcm());
			this.setUpdate(rm.isUpdate());
		}
		
	}
	
	public Rechnung getRechnungToSave() {
		Rechnung r = new Rechnung();
		
		r.setId(this.getId());
		r.setKontaktid(this.getCcm().getKontaktid());
		
		SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			r.setDatum(sdfToDate.parse(this.getDatum()));
			r.setFaelligkeitsdatum(sdfToDate.parse(this.getFaelligkeit()));
			
			if(isBezahlt())
				r.setBezahltAm(sdfToDate.parse(this.getBezahltam()));
			else
				r.setBezahltAm(null);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Helper.isNullOrEmpty(getKommentar()))
			r.setKommentar(null);
		else
			r.setKommentar(this.getKommentar());

		r.setNachricht(this.getNachricht());
		r.setRechnungsnummer(Long.parseLong(this.getRechnungnr()));
		
		r.setRechnungszeilen(this.getRechnungszeilen2());
		
		return r;
	}
	
	public Rechnungszeile getRechnungsZeileById(long id) {
		for(int i=0; i< rechnungszeilen2.size();i++) {
			if(rechnungszeilen2.get(i).getId() == id)
				return rechnungszeilen2.get(i);
		}
		
		return null;
	}

	@Override
	public void Update(RechnungZeileModel rz) {
		if(rz.isUpdate()) {
			Rechnungszeile r = getRechnungsZeileById(rz.getId());
			Rechnungszeile r2 = rz.getRechnungszeileToSave();
			
			r.setBezeichnung(r2.getBezeichnung());
			r.setMenge(r2.getMenge());
			r.setUst(r2.getUst());
			r.setStueckpreis(r2.getStueckpreis());
			
			rechnungszeilen.clear();
			for(int i=0; i < rechnungszeilen2.size(); i++) {
				this.rechnungszeilen.add(rechnungszeilen2.get(i).toString());
			}

		} else {
			rechnungszeilen.add(rz.getRechnungszeileToSave().toString());
			rechnungszeilen2.add(rz.getRechnungszeileToSave());
			
		}
		
	}
	
	public boolean validate() {
		boolean isValid = true;

		if(rechnungszeilen2.size()< 1) {
			isValid = false;
			System.out.println("Bitte geben Sie mindestens eine Rechnungszeile an");
		}

		System.out.println(ccm.getKontaktid());
		if(ccm.getKontaktid() < 1) {
			isValid = false;
			ccm.setTextField("Bitte ordnen Sie die Rechnung einem Kontakt zu");
		}

		if(Helper.isNullOrEmpty(getRechnungnr())) {
			isValid = false;
			this.setRechnungnr("Bitte geben Sie eine Rechnungsnummer an");
		} else {
			try {
				Long.parseLong(getRechnungnr());
			} catch (Exception e) {
				isValid = false;
				setRechnungnr("Bitte eine Nummer eingeben");
			}
		}

		if(Helper.isNullOrEmpty(getDatum())) {
			isValid = false;
			this.setDatum("Bitte geben Sie ein Datum an");
		} else {
			try {
				SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
				sdfToDate.parse(getDatum());
			} catch (ParseException e) {
				this.setDatum("Bitte Datum im Format dd/MM/yyyy");
				isValid=false;
			}
		}

		if(Helper.isNullOrEmpty(getFaelligkeit())) {
			isValid = false;
			this.setFaelligkeit("Bitte geben Sie ein Faelligkeitsdatum an");
		} else {
			try {
				SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
				sdfToDate.parse(getFaelligkeit());
			} catch (ParseException e) {
				this.setFaelligkeit("Bitte Datum im Format dd/MM/yyyy");
				isValid=false;
			}
		}

		if(Helper.isNullOrEmpty(getNachricht())) {
			isValid = false;
			this.setNachricht("Bitte geben Sie eine Nachricht an");
		}

		if(isBezahlt()) {
			if(Helper.isNullOrEmpty(getBezahltam())) {
				isValid = false;
				this.setBezahltam("Bitte geben Sie das Bezahlt Datum an");
			} else {
				try {
					SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
					sdfToDate.parse(getBezahltam());
				} catch (ParseException e) {
					this.setBezahltam("Bitte Datum im Format dd/MM/yyyy");
					isValid=false;
				}
			}
		}

		return isValid;
	}
}
