package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class WindowsMetroConstantsXml {

	private ReadXMLFile readXml; 
	public String WINDOWS_METRO_PROJECT_NAME = "windowsMetroNameValue";
	public String WINDOWS_METRO_PROJECT_DESC = "windowsMetroDescriptionValue";
	public String WINDOWS_METRO_TECHNOLOGY_VALUE = "technologyWindowsMetroValue";
	public String WINDOWS_METRO_EDIT_LINK = "windowsMetroEditApplicationLinkXpath";
	public String FUNCTIONAL_FRAMEWORK = "functionalFramework";

	public WindowsMetroConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadWindowsMetroConstants();
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

