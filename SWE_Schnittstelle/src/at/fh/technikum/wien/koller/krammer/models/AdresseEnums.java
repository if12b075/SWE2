package at.fh.technikum.wien.koller.krammer.models;

public enum AdresseEnums {
	LIEFERADRESSE {
		public String toString() {
			return "Lieferadresse";
		}
	},

	RECHNUNGSADRESSE {
		public String toString() {
			return "Rechnungsadresse";
		}
	},

	WOHNADRESSE {
		public String toString() {
			return "Wohnadresse";
		}
	}
}
