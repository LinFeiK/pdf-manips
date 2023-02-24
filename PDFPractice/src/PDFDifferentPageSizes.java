import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;

// Create a PDF with uneven page sizes in the current directory
public class PDFDifferentPageSizes {

	public static void main(String[] args) throws FileNotFoundException {
		String destination = System.getProperty("user.dir") + "\\uneven-pages.pdf";
		
		// Create a PDF document
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(destination)));
		
		// Create Rectangle objects to represent the two page sizes
		Rectangle pageSize1 = new Rectangle(300, 350);
		Rectangle pageSize2 = new Rectangle(500, 700);
		
		// Create the Document with one page
		Document document = new Document(pdfDoc, new PageSize(pageSize1));
		
		// Set margins (params topMargin, rightMargin, bottomMargin, leftMargin)
		document.setMargins(10, 10, 10, 10);
		
		// Add text in a paragraph
		Paragraph paragraph = new Paragraph("Page 1 text.");
		document.add(paragraph);
		
		// Set margins for the subsequent page
		document.setMargins(150, 150, 150, 200);
		
		// Add the second page by adding an AreaBreak
		document.add(new AreaBreak(new PageSize(pageSize2)));
		
		// Add another paragraph
		document.add(new Paragraph("Page 2 text."));
		
		// Close the document
		document.close();

	}

}
