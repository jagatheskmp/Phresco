package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;



public class HTML5YUIMobileWidgetXml {
	private ReadXMLFile readXml;
	   
   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_NAME="yuiMobileWidgetHelloworldNameValue";
   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_CODE="yuiMobileWidgetHelloworldCodeValue";
   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_DESC="yuiMobileWidgetHelloworldDescriptionValue";
   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_VERSION="yuiMobileWidgetHelloworldVersionValue";

   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_SERVER_CONTEXT="yuiMobileconfigServContextHelloWorld";

   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_CHECKBOX="yuiMobileWidgetHelloWorldCheckBoxXpath";
   	public String YUI_MOBILE_WIDGET_HELLOWORLD_PROJECT_LINK="yuiMobileWidgetHelloWorldEditApplicationLinkXpath";
	
	public String YUI_MOBILE_WIDGET_SERVER="appInfoMobileyuiMobileWidgetServerValue";
	public String YUI_MOBILE_WIDGET_TOMCAT_VERSION="TomacatServerVersionValue";

	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_NAME="yuiMobileWidgetEshopNameValue";
	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_CODE="yuiMobileWidgetEshopCodeValue";
	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_DESC="yuiMobileWidgetEshopDescriptionValue";
	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_VERSION="yuiMobileWidgetEshopVersionValue";

	public String HTML_VALUE="html5WebLayerValue";
	public String YUI_MOBILE_WIDGET_NAME_VALUE="yuiMobileWidgetValue";
	public String YUI_MOBILE_WIDGET_VERSION_VALUE="yuiMobileWidgetVersionValue";

	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_CHECKBOX="yuiMobileMobWidgetEshopCheckBoxXpath";
	public String YUI_MOBILE_WIDGET_ESHOP_PROJECT_LINK="yuiMobileMobWidgetEshopEditApplicationLinkXpath";

	public String PILOT_PROJECT_ESHOP_VALUE="pilotProjectEshopValue";
  	
  	public String CONFIG_SERVER_NAME_VALUE="configServNameValue";
  	public String CONFIG_SERVER_DESC_VALUE="configservDescValue";
  	public String CONFIG_SERVER_ENV_VALUE="configServEnvValue";
  	public String CONFIG_SERVER_VALUE="configServValue";
  	public String CONFIG_SERVER_PROTOCOL_VALUE="configServProtocolValue";
  	public String CONFIG_SERVER_HOST_VALUE="configServHostValue";
 	public String CONFIG_SERVER_PORT_VALUE="configServPortValue";
    public String CONFIG_SERVER_DEPLOY_VALUE="configServDeployDirectoryValue";
      
    public String YUI_WIDGET_ESHOP_SERVER_CONTEXT_VALUE="yuiMobileServContextValueEshop";

  	public String CONFIG_WEBSERVICE_NAME_VALUE="configWebServiceNameValue";
 	public String CONFIG_WEBSERVICE_DESC_VALUE="configWebserviceDesc";
  	public String CONFIG_WEBSERVICE_ENV_VALUE="configWebserviceEnvValue";
  	public String CONFIG_WEBSERVICE_VALUE="configWebserviceValue";
  	public String CONFIG_WEBSERVICE_PROTOCAOL_VALUE="configWebserviceProtocolValue";
  	public String CONFIG_WEBSERVICE_HOST_VALUE="configWebserviceHostValue";
  	public String CONFIG_WEBSERVICE_PORT_VALUE="configWebservicePortValue";
   	
  	public String YUI_WIDGET_ESHOP_WEBSERVICE_CONTEXT_VALUE="yuiMobileWebserviceContextEshopValue";
 
	public HTML5YUIMobileWidgetXml(){
	try {
			readXml = new ReadXMLFile();
		    readXml.loadYUIMobileWidgetConstants();
			Field[] arrayOfField1 = super.getClass().getFields();
			Field[] arrayOfField2 = arrayOfField1;
			int i = arrayOfField2.length;
			for (int j = 0; j < i; ++j) {
				Field localField = arrayOfField2[j];
				Object localObject = localField.get(this);
				if (localObject instanceof String)
					localField
							.set(this, readXml.getValue((String) localObject));

			}
		} catch (Exception localException) {
			throw new RuntimeException("Loading "
					+ super.getClass().getSimpleName() + " failed",
					localException);
		}
	}
}


