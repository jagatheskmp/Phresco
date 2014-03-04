package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class IPhoneLibraryConstantsXml {

	private ReadXMLFile readXml; 
	public String IPHONE_LIBRARY_PROJECT_NAME = "iPhoneLibraryNameValue";
	public String IPHONE_LIBRARY_PROJECT_DESC = "iPhoneLibraryDescriptionValue";
	public String IPHONE_LIBRARY_TECHNOLOGY_VALUE = "technologyiPhoneLibraryValue";
	public String IPHONE_LIBRARY_EDIT_LINK = "iPhoneLibraryEditApplicationLinkXpath";
	public String IPHONE_LIBRARY_SDK_VALUE = "sdkValue";
	public String IPHONE_LIBRARY_TARGET_VALUE = "targetValue";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public IPhoneLibraryConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadiPhoneLibraryConstants();
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

