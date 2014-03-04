package com.photon.phresco.uiconstants;
import java.lang.reflect.Field;

public class EnvironmentConstantsXml {

		private ReadXMLFile readXml;
		

		public String ENV_NAME_VALUE="Envirionmentname";
		public String DECSCRIPTION_VALUE="decscription_value";
		// Global Setting Variables 
		public String CONFIGURATION_SERVER_NAME_VALUE="configname";
		public String CONFIGURATION_SERVER_DESCRIPTION_VALUE="DescriptionvField";
		public String CONFIGURATION_SERVER_ENVIRONMENT_VALUE="EnvValue";
		public String CONFIGURATION_SERVER_TYPE_VALUE="typeValue"; 
		public String CONFIGURATION_SERVER_PROTOCOL_HTTP_VALUE="protocalvalue"; 
		public String CONFIGURATION_SERVER_HOST_VALUE="host_value"; 
		public String CONFIGURATION_SERVER_PORT_VALUE ="portvalue"; 
		public String CONFIGURATION_SERVER_DEPLOY_DIRECTORY_VALUE ="deploydirectory"; 
		public String SERVERTYPE_VALUE="ServerValue";
		public String CONTEXT_VALUE="context_value";
		public EnvironmentConstantsXml()
		{
			try {
				readXml = new ReadXMLFile();
				readXml.loadEnvironmentConstants();
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
			}
			catch (Exception localException) {
				throw new RuntimeException("Loading "
						+ super.getClass().getSimpleName() + " failed",
						localException);
			}
		}
	}


