import java.io.File;
import java.io.FileInputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

public class DisplayNumberOfPages {

	public static void main(String[] args) throws Exception {
		String currDir = System.getProperty("user.dir");
		File folder = new File(currDir.toString());
		File[] listOfFiles = folder.listFiles();
		PdfReader reader;
		PdfDocument pdfDoc;

		// For each PDF document in the current directory,
		// print its name and the number of pages it has
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().endsWith(".pdf")) {
				reader = new PdfReader(new FileInputStream(currDir + "\\" + listOfFiles[i].getName()));
				pdfDoc = new PdfDocument(reader);
				
				System.out.print(listOfFiles[i].getName() + "\t");
				System.out.println(pdfDoc.getNumberOfPages());
				
				pdfDoc.close();
			}
		}

	}

}
