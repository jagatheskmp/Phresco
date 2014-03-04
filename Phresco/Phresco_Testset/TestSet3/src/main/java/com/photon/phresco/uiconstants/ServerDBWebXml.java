package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class ServerDBWebXml {

	private ReadXMLFile readXml;
	
	public String SERVER_VALUE="Xamp_server_value";
   	public String MONGODB_DATABASE_VALUE_PASS="mongodb_value_pass";
	public String PHP_APP_INFO_PILOT_PROJECT_BLOG_VALUE="phpPilotProjectBlogValue";
   	
   	public ServerDBWebXml()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadServerDBWebXml();
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