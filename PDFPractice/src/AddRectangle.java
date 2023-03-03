import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class AddRectangle {

	public static void main(String[] args) throws Exception {
		String path = System.getProperty("user.dir");
		String destination = path + "//Added Rectangle.pdf";
		
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(destination));
		
		PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
		
		canvas.setFillColor(Color.BLUE);
		
		// Add a blue rectangle with a black border
		// bottom-left x, bottom-left y, width, height
		canvas.rectangle(200, 250, 100, 300);
		canvas.fillStroke();
		
		// Add a green rectangle with no border
		canvas.setFillColor(Color.GREEN);
		canvas.rectangle(150, 600, 100, 50);
		canvas.fill();
		
		pdfDoc.close();
		
	}

}
