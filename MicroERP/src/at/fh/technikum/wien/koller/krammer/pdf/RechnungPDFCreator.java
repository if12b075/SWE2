package at.fh.technikum.wien.koller.krammer.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import at.fh.technikum.wien.koller.krammer.commons.MERPConstants;
import at.fh.technikum.wien.koller.krammer.models.Firma;
import at.fh.technikum.wien.koller.krammer.models.Kontakt;
import at.fh.technikum.wien.koller.krammer.models.Person;
import at.fh.technikum.wien.koller.krammer.models.Rechnung;
import at.fh.technikum.wien.koller.krammer.proxy.MERPProxyFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RechnungPDFCreator {
	public static boolean createPDF(Rechnung r, Kontakt k) {

		try {

			String outputFile = MERPConstants.PDF_SAVE_LOCATION + "Rechnung_"
					+ r.getRechnungsnummer() + ".pdf";
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(outputFile));
			document.open();
			
			Font f1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
			Font f2 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
			Font f3 = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
			Paragraph p = new Paragraph();
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f1);

			
			p.add(new Phrase("\n"+MERPConstants.FIRMA_NAME + " / " +MERPConstants.FIRMA_ADRESSE + "\n"));
			
			p.setFont(f2);
			if(k.isFirma()) {
				Firma f = MERPProxyFactory.getFirmaById(k.getId());
				
				p.add(new Phrase("Firma\n" + f.getName() + "\n" + f.getUid() + "\n"));
				
				if(f.getRechnungsadresse()!=null) {
					p.add(new Phrase(f.getRechnungsadresse().getAdrrow1() + "\n"));
					if(f.getRechnungsadresse().getAdrrow2()!=null)
						p.add(new Phrase(f.getRechnungsadresse().getAdrrow2() + "\n"));
					p.add(new Phrase(f.getRechnungsadresse().getPlz() + " " + f.getRechnungsadresse().getOrt()+ "\n"));
					
				} else if(f.getLieferadresse() != null) {
					p.add(new Phrase(f.getLieferadresse().getAdrrow1() + "\n"));
					if(f.getLieferadresse().getAdrrow2()!=null)
						p.add(new Phrase(f.getLieferadresse().getAdrrow2() + "\n"));
					p.add(new Phrase(f.getLieferadresse().getPlz() + " " + f.getLieferadresse().getOrt()+ "\n"));
				} else {
					p.add(new Phrase(f.getWohnadresse().getAdrrow1() + "\n"));
					if(f.getWohnadresse().getAdrrow2()!=null)
						p.add(new Phrase(f.getWohnadresse().getAdrrow2() + "\n"));
					p.add(new Phrase(f.getWohnadresse().getPlz() + " " + f.getWohnadresse().getOrt()+ "\n"));
				}
				
			} else {
				Person per = MERPProxyFactory.getPersonById(k.getId());
				
				p.add(new Phrase(per.getTitel() + "\n"));
				if(per.getSuffix()!=null)
					p.add(new Phrase(per.getSuffix() + " " + per.getVorname() + " " + per.getNachname() + "\n"));
				else
					p.add(new Phrase(per.getVorname() + " " + per.getNachname() + "\n"));
				
				if(per.getRechnungsadresse()!=null) {
					p.add(new Phrase(per.getRechnungsadresse().getAdrrow1() + "\n"));
					if(per.getRechnungsadresse().getAdrrow2()!=null)
						p.add(new Phrase(per.getRechnungsadresse().getAdrrow2() + "\n"));
					p.add(new Phrase(per.getRechnungsadresse().getPlz() + " " + per.getRechnungsadresse().getOrt()+ "\n"));
					
				} else if(per.getLieferadresse() != null) {
					p.add(new Phrase(per.getLieferadresse().getAdrrow1() + "\n"));
					if(per.getLieferadresse().getAdrrow2()!=null)
						p.add(new Phrase(per.getLieferadresse().getAdrrow2() + "\n"));
					p.add(new Phrase(per.getLieferadresse().getPlz() + " " + per.getLieferadresse().getOrt()+ "\n"));
				} else {
					p.add(new Phrase(per.getWohnadresse().getAdrrow1() + "\n"));
					if(per.getWohnadresse().getAdrrow2()!=null)
						p.add(new Phrase(per.getWohnadresse().getAdrrow2() + "\n"));
					p.add(new Phrase(per.getWohnadresse().getPlz() + " " + per.getWohnadresse().getOrt()+ "\n"));
				}
			}
			document.add(p);

			p.clear();
			p.setAlignment(Element.ALIGN_RIGHT);
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f2);
			
			p.add(new Phrase("Rechnungsdatum:   "+ formatter.format(r.getDatum()) + "\n"));
			p.add(new Phrase("Fälligkeitsdatum:   "+ formatter.format(r.getFaelligkeitsdatum()) + "\n"));
			p.add(new Phrase("Ust-IdNR.:  "+ MERPConstants.UST_IDNR + "\n"));
			
			document.add(p);
			p.clear();
			p.setAlignment(Element.ALIGN_LEFT);
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f1);
			p.add(new Phrase("Rechnung Nr.:   "+ r.getRechnungsnummer() + "\n"));
			document.add(p);
			
			p.clear();
			
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f2);
			p.add(new Phrase(r.getNachricht() + "\n\n"));
			document.add(p);
			
			p.clear();
			
			PdfPTable table = new PdfPTable(24);
			table.setWidthPercentage(100);
			float ustsum = 0;
			float nettosum = 0;
			float bruttosum = 0;
			table.setHeaderRows(0);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell;
			
			cell = new PdfPCell(new Phrase("Nr."));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Bezeichnung"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(8);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Menge"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Stueckpreis €"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(3);
			table.addCell(cell);
			
			
			
			cell = new PdfPCell(new Phrase("Netto €"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Ust %"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Brutto €"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(3);
			table.addCell(cell);
			
			
			for(int i=0;i<r.getRechnungszeilen().size();i++) {
				cell = new PdfPCell(new Phrase(String.valueOf(i+1)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(r.getRechnungszeilen().get(i).getBezeichnung()));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(8);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(r.getRechnungszeilen().get(i).getMenge())));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(r.getRechnungszeilen().get(i).getStueckpreis())));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				table.addCell(cell);
				
				float netto = r.getRechnungszeilen().get(i).getStueckpreis()*r.getRechnungszeilen().get(i).getMenge();
				nettosum += netto;
				cell = new PdfPCell(new Phrase(String.valueOf(netto)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Phrase(String.valueOf(r.getRechnungszeilen().get(i).getUst())));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				table.addCell(cell);
				float ust = netto * r.getRechnungszeilen().get(i).getUst() / 100;
				ustsum += ust;
				float brutto = netto + ust;
				bruttosum += brutto;
				cell = new PdfPCell(new Phrase(String.valueOf(brutto)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				table.addCell(cell);
				
			}
			
			document.add(table);
			p.setAlignment(Element.ALIGN_RIGHT);
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f2);
			p.add(new Phrase("Summe netto:   " + nettosum + " €\n"));
			p.add(new Phrase("Summe ust:      " + ustsum + " €\n"));
			p.add(new Phrase("Summe brutto:   " + bruttosum + " €\n\n\n"));
			document.add(p);
			p.clear();
			
			p.setAlignment(Element.ALIGN_LEFT);
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f2);
			p.add(new Phrase(r.getKommentar() + "\n\n"));
			document.add(p);
			p.clear();
			
			p.setAlignment(Element.ALIGN_LEFT);
			p.setSpacingBefore(10);
			p.setSpacingAfter(10);
			p.setFont(f3);
			p.add(new Phrase("Telefon:  " + MERPConstants.FIRMA_TELEFON + "                 " + MERPConstants.FIRMA_NAME + "\n"));
			p.add(new Phrase("Fax:      " + MERPConstants.FIRMA_FAX + "            " + MERPConstants.FIRMA_BANK + "\n"));
			p.add(new Phrase("Email:    " + MERPConstants.FIRMA_MAIL + "        " + "BLZ: " + MERPConstants.FIRMA_BLZ + "\n"));
			p.add(new Phrase("Web:      " + MERPConstants.FIRMA_WEB + "         " + "KTO: " +MERPConstants.FIRMA_KTO + "\n"));
			document.add(p);

			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
