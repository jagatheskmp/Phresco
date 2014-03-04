package com.photon.phresco.uiconstants;

import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.photon.phresco.selenium.util.ScreenException;


public class ReadXMLFile {

	private Element eElement;
	private Log log = LogFactory.getLog(getClass());
	private String phrscUi = "./src/main/resources/phrescoUiConstants.xml";
	private String phrscEnv = "./src/main/resources/phresco-env-config.xml";
	private String phrscData="./src/main/resources/PhrescoFrameworkData.xml";
	private String UserInfoConst="./src/main/resources/UserInfoConstants.xml";
	private String importApplication="./src/main/resources/ImportApplicationConstants.xml";
	private String ArchetypeCreate="./src/main/resources/ArchetypeCreation.xml";
	private String runAgainstSource="./src/main/resources/RunAgainstSourceConstants.xml";
	private String cloneConfiguration="./src/main/resources/CloneConfigurationConstants.xml";
	private String loginScenario="./src/main/resources/LoginScenariosConstants.xml";
	
	public ReadXMLFile() throws ScreenException {
		log.info("@ReadXMLFile Constructor::loading *****PhrescoUIConstants******");
		}
	
	public void loadPhrescoConstansts(String properties) throws ScreenException {

		try {
			File fXmlFile = new File(properties);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName("environment");
/*	System.out.println("-----------------------");*/

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadUserInfoConstants() throws ScreenException {
		loadPhrescoConstansts(UserInfoConst);
	}
	public void loadPhrescoEnvConstansts()throws ScreenException{
		loadPhrescoConstansts(phrscEnv);
	}
	
	public void loadPhrescoFrameworkUiConstants()throws ScreenException{
		loadPhrescoConstansts(phrscUi);
	}
	
	public void loadPhrescoDataConstants()throws ScreenException{
		loadPhrescoConstansts(phrscData);
	}
	
	public void loadImportApplication() throws ScreenException {
		loadPhrescoConstansts(importApplication);
	}
	
	public void loadArchetyeCreation() throws ScreenException {
		loadPhrescoConstansts(ArchetypeCreate);
	}
	
	public void loadCloneConfiguration() throws ScreenException {
		loadPhrescoConstansts(cloneConfiguration);
	}
	
	public void loadLoginScenarios() throws ScreenException {
		loadPhrescoConstansts(loginScenario);
	}
	
	public void loadRunAgainstSource() throws ScreenException {
		loadPhrescoConstansts(runAgainstSource);
	}
	
	public String getValue(String elementName) {

		NodeList nlList = eElement.getElementsByTagName(elementName).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);		
		if(nValue==null){
			return null; 
		}

		return nValue.getNodeValue();
	}
		

}


