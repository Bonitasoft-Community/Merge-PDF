/**
 * READ ME FOR BONITASOFT CONNECTOR MERGE PDF
 * DATE : 14-09-03
 * AUTOR : Fabrice ROSITO
 * MAIL : fabrice.rosito@bonitasoft.com
**/

1 - Introduction
The connector is for the formation BPAD, but it also aims to provide a good connector's implementation example.

2 - Description
The goal of this connector is merge two pdf file in one. 

3 - INTERFACE CONTRACT
Inputs : 
pdfName1 / Mandatory / java.lang.String / It is the name of the first BonitaBPM Document in the pool
pdfName2 / Mandatory / java.lang.String / It is the name of the second BonitaBPM Document in the pool
processInstanceId / Mandatory / java.lang.Long / It is the id of the case

Outputs :
outputPDFStream / org.bonitasoft.engine.bpm.document.DocumentValue / It is the result of the operation for set in BonitaBPM Document

4 - Dependencies
bonita-mergePdf-x.x.x.jar : Home made developpement provide technical operation, merge two intputstream and make a outputstream
pdfbox-x.x.x.jar : Apache PDFBox - A Java PDF Library (https://pdfbox.apache.org/)