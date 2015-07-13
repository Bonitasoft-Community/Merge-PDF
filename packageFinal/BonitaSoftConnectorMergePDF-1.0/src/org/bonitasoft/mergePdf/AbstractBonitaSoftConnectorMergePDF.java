package org.bonitasoft.mergePdf;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;
import org.bonitasoft.engine.bpm.document.DocumentValue;

public abstract class AbstractBonitaSoftConnectorMergePDF extends AbstractConnector {

	protected final static String PDFNAME1_INPUT_PARAMETER = "pdfName1";
	protected final static String PDFNAME2_INPUT_PARAMETER = "pdfName2";
	protected final static Long PROCESSINSTANCEID_INPUT_PARAMETER = "processInstanceId";
	protected final DocumentValue OUTPUTPDFSTREAM_OUTPUT_PARAMETER = "outputPDFStream";

	protected final String getPdfName1() {
		return (String) getInputParameter(PDFNAME1_INPUT_PARAMETER);
	}

	protected final String getPdfName2() {
		return (String) getInputParameter(PDFNAME2_INPUT_PARAMETER);
	}

	protected final Long getProcessInstanceId() { return (Long) getInputParameter(PROCESSINSTANCEID_INPUT_PARAMETER); }

	protected final void setOutputPDFStream(DocumentValue outputPDFStream) { setOutputParameter(OUTPUTPDFSTREAM_OUTPUT_PARAMETER, outputPDFStream); }

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			getPdfName1();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("pdfName1 type is invalid");
		}
		try {
			getPdfName2();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("pdfName2 type is invalid");
		}
		try {
			getProcessInstanceId();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("processInstanceId type is invalid");
		}
	}
}
