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

	private static Element eElement;
	private Log log = LogFactory.getLog(getClass());
	private static final String phrscUi = "./src/main/resources/phrescoUiConstants.xml";
	private static final String phrscEnv = "./src/main/resources/phresco_env_config.xml";
	private static final String phrscData="./src/main/resources/PhrescoFrameworkData.xml";
	private static final String UserInfoConst="./src/main/resources/UserInfoConstants.xml";
	private static final String php="./src/main/resources/phpConstants.xml";
	private static final String drupal6="./src/main/resources/Drupal6Constants.xml";
	private static final String drupal7="./src/main/resources/Drupal7Constants.xml";
	private static final String wordPress="./src/main/resources/WordPressConstants.xml";
	private static final String jQueryMobWidget="./src/main/resources/HTML5JQueryMobileWidget.xml";
	private static final String multiYUIWidget="./src/main/resources/HTML5MultichannelYUIWidgetConstants.xml";
	private static final String multiJQueryWidget="./src/main/resources/HTML5MultichannelJQueryWidgetConstants.xml";
	private static final String siteCore="./src/main/resources/SiteCoreConstants.xml";
	private static final String iPhoneNative="./src/main/resources/IPhoneNativeConstants.xml";
	private static final String iPhoneHybrid="./src/main/resources/IPhoneHybridConstants.xml";
	private static final String androidNative="./src/main/resources/AndroidNativeConstants.xml";
	private static final String androidHybrid="./src/main/resources/AndroidHybridConstants.xml";
	private static final String androidLibrary="./src/main/resources/AndroidLibraryConstants.xml";
	private static final String iPhoneLibrary="./src/main/resources/IPhoneLibraryConstants.xml";
	private static final String iPhoneWorkspace="./src/main/resources/IPhoneWorkspaceConstants.xml";
	private static final String blackBerryHybrid="./src/main/resources/BlackBerryHybridConstants.xml";                                                                     
	private static final String windowsMetro="./src/main/resources/WindowsMetroConstants.xml"; 
	private static final String windowsPhone="./src/main/resources/WindowsPhoneConstants.xml";
	private static final String AspDotNet="./src/main/resources/AspDotNetConstants.xml";
	private static final String SharePoint="./src/main/resources/SharePointConstants.xml";
	private static final String JavaStandAlone="./src/main/resources/JavaStandaloneConstants.xml";
	private static final String JavaWebService="./src/main/resources/JavaWebServiceConstants.xml";
	private static final String NodeJsWebService="./src/main/resources/NodeJsWebServiceConstants.xml";
	private static final String DotNetTechnologies="./src/main/resources/DotNetTechnologiesServer.xml";
	private static final String yuiConst="./src/main/resources/HTML5YUIMobileWidget.xml";
	private static final String importApplication="./src/main/resources/ImportApplicationConstants.xml";
	private static final String ArchetypeCreate="./src/main/resources/ArchetypeCreation.xml";
	private static final String runAgainstSource="./src/main/resources/RunAgainstSourceConstants.xml";
	private static final String cloneConfiguration="./src/main/resources/CloneConfigurationConstants.xml";
	private static final String environmentcreation="./src/main/resources/EnvironmentConstans.xml";
	private static final String server_database_web="./src/main/resources/ServerDBWebXml.xml/";
	public  static final String tooltipverification="./src/main/resources/TestSet3.xml/";
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
	public void loadJqueryWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(jQueryMobWidget);
	}
	
	public void loadMultiChannelYUIWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(multiYUIWidget);
	}
	
	public void loadMultiChannelJQueryWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(multiJQueryWidget);
	}
	
	public void loadSiteCoreConstants() throws ScreenException {
		loadPhrescoConstansts(siteCore);
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
		
	public void loadiPhoneWorkspaceConstants() throws ScreenException {
		loadPhrescoConstansts(iPhoneWorkspace);
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
	
	
	public void loadAspDotNetConstants() throws ScreenException {
		loadPhrescoConstansts(AspDotNet);
	}
	
	public void loadSharePointConstants() throws ScreenException {
		loadPhrescoConstansts(SharePoint);
	}
	
	public void loadJavaStandaloneConstants() throws ScreenException {
		loadPhrescoConstansts(JavaStandAlone);
	}
	
	public void loadJavaWebServiceConstants() throws ScreenException {
		loadPhrescoConstansts(JavaWebService);
	}
	
	public void loadNodeJsWebServiceConstants() throws ScreenException {
		loadPhrescoConstansts(NodeJsWebService);
	}
	public void loadDotNetTechnologiesServer() throws ScreenException {
		loadPhrescoConstansts(DotNetTechnologies);
	}
	public void loadYUIMobileWidgetConstants() throws ScreenException {
		loadPhrescoConstansts(yuiConst);
	}
	
	
		/*Import Application testset1*/
	public void loadImportApplication() throws ScreenException {
		loadPhrescoConstansts(importApplication);
	}
	public void loadArchetyeCreation() throws ScreenException {
		loadPhrescoConstansts(ArchetypeCreate);
	}
	
	public void loadCloneConfiguration() throws ScreenException {
		loadPhrescoConstansts(cloneConfiguration);
		
	}

	public void loadEnvironmentConstants() throws ScreenException {
		loadPhrescoConstansts(environmentcreation);
		
	}
	
	public void loadRunAgainstSource() throws ScreenException {
		loadPhrescoConstansts(runAgainstSource);
	}
	public void loadServerDBWebXml()throws ScreenException
	{
		loadPhrescoConstansts(server_database_web);
	}
	public void loadTestSet3Xml() throws ScreenException
	{
		loadPhrescoConstansts(tooltipverification);
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


