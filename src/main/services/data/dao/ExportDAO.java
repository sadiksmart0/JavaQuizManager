package main.services.data.dao;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class ExportDAO {


    /**  Exporting Questions to  PDF ******************************************************/
    public List pdfExport(List qlist){


        final String path = "./resources/Exported/Exported_Question.pdf";
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDoc);
            document.add(qlist);
            document.close();
            System.out.println("\nPdf exported Successfully");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


/**  Exporting Quiz as text  **********************************************************************/


    public  String textExport(ArrayList<String> qlist){

        try {
            FileWriter fileWriter = new FileWriter("./resources/Exported/Exported_QuestionIntext.txt");
            int k = 0;
            while (k < qlist.size()){
                fileWriter.write(k+1+" "+qlist.get(k)+"\n");
                k++;
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
