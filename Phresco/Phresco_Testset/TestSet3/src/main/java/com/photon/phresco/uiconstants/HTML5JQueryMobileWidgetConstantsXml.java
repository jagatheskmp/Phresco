package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class HTML5JQueryMobileWidgetConstantsXml {
	private ReadXMLFile readXml;

	public String JQUERY_WIDGET_HELLOWORLD_PROJECT_NAME="jQueryMobWidgetHelloworldNameValue";
	public String JQUERY_WIDGET_HELLOWORLD_PROJECT_CODE="jQueryMobWidgetHelloworldCodeValue";
	public String JQUERY_WIDGET_HELLOWORLD_PROJECT_DESCRIPTION="jQueryMobWidgetHelloworldDescriptionValue";
	public String JQUERY_WIDGET_HELLOWORLD_PROJECT_VERSION="jQueryMobWidgetHelloworldVersionValue";

 	public String JQUERY_MOB_WIDGET_HELLOWORLD_CHECKBOX="jQueryMobWidgetHelloWorldCheckBoxXpath";
	public String JQUERY_MOB_WIDGET_HELLOWORLD_EDIT_LINK="jQueryMobWidgetHelloWorldEditApplicationLinkXpath";
	
	public String JQUERY_MOB_WIDGET_SERVER_VALUE="appInfoJQueryMobWidgetServerValue";
	public String JQUERY_MOB_WIDGET_HELLOWORLD_TOMCAT_VERSION="jQueryMobWidgetHelloworldTomacatServerVersionValue";
   
 	public String JQUERY_WIDGET_HELLOWORLD_CONFIG_SERVER_CONTEXT="jqueryconfigServContextValueHelloWorld";
		
	public String JQUERY_WIDGET_ESHOP_PROJECT_NAME="jQueryMobWidgetEshopNameValue";
	public String JQUERY_WIDGET_ESHOP_PROJECT_CODE="jQueryMobWidgetEshopCodeValue";
	public String JQUERY_WIDGET_ESHOP_PROJECT_DESCRIPTION="jQueryMobWidgetEshopDescriptionValue";
	public String JQUERY_WIDGET_ESHOP_PROJECT_VERSION="jQueryMobWidgetEshopVersionValue";

	public String WEBLAYER_HTML5_VALUE="html5WebLayerValue";
	public String JQUERY_MOB_WIDGET_VALUE="jQueryMobWidgetValue";
	public String JQUERY_MOB_WIDGET_VERSION_VALUE="jQueryMobWidgetVersionValue";
	
	public String JQUERY_MOB_WIDGET_ESHOP_CHECKBOX="jQueryMobWidgetEshopCheckBoxXpath";
	public String JQUERY_MOB_WIDGET_ESHOP_EDIT_LINK="jQueryMobWidgetEshopEditApplicationLinkXpath";

	public String PILOT_PROJECT_ESHOP="pilotProjectEshopValue";
		
	public String CONFIG_SERVER_NAME_VALUE="configServNameValue";
  	public String CONFIG_SERVER_DESC_VALUE="configservDescValue";
  	public String CONFIG_SERVER_ENV_VALUE="configServEnvValue";
  	public String CONFIG_SERVER_VALUE="configServValue";
  	public String CONFIG_SERVER_PROTOCOL_VALUE="configServProtocolValue";
  	public String CONFIG_SERVER_HOST_VALUE="configServHostValue";
 	public String CONFIG_SERVER_PORT_VALUE="configServPortValue";
    public String CONFIG_SERVER_DEPLOY_VALUE="configServDeployDirectoryValue";
      
    public String JQUERY_MOB_WIDGET_ESHOP_SERVER_CONTEXT_VALUE="jqueryconfigServContextValueEshop";
 
  	public String CONFIG_WEBSERVICE_NAME_VALUE="configWebServiceNameValue";
 	public String CONFIG_WEBSERVICE_DESC_VALUE="configWebserviceDesc";
  	public String CONFIG_WEBSERVICE_ENV_VALUE="configWebserviceEnvValue";
  	public String CONFIG_WEBSERVICE_VALUE="configWebserviceValue";
  	public String CONFIG_WEBSERVICE_PROTOCAOL_VALUE="configWebserviceProtocolValue";
  	public String CONFIG_WEBSERVICE_HOST_VALUE="configWebserviceHostValue";
  	public String CONFIG_WEBSERVICE_PORT_VALUE="configWebservicePortValue";
    public String JQUERY_WIDGET_ESHOP_CONFIG_WEBSERVICE_CONTEXT="jqueryconfigWebserviceContextEshopValue";
 	
	public HTML5JQueryMobileWidgetConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadJqueryWidgetConstants();
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