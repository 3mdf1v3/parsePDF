package it.parse.pdf;

import java.awt.Desktop;
import java.io.File;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ParsePDF {

	public static void main(String[] args) throws Exception {
		
		PdfDocument pdfDocument = null;
		Document document = null;
		String SOURCEFILE = null;
		String DESTINATIONFILE = null;
		
		if (args != null && args.length == 1 && args[0] != null) {
			try {
				String FONT = "arimon__.ttf";
				SOURCEFILE = args[0];
				DESTINATIONFILE = args[0].replaceFirst(".pdf", "-vertical.pdf");
				
				PdfReader reader = new PdfReader(SOURCEFILE);
				PdfFont f1 = PdfFontFactory.createFont(FONT, "Cp1250", true);
			
				pdfDocument = new PdfDocument(reader);        	
				String text = PdfTextExtractor.getTextFromPage(pdfDocument.getFirstPage(), new LocationTextExtractionStrategy());
				pdfDocument.close();
				
				PdfWriter pdfWriter = new PdfWriter(DESTINATIONFILE);
				pdfDocument = new PdfDocument(pdfWriter);
				PageSize pagesize = PageSize.A4;
				
				document = new Document(pdfDocument, pagesize);
				
				// Stampa laterale sx
				document.setMargins(10, 0, 0, 20);
				
				// Imposta stampa centrata
				//document.setLeftMargin(220);
				
				document.setTopMargin(50);		
				
				Paragraph p = new Paragraph();
				p.setFont(f1);
				p.setFontSize(11);
				p.setWidth(450);
				p.setFixedLeading(10f);
				
				// Imposta stampa centrata
				//p.setMarginLeft(10f);
				
				p.setRotationAngle(1.5708);
					
				String [] lines = text.split("\n");
				
				p.add("\u0000" + lines[1] + "\r\n");
				p.add("\u0000" + lines[2] + "\r\n");
				p.add("\u0000" + lines[3] + "\r\n");
				p.add("\u0000" + lines[4] + "\r\n");
				p.add("\u0000" + lines[5] + "\r\n");
				p.add("\u0000" + lines[6] + "\r\n");
				p.add("\u0000" + lines[7] + "\r\n");
				p.add("\u0000" + lines[8] + "\r\n");
				p.add("\u0000" + lines[9] + "\r\n");
				p.add("\u0000" + lines[10] + "\r\n");
				p.add("\u0000" + lines[11] + "\r\n");
				p.add("\u0000" + lines[12] + "\r\n");
				p.add("\u0000" + lines[13] + "\r\n");
				p.add("\u0000" + lines[14] + "\r\n");
				p.add("\u0000" + lines[15] + "\r\n");
				
				document.add(p);
				
			} catch (Exception e) {
				e.printStackTrace(System.out);
			} finally {
				if (pdfDocument != null) {
					pdfDocument.close();
				}
				if (document != null) {
					document.close();
					if (Desktop.isDesktopSupported()) {
						File pdfFile = new File(DESTINATIONFILE);
			        	Desktop.getDesktop().open(pdfFile);
					}
				}
			}		
		} else {
			System.out.println("Not enough parameters");
		}
		
		System.exit(0);
	}	
		

		
	
}
