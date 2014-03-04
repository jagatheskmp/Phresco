package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class IPhoneWorkspaceConstantsXml {

	private ReadXMLFile readXml; 

	public String IPHONE_WORKSPACE_PROJECT_NAME="iPhoneWorkspaceNameValue";
	public String IPHONE_WORKSPACE_PROJECT_CODE="iPhoneWorkspaceCodeValue";
	public String IPHONE_WORKSPACE_PROJECT_DESCRIPTION="iPhoneWorkspaceDescriptionValue";
	public String IPHONE_WORKSPACE_PROJECT_VERSION="iPhoneWorkspaceVersionValue";
	public String IPHONE_WORKSPACE_TECHNOLOGY_VALUE="technologyiPhoneWorkspaceValue";
	public String IPHONE_WORKSPACE_TECHNOLOGY_VERSION="technologyiPhoneWorkspaceVersionValue";
	public String IPHONE_WORKSPACE_CHECKBOX="iPhoneWorkspaceCheckBoxXpath";
	public String IPHONE_WORKSPACE_EDIT_LINK="iPhoneWorkspaceEditApplicationLinkXpath";
	
	public IPhoneWorkspaceConstantsXml() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadiPhoneWorkspaceConstants();
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

