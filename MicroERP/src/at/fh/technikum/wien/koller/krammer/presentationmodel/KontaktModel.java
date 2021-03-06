package at.fh.technikum.wien.koller.krammer.presentationmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import at.fh.technikum.wien.koller.krammer.models.Adresse;
import at.fh.technikum.wien.koller.krammer.models.AdresseEnums;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class KontaktModel {
	private long id;
	private long wohnadrid;
	private long rechadrid;
	private long lieferadrid;
	private long firmaid;
	private boolean isUpdate = false;

	private CustomControlModel ccm = new CustomControlModel();

	private StringProperty vorname = new SimpleStringProperty();
	private StringProperty nachname = new SimpleStringProperty();
	private StringProperty firmenname = new SimpleStringProperty();
	private StringProperty UID = new SimpleStringProperty();
	private StringProperty suffix = new SimpleStringProperty();
	private StringProperty titel = new SimpleStringProperty();
	private StringProperty geburtstag = new SimpleStringProperty();

	private StringProperty wohnadress1 = new SimpleStringProperty();
	private StringProperty wohnadress2 = new SimpleStringProperty();
	private StringProperty wohnplz = new SimpleStringProperty();
	private StringProperty wohnort = new SimpleStringProperty();

	private StringProperty lieferadress1 = new SimpleStringProperty();
	private StringProperty lieferadress2 = new SimpleStringProperty();
	private StringProperty lieferplz = new SimpleStringProperty();
	private StringProperty lieferort = new SimpleStringProperty();

	private StringProperty rechnungadress1 = new SimpleStringProperty();
	private StringProperty rechnungadress2 = new SimpleStringProperty();
	private StringProperty rechnungplz = new SimpleStringProperty();
	private StringProperty rechnungort = new SimpleStringProperty();

	private BooleanBinding isFirma = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return !Helper.isNullOrEmpty(getFirmenname());
		}
	};
	private BooleanBinding disableEditPerson = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return !Helper.isNullOrEmpty(getFirmenname())
					&& Helper.isNullOrEmpty(getVorname())
					&& Helper.isNullOrEmpty(getNachname());
		}
	};
	private BooleanBinding disableEditFirma = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return Helper.isNullOrEmpty(getFirmenname())
					&& (!Helper.isNullOrEmpty(getVorname()) || !Helper
							.isNullOrEmpty(getNachname()));
		}
	};

	public KontaktModel() {
		ChangeListener<String> canEditListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				isFirma.invalidate();
				disableEditPerson.invalidate();
				disableEditFirma.invalidate();
			}
		};
		ccm.setIsChangeable(false);
		vorname.addListener(canEditListener);
		nachname.addListener(canEditListener);
		firmenname.addListener(canEditListener);
	}

	public final StringProperty vornameProperty() {
		return vorname;
	}

	public final StringProperty nachnameProperty() {
		return nachname;
	}

	public final StringProperty firmennameProperty() {
		return firmenname;
	}

	public final StringProperty UIDProperty() {
		return UID;
	}

	public final BooleanBinding isFirmaBinding() {
		return isFirma;
	}

	public BooleanBinding disableEditPersonBinding() {
		return disableEditPerson;
	}

	public BooleanBinding disableEditFirmaBinding() {
		return disableEditFirma;
	}

	public final StringProperty suffixProperty() {
		return suffix;
	}

	public final StringProperty titelProperty() {
		return titel;
	}

	public final StringProperty geburtstagProperty() {
		return geburtstag;
	}

	public final StringProperty wohnadress1Property() {
		return wohnadress1;
	}

	public final StringProperty wohnadress2Property() {
		return wohnadress2;
	}

	public final StringProperty wohnplzProperty() {
		return wohnplz;
	}

	public final StringProperty wohnortProperty() {
		return wohnort;
	}

	public final StringProperty rechnungadress1Property() {
		return rechnungadress1;
	}

	public final StringProperty rechnungadress2Property() {
		return rechnungadress2;
	}

	public final StringProperty rechnungplzProperty() {
		return rechnungplz;
	}

	public final StringProperty rechnungortProperty() {
		return rechnungort;
	}

	public final StringProperty lieferadress1Property() {
		return lieferadress1;
	}

	public final StringProperty lieferadress2Property() {
		return lieferadress2;
	}

	public final StringProperty lieferplzProperty() {
		return lieferplz;
	}

	public final StringProperty lieferortProperty() {
		return lieferort;
	}

	public String getVorname() {
		return vorname.get();
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public String getNachname() {
		return nachname.get();
	}

	public void setNachname(String nachname) {
		this.nachname.set(nachname);
	}

	public String getFirmenname() {
		return firmenname.get();
	}

	public void setFirmenname(String firmenname) {
		this.firmenname.set(firmenname);
	}

	public String getUID() {
		return UID.get();
	}

	public void setUID(String uID) {
		UID.set(uID);
	}

	public boolean isFirma() {
		return isFirma.get();
	}

	public boolean disableEditPerson() {
		return disableEditPerson.get();
	}

	public boolean disableEditFirma() {
		return disableEditFirma.get();
	}

	public String getSuffix() {
		return suffix.get();
	}

	public void setSuffix(String suffix) {
		this.suffix.set(suffix);
	}

	public String getTitel() {
		return titel.get();
	}

	public void setTitel(String titel) {
		this.titel.set(titel);
	}

	public String getGeburtstag() {
		return geburtstag.get();
	}

	public void setGeburtstag(String geburtstag) {
		this.geburtstag.set(geburtstag);
	}

	public String getWohnadress1() {
		return wohnadress1.get();
	}

	public void setWohnadress1(String wohnadress1) {
		this.wohnadress1.set(wohnadress1);
	}

	public String getWohnadress2() {
		return wohnadress2.get();
	}

	public void setWohnadress2(String wohnadress2) {
		this.wohnadress2.set(wohnadress2);
	}

	public String getWohnplz() {
		return wohnplz.get();
	}

	public void setWohnplz(String wohnplz) {
		this.wohnplz.set(wohnplz);
	}

	public String getWohnort() {
		return wohnort.get();
	}

	public void setWohnort(String wohnort) {
		this.wohnort.set(wohnort);
	}

	public String getLieferadress1() {
		return lieferadress1.get();
	}

	public void setLieferadress1(String lieferadress1) {
		this.lieferadress1.set(lieferadress1);
	}

	public String getLieferadress2() {
		return lieferadress2.get();
	}

	public void setLieferadress2(String lieferadress2) {
		this.lieferadress2.set(lieferadress2);
	}

	public String getLieferplz() {
		return lieferplz.get();
	}

	public void setLieferplz(String lieferplz) {
		this.lieferplz.set(lieferplz);
	}

	public String getLieferort() {
		return lieferort.get();
	}

	public void setLieferort(String lieferort) {
		this.lieferort.set(lieferort);
	}

	public String getRechnungadress1() {
		return rechnungadress1.get();
	}

	public void setRechnungadress1(String rechnungadress1) {
		this.rechnungadress1.set(rechnungadress1);
	}

	public String getRechnungadress2() {
		return rechnungadress2.get();
	}

	public void setRechnungadress2(String rechnungadress2) {
		this.rechnungadress2.set(rechnungadress2);
	}

	public String getRechnungplz() {
		return rechnungplz.get();
	}

	public void setRechnungplz(String rechnungplz) {
		this.rechnungplz.set(rechnungplz);
	}

	public String getRechnungort() {
		return rechnungort.get();
	}

	public void setRechnungort(String rechnungort) {
		this.rechnungort.set(rechnungort);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFirmaid() {
		return firmaid;
	}

	public void setFirmaid(long firmaid) {
		this.firmaid = firmaid;
	}

	public long getWohnadrid() {
		return wohnadrid;
	}

	public void setWohnadrid(long wohnadrid) {
		this.wohnadrid = wohnadrid;
	}

	public long getRechadrid() {
		return rechadrid;
	}

	public void setRechadrid(long rechadrid) {
		this.rechadrid = rechadrid;
	}

	public long getLieferadrid() {
		return lieferadrid;
	}

	public void setLieferadrid(long lieferadrid) {
		this.lieferadrid = lieferadrid;
	}

	public CustomControlModel getCcm() {
		return ccm;
	}

	public void setCcm(CustomControlModel ccm) {
		this.ccm = ccm;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setModel(Kontakt k) {
		this.setId(k.getId());
		if (k.isFirma()) {
			Firma f = MERPProxyFactory.getFirmaById(k.getId());

			if (f != null) {
				if (f.getLieferadresse() != null) {
					this.setLieferadrid(f.getLieferadresse().getId());
					this.setLieferadress1(f.getLieferadresse().getAdrrow1());
					this.setLieferadress2(f.getLieferadresse().getAdrrow2());
					this.setLieferort(f.getLieferadresse().getOrt());
					this.setLieferplz(String.valueOf(f.getLieferadresse()
							.getPlz()));
				}

				if (f.getRechnungsadresse() != null) {
					this.setRechadrid(f.getRechnungsadresse().getId());
					this.setRechnungadress1(f.getRechnungsadresse()
							.getAdrrow1());
					this.setRechnungadress2(f.getRechnungsadresse()
							.getAdrrow2());
					this.setRechnungort(f.getRechnungsadresse().getOrt());
					this.setRechnungplz(String.valueOf(f.getRechnungsadresse()
							.getPlz()));
				}

				if (f.getWohnadresse() != null) {
					this.setWohnadrid(f.getWohnadresse().getId());
					this.setWohnadress1(f.getWohnadresse().getAdrrow1());
					this.setWohnadress2(f.getWohnadresse().getAdrrow2());
					this.setWohnort(f.getWohnadresse().getOrt());
					this.setWohnplz(String.valueOf(f.getWohnadresse().getPlz()));
				}

				this.setFirmenname(f.getName());
				this.setUID(f.getUid());

			} else
				System.out.println("Model konnte nicht gesetzt werden!");

		} else {
			Person p = MERPProxyFactory.getPersonById(k.getId());

			if (p != null) {
				firmaid = p.getFirmaid();

				if (firmaid != 0) {
					Firma f = MERPProxyFactory.getFirmaById(firmaid);
					this.ccm.setKontaktid(firmaid);
					this.ccm.setTextField(f.getName());
					this.ccm.setIsChangeable(false);

					this.ccm.setOk(true);

				} else {
					this.ccm.setOk(false);
				}

				if (p.getLieferadresse() != null) {
					this.setLieferadrid(p.getLieferadresse().getId());
					this.setLieferadress1(p.getLieferadresse().getAdrrow1());
					this.setLieferadress2(p.getLieferadresse().getAdrrow2());
					this.setLieferort(p.getLieferadresse().getOrt());
					this.setLieferplz(String.valueOf(p.getLieferadresse()
							.getPlz()));
				}

				if (p.getRechnungsadresse() != null) {
					this.setRechadrid(p.getRechnungsadresse().getId());
					this.setRechnungadress1(p.getRechnungsadresse()
							.getAdrrow1());
					this.setRechnungadress2(p.getRechnungsadresse()
							.getAdrrow2());
					this.setRechnungort(p.getRechnungsadresse().getOrt());
					this.setRechnungplz(String.valueOf(p.getRechnungsadresse()
							.getPlz()));
				}

				if (p.getWohnadresse() != null) {
					this.setWohnadrid(p.getWohnadresse().getId());
					this.setWohnadress1(p.getWohnadresse().getAdrrow1());
					this.setWohnadress2(p.getWohnadresse().getAdrrow2());
					this.setWohnort(p.getWohnadresse().getOrt());
					this.setWohnplz(String.valueOf(p.getWohnadresse().getPlz()));
				}

				this.setVorname(p.getVorname());
				this.setNachname(p.getNachname());
				this.setSuffix(p.getSuffix());
				this.setTitel(p.getTitel());
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				if (p.getGeburtstag() != null) {
					String formattedDate = formatter.format(p.getGeburtstag());
					this.setGeburtstag(formattedDate);
				}

			} else
				System.out.println("Model konnte nicht gesetzt werden!");
		}

	}

	public void setModel(KontaktModel km) {
		if (km != null) {
			this.setUpdate(km.isUpdate());
			this.setId(km.getId());
			this.ccm.setModel(km.getCcm());
			this.setFirmaid(km.getFirmaid());
			this.setLieferadrid(km.getLieferadrid());
			this.setRechadrid(km.getRechadrid());
			this.setWohnadrid(km.getWohnadrid());

			this.setFirmenname(km.getFirmenname());
			this.setUID(km.getUID());
			this.setVorname(km.getVorname());
			this.setNachname(km.getNachname());
			this.setSuffix(km.getSuffix());
			this.setTitel(km.getTitel());
			this.setGeburtstag(km.getGeburtstag());

			this.setWohnadress1(km.getWohnadress1());
			this.setWohnadress2(km.getWohnadress2());
			this.setWohnort(km.getWohnort());
			this.setWohnplz(km.getWohnplz());

			this.setRechnungadress1(km.getRechnungadress1());
			this.setRechnungadress2(km.getRechnungadress2());
			this.setRechnungort(km.getRechnungort());
			this.setRechnungplz(km.getRechnungplz());

			this.setLieferadress1(km.getLieferadress1());
			this.setLieferadress2(km.getLieferadress2());
			this.setLieferort(km.getLieferort());
			this.setLieferplz(km.getLieferplz());
		}

	}

	public Person getPersonToSave() {

		Person p = new Person();
		p.setId(this.getId());

		p.setFirmaid(this.getCcm().getKontaktid());
		p.setVorname(this.getVorname());
		p.setNachname(this.getNachname());
		p.setSuffix(this.getSuffix());
		p.setTitel(this.getTitel());
		if (!Helper.isNullOrEmpty(getGeburtstag())) {
			try {
				SimpleDateFormat sdfToDate = new SimpleDateFormat("dd/MM/yyyy");
				p.setGeburtstag(sdfToDate.parse(this.getGeburtstag()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			p.setGeburtstag(null);
		}

		if (Helper.isNullOrEmpty(this.getWohnadress1())) {
			p.setWohnadresse(null);
		} else {
			p.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE, this
					.getWohnadress1(), this.getWohnadress2(), Integer
					.parseInt(this.getWohnplz()), this.getWohnort(), this
					.getWohnadrid()));
		}

		if (Helper.isNullOrEmpty(this.getRechnungadress1())) {
			p.setRechnungsadresse(null);
		} else {
			p.setRechnungsadresse(new Adresse(AdresseEnums.RECHNUNGSADRESSE,
					this.getRechnungadress1(), this.getRechnungadress2(),
					Integer.parseInt(this.getRechnungplz()), this
							.getRechnungort(), this.getRechadrid()));
		}

		if (Helper.isNullOrEmpty(this.getLieferadress1())) {
			p.setLieferadresse(null);
		} else {
			p.setLieferadresse(new Adresse(AdresseEnums.LIEFERADRESSE, this
					.getLieferadress1(), this.getLieferadress2(), Integer
					.parseInt(this.getLieferplz()), this.getLieferort(), this
					.getLieferadrid()));
		}

		return p;
	}

	public Firma getFirmaToSave() {
		Firma f = new Firma();
		f.setId(this.getId());

		f.setName(this.getFirmenname());
		f.setUid(this.getUID());

		if (Helper.isNullOrEmpty(this.getWohnadress1())) {
			f.setWohnadresse(null);
		} else {
			f.setWohnadresse(new Adresse(AdresseEnums.WOHNADRESSE, this
					.getWohnadress1(), this.getWohnadress2(), Integer
					.parseInt(this.getWohnplz()), this.getWohnort(), this
					.getWohnadrid()));
		}

		if (Helper.isNullOrEmpty(this.getRechnungadress1())) {
			f.setRechnungsadresse(null);
		} else {
			f.setRechnungsadresse(new Adresse(AdresseEnums.RECHNUNGSADRESSE,
					this.getRechnungadress1(), this.getRechnungadress2(),
					Integer.parseInt(this.getRechnungplz()), this
							.getRechnungort(), this.getRechadrid()));
		}

		if (Helper.isNullOrEmpty(this.getLieferadress1())) {
			f.setLieferadresse(null);
		} else {
			f.setLieferadresse(new Adresse(AdresseEnums.LIEFERADRESSE, this
					.getLieferadress1(), this.getLieferadress2(), Integer
					.parseInt(this.getLieferplz()), this.getLieferort(), this
					.getLieferadrid()));
		}

		return f;
	}

	public boolean validate() {
		boolean valid = true;
		if (this.isFirma()) {
			if (Helper.isNullOrEmpty(this.getFirmenname())) {
				valid = false;
				this.setFirmenname("Bitte geben Sie einen Firmennamen ein");
			}
			if (Helper.isNullOrEmpty(this.getUID())) {
				valid = false;
				this.setUID("Bitte geben Sie eine UID ein");
			}

		} else {
			if (Helper.isNullOrEmpty(this.getNachname())) {
				valid = false;
				this.setNachname("Bitte geben Sie einen Nachnamen ein");
			}
			if (Helper.isNullOrEmpty(this.getVorname())) {
				valid = false;
				this.setVorname("Bitte geben Sie einen Vorname ein");
			}
			if (Helper.isNullOrEmpty(this.getSuffix())) {
				valid = false;
				this.setSuffix("Bitte geben Sie einen Suffix ein");
			}
			if (Helper.isNullOrEmpty(this.getTitel())) {
				this.setTitel("");
			}
			if (Helper.isNullOrEmpty(this.getGeburtstag())) {
				this.setGeburtstag("");
			} else {
				try {
					SimpleDateFormat sdfToDate = new SimpleDateFormat(
							"dd/MM/yyyy");
					sdfToDate.parse(this.getGeburtstag());
				} catch (ParseException e) {
					this.setGeburtstag("Bitte Datum im Format dd/MM/yyyy");
					valid = false;
				}
			}
			if (Helper.isNullOrEmpty(this.getCcm().getTextField()))
				this.firmaid = 0;

		}

		if (Helper.isNullOrEmpty(this.getWohnadress1())) {
			valid = false;
			this.setWohnadress1("Bitte geben Sie eine wohnadresse ein");
		}
		if (Helper.isNullOrEmpty(this.getWohnadress2())) {
			this.setWohnadress2("");
		}
		if (Helper.isNullOrEmpty(this.getWohnort())) {
			valid = false;
			this.setWohnort("Bitte geben Sie den Ort ein");
		}
		if (Helper.isNullOrEmpty(this.getWohnplz())) {
			valid = false;
			this.setWohnplz("Bitte geben Sie die Wohnplz an");

		} else {
			try {
				Integer.parseInt(this.getWohnplz());
			} catch (Exception ex) {
				valid = false;
				this.setWohnplz("Bitte geben Sie eine gueltige PLZ ein");
			}
		}
		if (Helper.isNullOrEmpty(this.getRechnungadress1())) {
			this.setRechnungadress1("");
		}
		if (Helper.isNullOrEmpty(this.getRechnungadress2())) {
			this.setRechnungadress2("");
		}
		if (Helper.isNullOrEmpty(this.getRechnungort())) {
			this.setRechnungort("");
		}
		if (Helper.isNullOrEmpty(this.getRechnungplz())) {
			this.setRechnungplz("");

		} else {
			try {
				Integer.parseInt(this.getRechnungplz());
			} catch (Exception ex) {
				valid = false;
				this.setRechnungplz("Bitte geben Sie eine gueltige PLZ ein");
			}
		}
		if (Helper.isNullOrEmpty(this.getLieferadress1())) {
			this.setLieferadress1("");
		}
		if (Helper.isNullOrEmpty(this.getLieferadress2())) {
			this.setLieferadress2("");
		}
		if (Helper.isNullOrEmpty(this.getLieferort())) {
			this.setLieferort("");
		}
		if (Helper.isNullOrEmpty(this.getLieferplz())) {
			this.setLieferplz("");

		} else {
			try {
				Integer.parseInt(this.getLieferplz());
			} catch (Exception ex) {
				valid = false;
				this.setLieferplz("Bitte geben Sie eine gueltige PLZ ein");
			}
		}

		return valid;
	}

}
