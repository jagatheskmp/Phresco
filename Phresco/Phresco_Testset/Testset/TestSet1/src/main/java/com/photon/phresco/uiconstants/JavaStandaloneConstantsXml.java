package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class JavaStandaloneConstantsXml {

	private ReadXMLFile readXml;
	public String JAVA_SA_HELLOWORLD_NAME_VALUE = "javaStandaloneHelloWorldNameValue";
	public String JAVA_SA_HELLOWORLD_DESC_VALUE = "javaStandaloneHelloWorldDescriptionValue";
	public String JAVA_SA_HELLOWORLD_VALUE = "javaStandaloneValue";
	public String JAVA_SA_HELLOWORLD_EDIT_APP_LINK = "javaStandaloneHelloWorldEditApplicationLinkXpath";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public JavaStandaloneConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadJavaStandaloneConstants();
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
