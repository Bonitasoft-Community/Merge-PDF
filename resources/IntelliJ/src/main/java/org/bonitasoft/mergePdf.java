package org.bonitasoft;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

/**
 * @Created by Fabrice Rosito on 01/09/2014.
 * @author Fabrice Rosito
 * @mail : fabrice.rosito@bonitasoft.com
 * @warning : Developed for BonitaBPM 6.3.3
 * @warning : Careful treatment may take a little time (ex : 3s for two pdf of 3mo)
 */
public class mergePdf {
    public void mergePdf()  {
    }

    public OutputStream actionMerge(List<InputStream> myList) throws IOException, COSVisitorException {
        PDFMergerUtility merger = new PDFMergerUtility();

        for(InputStream curInputStream:myList){
            merger.addSource(curInputStream);
        }

        OutputStream myOutputStream = new ByteArrayOutputStream();

        merger.setDestinationStream(myOutputStream);
        merger.mergeDocuments();

        return myOutputStream;
    }
}
