package at.fh.technikum.wien.koller.krammer.presentationmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

public class CustomControlModel {
	private StringProperty labeltext = new SimpleStringProperty();
	private StringProperty searchtext = new SimpleStringProperty();
	private ObjectProperty<Image> success = new SimpleObjectProperty<>();

	private BooleanBinding isOk = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return ok;
		}
	};

	private Boolean ok = false;

	public CustomControlModel() {
		ChangeListener<Boolean> canEditListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(ok)
					success.set(new Image("/images/ok.png"));
				else
					success.set(new Image("/images/attention.png"));
				
			}
		
		};
		
		isOk.addListener(canEditListener);
	}

	public final BooleanBinding isOkBinding() {
		return isOk;
	}

	public boolean changeOkPicture() {
		return isOk.get();
	}
	
	public Image getSuccessImage() {
		return success.get();
	}
	
	public void setSuccessImage(Image i) {
		success.set(i);
	}
	
	public ObjectProperty<Image> successImageProperty() {
		return success;
	}

	public String getLabelText() {
		return labeltext.get();
	}

	public void setLabelText(String value) {
		labeltext.set(value);
	}

	public StringProperty labelTextProperty() {
		return labeltext;
	}

	public String getTextField() {
		return textFieldProperty().get();
	}

	public void setTextField(String value) {
		textFieldProperty().set(value);
	}

	public StringProperty textFieldProperty() {
		return searchtext;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
		success.set(new Image("/images/ok.png"));
	}
	
	public void setModel(CustomControlModel cm) {
		this.setLabelText(cm.getLabelText());
		this.setOk(cm.isOk());
		this.setSuccessImage(cm.getSuccessImage());
		this.setTextField(cm.getTextField());
	}

}