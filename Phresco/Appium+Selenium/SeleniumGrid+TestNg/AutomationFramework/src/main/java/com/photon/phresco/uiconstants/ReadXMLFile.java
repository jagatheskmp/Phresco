
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
	private final String phrsc = "./src/main/resources/phresco-env-config.xml";
	private final String constants = "./src/main/resources/UIConstants.xml";
	private final String UsrInfConst = "./src/main/resources/UserInfo.xml";
	private final String ENVIRONMENT = "environment";
	private final String UICONSTANTDATA = "uiConstants";
	private final String USERINFO ="userInfo";

	public ReadXMLFile() throws ScreenException {
		log.info("LOADING DATA FROM XML FILE");
		loadPhrescoConstansts(phrsc,ENVIRONMENT);
	}

	public void loadPhrescoConstansts(String properties,String rootTag) throws ScreenException {

		try {
			File fXmlFile = new File(properties);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName(rootTag);
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

	public void loadUIConstants() throws ScreenException {
		loadPhrescoConstansts(constants,UICONSTANTDATA);
	}

	public void loadUserInfoConstants() throws ScreenException {
		loadPhrescoConstansts(UsrInfConst,USERINFO);

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