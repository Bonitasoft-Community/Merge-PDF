import org.apache.pdfbox.exceptions.COSVisitorException;
import org.bonitasoft.custom.MergePdf;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.IOException;
import java.util.List;

/**
 * @Created by Fabrice Rosito on 01/09/2014.
 * @author Fabrice Rosito
 * @mail : fabrice.rosito@bonitasoft.com
 * @warning : Developed for BonitaBPM 6.3.3
 * @warning : Careful treatment may take a little time
 */
public class TestBonitaConnectorMergePdf {

    public static void main(String[] args){
        testUnite();
    }

    public static void testUnite() {
        MergePdf myMergePdf = new MergePdf();

        OutputStream myReturn = null;
        try {
            InputStream pdf1 = new FileInputStream("C:/DATAS/mypdf1.pdf");

            InputStream pdf2 = new FileInputStream("C:/DATAS/mypdf2.pdf");

            List<InputStream> myList = new ArrayList<InputStream>();
            myList.add(pdf1);
            myList.add(pdf2);

            myReturn = myMergePdf.actionMerge(myList);

            FileOutputStream fop = null;
            File file;
            String content = "This is the text content";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String now = format.format(Calendar.getInstance().getTime());
            String strOut = "combined_"+now+".pdf";

            file = new File("c:/DATAS/"+strOut);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = ((ByteArrayOutputStream) myReturn).toByteArray();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.printf(strOut);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
    }
}
