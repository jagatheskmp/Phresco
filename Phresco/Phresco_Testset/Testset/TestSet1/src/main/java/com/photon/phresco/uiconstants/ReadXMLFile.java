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
	private String phrscData = "./src/main/resources/PhrescoFrameworkData.xml";
	private String UserInfoConst = "./src/main/resources/UserInfoConstants.xml";
	private String php = "./src/main/resources/phpConstants.xml";
	private String drupal6 = "./src/main/resources/Drupal6Constants.xml";
	private String drupal7 = "./src/main/resources/Drupal7Constants.xml";
	private String wordPress = "./src/main/resources/WordPressConstants.xml";	
	private String JavaWebService = "./src/main/resources/JavaWebServiceConstants.xml";
	private String JavaStandAlone = "./src/main/resources/JavaStandaloneConstants.xml";
	private String siteCore = "./src/main/resources/SiteCoreConstants.xml";
	private String AspDotNet = "./src/main/resources/AspDotNetConstants.xml";
	private String SharePoint = "./src/main/resources/SharePointConstants.xml";
	private String NodeJsWebService = "./src/main/resources/NodeJsWebServiceConstants.xml";
	private String jQueryMobWidget = "./src/main/resources/HTML5JQueryMobileWidget.xml";
	private String multiYUIWidget = "./src/main/resources/HTML5MultichannelYUIWidgetConstants.xml";
	private String multiJQueryWidget = "./src/main/resources/HTML5MultichannelJQueryWidgetConstants.xml";
	private String yuiConst = "./src/main/resources/HTML5YUIMobileWidget.xml";
	private String iPhoneNative = "./src/main/resources/IPhoneNativeConstants.xml";
	private String iPhoneHybrid = "./src/main/resources/IPhoneHybridConstants.xml";
	private String androidNative = "./src/main/resources/AndroidNativeConstants.xml";
	private String androidHybrid = "./src/main/resources/AndroidHybridConstants.xml";
	private String androidLibrary = "./src/main/resources/AndroidLibraryConstants.xml";
	private String iPhoneLibrary = "./src/main/resources/IPhoneLibraryConstants.xml";
	private String blackBerryHybrid = "./src/main/resources/BlackBerryHybridConstants.xml";                                                                
	private String windowsMetro = "./src/main/resources/WindowsMetroConstants.xml"; 
	private String windowsPhone = "./src/main/resources/WindowsPhoneConstants.xml";

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

	public void loadPhpConstants()throws ScreenException{
		loadPhrescoConstansts(php);
	}

	public void loadDrupal6Constants()throws ScreenException{
		loadPhrescoConstansts(drupal6);
	}

	public void loadDrupal7Constants()throws ScreenException{
		loadPhrescoConstansts(drupal7);
	}

	public void loadWordPressConstants() throws ScreenException {
		loadPhrescoConstansts(wordPress);
	}

	public void loadJavaWebServiceConstants() throws ScreenException {
		loadPhrescoConstansts(JavaWebService);
	}

	public void loadJavaStandaloneConstants() throws ScreenException {
		loadPhrescoConstansts(JavaStandAlone);
	}

	public void loadSiteCoreConstants() throws ScreenException {
		loadPhrescoConstansts(siteCore);
	}

	public void loadAspDotNetConstants() throws ScreenException {
		loadPhrescoConstansts(AspDotNet);
	}

	public void loadSharePointConstants() throws ScreenException {
		loadPhrescoConstansts(SharePoint);
	}

	public void loadNodeJsWebServiceConstants() throws ScreenException {
		loadPhrescoConstansts(NodeJsWebService);
	}

	public void loadJqueryWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(jQueryMobWidget);
	}

	public void loadMultiChannelYUIWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(multiYUIWidget);
	}

	public void loadMultiChannelJQueryWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(multiJQueryWidget);
	}

	public void loadYUIMobileWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(yuiConst);
	}

	public void loadiPhoneNativeConstants() throws ScreenException {
		loadPhrescoConstansts(iPhoneNative);
	}
	
	public void loadiPhoneHybridConstants() throws ScreenException {
		loadPhrescoConstansts(iPhoneHybrid);
	}
	
	public void loadAndroidNativeConstants() throws ScreenException {
		loadPhrescoConstansts(androidNative);
	}
	
	public void loadAndroidHybridConstants() throws ScreenException {
		loadPhrescoConstansts(androidHybrid);
	}

	public void loadAndroidLibraryConstants() throws ScreenException {
		loadPhrescoConstansts(androidLibrary);
	}

	public void loadiPhoneLibraryConstants() throws ScreenException {
		loadPhrescoConstansts(iPhoneLibrary);
	}

	public void loadBlackBerryHybridConstants() throws ScreenException {
		loadPhrescoConstansts(blackBerryHybrid);
	}

	public void loadWindowsMetroConstants() throws ScreenException {
		loadPhrescoConstansts(windowsMetro);
	}
	
	public void loadWindowsPhoneConstants() throws ScreenException {
		loadPhrescoConstansts(windowsPhone);
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


