package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class DotNetTechnologiesServer {

	private ReadXMLFile readXml;

   public String SERVERNAME="serverName";
   public String SERVERDESCRIPTION="serverDescription";
   public String SERVERTYPE="serverType";
   public String SERVERHOST="serverHost";
   public String SERVERPORT="serverPort";
   public String SERVERSELECTTYPE="serverSelectType";
   public String SERVERVERSION="serverVersion";
   public String DEPLOYDIRECTORY="deployDirectiory";
   public String SERVERCONTEXT="serverContext";
   
   public String SHAREPOINTSERVERDESC="sharePointServerDesc";
   public String SHAREPOINTSERVERCONTEXT="sharePointServerContext";
   public String SHAREPOINTNONEPORT="sharepointNonePort";
   public String SHAREPOINTHOST="sharepointHost";
   public String SHAREPOINTESHOPPORT="sharepoinEshopPort";
   

	public DotNetTechnologiesServer()
	{
		try {
			readXml = new ReadXMLFile();
			readXml.loadDotNetTechnologiesServer();
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