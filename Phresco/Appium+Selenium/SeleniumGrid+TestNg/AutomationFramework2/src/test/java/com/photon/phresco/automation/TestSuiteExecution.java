package com.photon.phresco.automation;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.photon.phresco.automation.parser.XmlParser;


public class TestSuiteExecution {

	public static void main(String[] args) throws MalformedURLException {

		try {
			XmlParser parser = new XmlParser();
			boolean isParseXml = parser.parseXml("/Users/bharatkumarradha/WorkOffice/work/Automation/svn/SeleniumGrid+TestNg/AutomationFramework/src/test/resources/automation.xml");
			if (isParseXml) {
				List<XmlSuite> suites = parser.getSuites();
				TestNG tng = new TestNG();
				tng.setXmlSuites(suites);
				tng.run();
			}
//			XmlSuite suite = new XmlSuite();
//			suite.setName("Automation TestSuite");

//			XmlTest test = new XmlTest(suites.get(0));
//			test.setName("SKIPPED THIS LEVEL");
//			List<XmlClass> classes = new ArrayList<XmlClass>();
//			XmlClass xmlClass = new XmlClass("com.photon.phresco.automation.AutomationFactory");
//			xmlClass.setParameters(parameters);
//			classes.add(xmlClass);
//			test.setXmlClasses(classes) ;

			/*List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
