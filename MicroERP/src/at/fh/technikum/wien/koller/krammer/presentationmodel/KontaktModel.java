package at.fh.technikum.wien.koller.krammer.presentationmodel;

import at.fh.technikum.wien.koller.krammer.commons.Helper;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class KontaktModel {
	private StringProperty vorname = new SimpleStringProperty();
    private StringProperty nachname = new SimpleStringProperty();
    private StringProperty firmenname = new SimpleStringProperty();
    private StringProperty UID = new SimpleStringProperty();
    

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
}
