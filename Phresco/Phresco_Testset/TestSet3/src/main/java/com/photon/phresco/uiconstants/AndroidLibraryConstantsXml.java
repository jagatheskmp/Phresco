package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class AndroidLibraryConstantsXml {

	private ReadXMLFile readXml; 

	public String ANDROID_LIBRARY_PROJECT_NAME="androidLibraryNameValue";
	public String ANDROID_LIBRARY_PROJECT_CODE="androidLibraryCodeValue";
	public String ANDROID_LIBRARY_PROJECT_DESCRIPTION="androidLibraryDescriptionValue";
	public String ANDROID_LIBRARY_PROJECT_VERSION="androidLibraryVersionValue";
	public String ANDROID_LIBRARY_TECHNOLOGY_VALUE="technologyAndroidLibraryValue";
	public String ANDROID_LIBRARY_TECHNOLOGY_VERSION="technologyAndroidLibraryVersionValue";
	public String ANDROID_LIBRARY_CHECKBOX="androidLibraryCheckBoxXpath";
	public String ANDROID_LIBRARY_EDIT_LINK="androidLibraryEditApplicationLinkXpath";
	

	public AndroidLibraryConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadAndroidLibraryConstants();
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


