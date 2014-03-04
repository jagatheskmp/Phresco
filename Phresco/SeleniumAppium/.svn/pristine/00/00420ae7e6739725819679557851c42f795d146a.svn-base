package com.photon.phresco.mydevice.enumscreen;

import com.photon.phresco.mydevice.model.UiIdentifiers;
import com.photon.phresco.mydevice.parser.ElementIdentifiersParser;

public enum LoadXmlParser {

	ANDROID{
		public UiIdentifiers execute(){
			ElementIdentifiersParser uid= new ElementIdentifiersParser();
			try {
				return uid.parseXml("./src/main/resources/Android-UiIdentifiers.xml");
			} catch (Exception e) {

				e.printStackTrace();
			}
			return null;
		}

	},
	IOS{
		public UiIdentifiers execute(){
			ElementIdentifiersParser uid= new ElementIdentifiersParser();
			try {
				return uid.parseXml("./src/main/resources/Iphone-UiIdentifiers.xml");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	};

	public UiIdentifiers execute(){
		return null;
	}
}
