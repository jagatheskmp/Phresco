package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class WindowsPhoneConstantsXml {
	
	

		private ReadXMLFile readXml; 

		public String WINDOWS_PHONE_PROJECT_NAME="windowsPhoneNameValue";
		public String WINDOWS_PHONE_PROJECT_CODE="windowsPhoneCodeValue";
		public String WINDOWS_PHONE_PROJECT_DESCRIPTION="windowsPhoneDescriptionValue";
		public String WINDOWS_PHONE_PROJECT_VERSION="windowsPhoneVersionValue";
		public String WINDOWS_PHONE_TECHNOLOGY_VALUE="technologyWindowsPhoneValue";
		public String WINDOWS_PHONE_TECHNOLOGY_VERSION="technologyWindowsPhoneVersionValue";
		public String WINDOWS_PHONE_CHECKBOX="windowsPhoneCheckBoxXpath";
		public String WINDOWS_PHONE_EDIT_LINK="windowsPhoneEditApplicationLinkXpath";
	
		public WindowsPhoneConstantsXml() {
			try {
				readXml = new ReadXMLFile();
				readXml.loadWindowsPhoneConstants();
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

