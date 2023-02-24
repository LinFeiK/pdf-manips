import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

// Resize pages in a PDF. 
// Note 1: The code in PDFDifferentPageSizes.java should be run first
// 	to create a PDF with pages of different sizes for this code.
// Note 2: The resulting PDF will be created in the 'modified' folder.
public class ResizePages {

	public static void main(String[] args) throws Exception {
		String source = System.getProperty("user.dir") + "\\uneven-pages.pdf";
		String destination = System.getProperty("user.dir") + "\\modified\\uneven-pages-resized.pdf";
		
		// Create the PdfDocument
		PdfDocument pdfDoc = new PdfDocument(new PdfReader(new FileInputStream(source)),
											 new PdfWriter(new FileOutputStream(destination)));
		
		// Get the dimensions of page 1
		PdfPage page1 = pdfDoc.getPage(1);
		// Visible region when displayed
		Rectangle page1CropBox = page1.getCropBox();
		// Actual dimensions (in this case, the same as cropBox)
		Rectangle page1MediaBox = page1.getMediaBox();
		
		System.out.println("Page 1 crop box: " + page1CropBox);
		System.out.println("Page 1 media box: " + page1MediaBox);
		
		// Get the dimensions of page 2
		PdfPage page2 = pdfDoc.getPage(2);
		Rectangle page2CropBox = page2.getCropBox();
		Rectangle page2MediaBox = page2.getMediaBox();
		System.out.println("Page 2 crop box: " + page2CropBox);
		System.out.println("Page 2 media box: " + page2MediaBox);
		
		// Calculate the new lower left y-coordinate so the text on Page 1 remains
		// in the appropriate location (top-left)
		float lowerLeftY = -1 * (page2MediaBox.getHeight() - page1MediaBox.getHeight());
		// Resize page 1 to be the same as page 2
		// (params lower left x, lower left y, width, height)
		page1.setMediaBox(new Rectangle(0, lowerLeftY, page2MediaBox.getWidth(), page2MediaBox.getHeight()));
		
		// Doing this instead of calculating lowerLeftY would resize the page
		// with the extra space above the text, since the coordinates of the media box 
		// are (0, 0), i.e., the bottom left of the page
//		page1.setMediaBox(page2MediaBox);
		
		// Only changing Page 1's cropBox would have no visible effect in this case since the
		// page is already smaller than Page 2 (can't display an area that doesn't exist)
//		page1.setCropBox(page2MediaBox);
		
		System.out.println("New Page 1 crop box: " + page1.getCropBox());
		System.out.println("New Page 1 media box: " + page1.getMediaBox());
		
		pdfDoc.close();
		
	}

}
