package com.photon.phresco.eshop.enumscreen;

import com.photon.phresco.eshop.model.UiIdentifiers;
import com.photon.phresco.eshop.parser.ElementIdentifiersParser;

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

	};

	public UiIdentifiers execute(){
		return null;
	}
}
