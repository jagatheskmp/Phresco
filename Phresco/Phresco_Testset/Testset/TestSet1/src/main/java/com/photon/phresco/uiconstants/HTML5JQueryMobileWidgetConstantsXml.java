package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class HTML5JQueryMobileWidgetConstantsXml {
	private ReadXMLFile readXml;

	public String JQUERY_MOB_WIDGET_HELLOWORLD_NAME = "jQueryMobWidgetHelloworldNameValue";
	public String JQUERY_MOB_WIDGET_HELLOWORLD_DESC = "jQueryMobWidgetHelloworldDescriptionValue";
	public String JQUERY_MOB_WIDGET_ESHOP_NAME = "jQueryMobWidgetEshopNameValue";
	public String JQUERY_MOB_WIDGET_ESHOP_DESC = "jQueryMobWidgetEshopDescriptionValue";	
	public String JQUERY_MOB_WIDGET_HELLOWORLD_EDIT_LINK = "jQueryMobWidgetHelloWorldEditApplicationLinkXpath";
	public String JQUERY_MOB_WIDGET_ESHOP_EDIT_LINK = "jQueryMobWidgetEshopEditApplicationLinkXpath";
	public String WEB_LAYER_HTML5_VALUE = "html5WebLayerValue";
	public String JQUERY_MOB_WIDGET_VALUE = "jQueryMobWidgetValue";
	public String JQUERY_MOB_WIDGET_VERSION_VALUE = "jQueryMobWidgetVersionValue";
	public String PILOT_PROJECT_ESHOP = "pilotProjectEshopValue";
	public String WIDGET_SERVER_VALUE = "widgetServerValue";
	public String WIDGET_SERVER_VERSION = "widgetServerVersionValue";
	public String CONFIG_SERVER_NAME_VALUE = "configServNameValue";
	public String CONFIG_SERVER_DESC_VALUE = "configservDescValue";
	public String CONFIG_SERVER_ENV_VALUE = "configServEnvValue";
	public String CONFIG_SERVER_VALUE = "configServValue";
	public String CONFIG_SERVER_PROTOCOL_VALUE = "configServProtocolValue";
	public String CONFIG_SERVER_HOST_VALUE = "configServHostValue";
	public String CONFIG_SERVER_PORT_VALUE = "configServPortValue";
	public String CONFIG_SERVER_DEPLOY_VALUE = "configServDeployDirectoryValue";
	public String JQUERY_MOB_WIDGET_HW_SERVER_CONTEXT = "jqueryconfigServContextValueEshopHW";
	public String JQUERY_MOB_WIDGET_ESHOP_SERVER_CONTEXT = "jqueryconfigServContextValueEshop";
	public String CONFIG_WEBSERVICE_NAME_VALUE = "configWebServiceNameValue";
	public String CONFIG_WEBSERVICE_DESC_VALUE = "configWebserviceDesc";
	public String CONFIG_WEBSERVICE_ENV_VALUE = "configWebserviceEnvValue";
	public String CONFIG_WEBSERVICE_VALUE = "configWebserviceValue";
	public String CONFIG_WEBSERVICE_PROTOCAOL_VALUE = "configWebserviceProtocolValue";
	public String CONFIG_WEBSERVICE_HOST_VALUE = "configWebserviceHostValue";
	public String CONFIG_WEBSERVICE_PORT_VALUE = "configWebservicePortValue";
	public String JQUERY_MOB_WIDGET_ESHOP_WEBSERVICE_CONTEXT = "jqueryconfigWebserviceContextEshopValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

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