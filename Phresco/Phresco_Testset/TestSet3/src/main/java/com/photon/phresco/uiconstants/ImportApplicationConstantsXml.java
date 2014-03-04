package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class ImportApplicationConstantsXml {
	
		private ReadXMLFile readXml;
		   
			public String SVN="svn";
			public String GITHUB="github";	
			public String USERNAME="username";
			public String PASSWORD="password";			
			public String SVN_REPO_URL="svnrepoUrlPhpBlog";
			public String PHP_BLOG_TEXT="phpText";					
			public String SVN_REPO_URL_REVISON="svnrepoUrlNodejsEshop";
			public String SVN_REVISION_NUMBER="svnrevisionNodejsEshop";			
			public String NODE_JS_TEXT="NodejsEshopText";			
			public String OTHER_USERNAME="otherUsername";
			public String OTHER_PASSWORD="otherPassword";
			public String GITHUB_URL="gitUrl"; 
			
		
		public ImportApplicationConstantsXml(){
		try {
				readXml = new ReadXMLFile();
			    readXml.loadImportApplication();
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
