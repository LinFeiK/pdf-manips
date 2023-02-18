import java.io.FileNotFoundException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PDFPractice {

	public static void main(String[] args) throws FileNotFoundException {
		
		StringBuilder destination = new StringBuilder(System.getProperty("user.dir"));
		destination.append("/sample.pdf");
		
		// Create a PdfWriter object accepting the path of where
		// the PDF should be created
		// elements added to the writer will be written to the specified file once
		// it is passed to a PdfDocument object
		PdfWriter writer = new PdfWriter(destination.toString());
		
		// Create a PdfDocument object
		PdfDocument pdfDoc = new PdfDocument(writer);
		
		// Add an empty page to the PDF
		pdfDoc.addNewPage();
		
		// Create a Document object 
		Document document = new Document(pdfDoc);
		
		// Close the document
		document.close();
	}

}
