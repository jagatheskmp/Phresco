package com.photon.phresco.eshop.enumscreen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;


public enum ElementTypeFinder {

	XPATH{
		public By execute(String text){
			return By.xpath(text);

		}
	},
	LINKTEXT{
		public By execute(String text){
			return By.linkText(text);

		}
	}, 
	ID{
		public By execute(String text){
			return By.id(text);

		}
	}, 
	CLASSNAME{
		public By execute(String text){
			return By.className(text);

		}
	}, 
	CSSSELECTOR{
		public By execute(String text){
			return By.cssSelector(text);

		}
	}, 
	NAME{
		public By execute(String text){
			return By.name(text);

		}
	}, 
	PARTIALLINKTEXT{
		public By execute(String text){
			return By.partialLinkText(text);

		}
	}, 
	TAGNAME{
		public By execute(String text){
			return By.tagName(text);

		}
	};

	public By execute(String text){
		Log log = LogFactory.getLog("ElementTypeFinder Enum Class");
		log.info("Element Types should be any of listed here based on your ID   xpath,linkText,className,id,cssSelector,name,partialLinkText,tagName");
		return null;

	}

}
