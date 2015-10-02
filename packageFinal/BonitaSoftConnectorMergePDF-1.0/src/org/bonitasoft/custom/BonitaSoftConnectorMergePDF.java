/**
 * @author Fabrice Rosito
 * @mail : fabrice.rosito@bonitasoft.com
 * @date : 04-09-03
 * @warning : Developed for BonitaBPM 6.3.3
 * @warning : Careful treatment may take a little time
 */
package org.bonitasoft.custom;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.bonitasoft.engine.connector.ConnectorException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.document.Document;
import org.bonitasoft.engine.bpm.document.DocumentNotFoundException;
import org.bonitasoft.engine.bpm.document.DocumentValue;

/**
 *The connector execution will follow the steps
 * 1 - setInputParameters() --> the connector receives input parameters values
 * 2 - validateInputParameters() --> the connector can validate input parameters values
 * 3 - connect() --> the connector can establish a connection to a remote server (if necessary)
 * 4 - executeBusinessLogic() --> execute the connector
 * 5 - getOutputParameters() --> output are retrieved from connector
 * 6 - disconnect() --> the connector can close connection to remote server (if any)
 */
public class BonitaSoftConnectorMergePDF extends AbstractBonitaSoftConnectorMergePDF {

	@Override
	protected void executeBusinessLogic() throws ConnectorException{
		//var
		Boolean trace = false;
		
		//Init logger
		Logger logger = Logger.getLogger("org.bonitasoft");
		logger.info(this.getClass().getName() + " - execute method executeBusinessLogic.");
		
		//Init processAPI
    	ProcessAPI myProcessAPI = apiAccessor.getProcessAPI();
		
		//Get access to the connector input parameters
		String pdfName1 = getPdfName1();
		String pdfName2 = getPdfName2();
		Long processInstanceId = getProcessInstanceId();
		
		if(trace){
			logger.info("pdfName1 : " + pdfName1);
			logger.info("pdfName2 : " + pdfName2);
			logger.info("processInstanceId : " + processInstanceId);
		}
		
		//Init value return
        DocumentValue documentValue = new DocumentValue("empty");
        
        //Business Logic
        try {
        	Document myDocument = null;
        	byte[] docContent = null;
        	
        	myDocument = myProcessAPI.getLastDocument(processInstanceId, pdfName1);
        	docContent = myProcessAPI.getDocumentContent(myDocument.getContentStorageId());
        	InputStream pdf1 = new ByteArrayInputStream(docContent);
        	
        	myDocument = myProcessAPI.getLastDocument(processInstanceId, pdfName2);
        	docContent = myProcessAPI.getDocumentContent(myDocument.getContentStorageId());
        	InputStream pdf2 = new ByteArrayInputStream(docContent);
        	
    		List<InputStream> myList = new ArrayList<InputStream>();
            myList.add(pdf1);
            myList.add(pdf2);
    		
            MergePdf myMergePdf = new MergePdf();
        	
        	OutputStream myReturn = myMergePdf.actionMerge(myList);
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String now = format.format(Calendar.getInstance().getTime());
            String strOut = "combined_"+now+".pdf";
            
            byte[] bytes = ((ByteArrayOutputStream) myReturn).toByteArray();
            
            documentValue = new DocumentValue(bytes, "application/pdf", strOut);
            logger.info(this.getClass().getName() + " - Merged PDF create : " + strOut);
		} catch (COSVisitorException e) {
			logger.severe(this.getClass().getName() + " - Error COSVisitorException : " + e.getMessage());
		} catch (IOException e) {
			logger.severe(this.getClass().getName() + " - Error IOException : " + e.getMessage());
		} catch (DocumentNotFoundException e) {
			logger.severe(this.getClass().getName() + " - Error DocumentNotFoundException : " + e.getMessage());
		}
        
        //Return
		setOutputPDFStream(documentValue);
	 }

	@Override
	public void connect() throws ConnectorException{
		//[Optional] Open a connection to remote server
	
	}

	@Override
	public void disconnect() throws ConnectorException{
		//[Optional] Close connection to remote server
	
	}

}
