package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class JavaStandaloneConstantsXml {

	private ReadXMLFile readXml;
	public String JAVA_STANDALONE_HELLOWORLD_PROJECT_NAME="javaStandaloneHelloWorldNameValue";
	public String JAVA_STANDALONE_HELLOWORLD_PROJECT_CODE="javaStandaloneHelloWorldCodeValue";
	public String JAVA_STANDALONE_HELLOWORLD_PROJECT_DESCRIPTION="javaStandaloneHelloWorldDescriptionValue";
	public String JAVA_STANDALONE_HELLOWORLD_PROJECT_VERSION="javaStandaloneHelloWorldVersionValue";

	
	public String JAVA_STANDALONE_HELLOWORLD_VALUE="javaStandaloneHelloWorldValue";
	public String JAVA_STANDALONE_HELLOWORLD_VERSION_VALUE="javaStandaloneHelloWorldVersionValue";
	
	public String JAVA_STANDALONE_HELLOWORLD_CHECKBOX_XPATH="javaStandaloneHelloWorldCheckBoxXpath";
	public String JAVA_STANDALONE_HELLOWORLD_EDIT_LINK="javaStandaloneHelloWorldEditApplicationLinkXpath";
	
	
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
