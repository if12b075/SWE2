package at.fh.technikum.wien.koller.krammer.commons;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MERPConstants {
	public static final String HTTP_PLUGIN_URL = "http://localhost:8088/micro/";

	public static ObservableList<String> days31 = FXCollections
			.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
					"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
					"30", "31");
	public static ObservableList<String> days30 = FXCollections
			.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
					"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
					"30");
	public static ObservableList<String> days29 = FXCollections
			.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
					"20", "21", "22", "23", "24", "25", "26", "27", "28", "29");
	public static ObservableList<String> days28 = FXCollections
			.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
					"20", "21", "22", "23", "24", "25", "26", "27", "28");
	public static ObservableList<String> month = FXCollections
			.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12");
	public static ObservableList<String> years;

	public static void loadYears() {

		int actYear = Calendar.getInstance().get(Calendar.YEAR);

		for (Integer i = 1900; i <= actYear; i++) {
			years.add(i.toString());
		}
	}

	public static boolean isSchaltjahr(int year) {
		return new GregorianCalendar().isLeapYear(year);
	}

	public static ObservableList<String> getDaysForMonth(int month, int year) {
		ObservableList<String> erg;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			erg = days31;
			break;
		case 2:
			if (isSchaltjahr(year))
				erg = days29;
			else
				erg = days28;
			break;
		default:
			erg = days30;
			break;
		}
		return erg;
	}

}
