package com.photon.phresco.automation;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.photon.phresco.automation.parser.XmlParser;


public class TestSuiteExecution {

	public static void main(String[] args) throws MalformedURLException {

		try {
			XmlParser parser = new XmlParser();
			boolean isParseXml = parser.parseXml("./src/test/resources/automation.xml");
			if (isParseXml) {
				List<XmlSuite> suites = parser.getSuites();
				System.out.println("suites size... " +suites.size());
				for (XmlSuite xmlSuite : suites) {
					List<XmlTest> tests = xmlSuite.getTests();
					for (XmlTest xmlTest : tests) {
						System.out.println("test params " + xmlTest.getParameters());
					}
					
				}
				TestNG tng = new TestNG();
				tng.setXmlSuites(suites);
				tng.run();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
