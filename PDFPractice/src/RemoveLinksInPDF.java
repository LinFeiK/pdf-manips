import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.layout.Document;

public class RemoveLinksInPDF {

	public static void main(String[] args) throws Exception {
		String currDir = System.getProperty("user.dir");
		File folder = new File(currDir.toString());
		File[] listOfFiles = folder.listFiles();
		PdfReader reader;
		PdfWriter writer;
		PdfDocument pdfDoc;
		Document doc;
		PdfPage pdfPage = null;
		String pdfName = "";

		// For each PDF document in the current directory,
		// if there are URI type annotations (i.e., if anything in the document
		// can be clicked on to lead to a website), delete that annotation
		for (int i = 0; i < listOfFiles.length; i++) {
			pdfName = listOfFiles[i].getName();
			if (pdfName.endsWith(".pdf")) {
				reader = new PdfReader(new FileInputStream(currDir + "\\" + pdfName));
				// Use this to have the file without links in the 'modified' directory
				writer = new PdfWriter(new FileOutputStream(currDir + "\\modified\\" + pdfName));
				// Use this to replace the existing file instead of creating a new one
//				writer = new PdfWriter(new FileOutputStream(currDir + "\\" + pdfName));
				pdfDoc = new PdfDocument(reader, writer);
				doc = new Document(pdfDoc);
				
				System.out.print(pdfName + "\t");
				System.out.println(pdfDoc.getNumberOfPages());
				
				for (int j = 1; j <= pdfDoc.getNumberOfPages(); j++) {
					// Get the current page
					pdfPage = pdfDoc.getPage(j);
					
					// Get the annotations for the page
					List<PdfAnnotation> annotations = pdfPage.getAnnotations();
					
					if (annotations == null || annotations.size() == 0) {
						System.out.println("No annotations on page " + j);
					} else {
						for (PdfAnnotation a : annotations) {
							// Make sure the annotation has a link
							if (!a.getSubtype().equals(PdfName.Link)) continue;
							
							// Make sure the annotation has an action
							if (a.getAction() != null) {
								// Get the action
								PdfDictionary annotationAction = a.getAction();
								System.out.println(annotationAction.get(PdfName.S));
								
								// If the action is of type URI, remove this annotation
								if (annotationAction.get(PdfName.S).equals(PdfName.URI)) {
									// Print the link that the annotation leads to
									System.out.println(annotationAction.getAsString(PdfName.URI));
									// Remove the annotation from the page
									pdfPage.removeAnnotation(a);
								}
							}
						}
					}
				}
				doc.close();
			}
		}

	}

}
