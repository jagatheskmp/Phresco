package com.photon.phresco.automation.parser;

import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlSuite;

import com.photon.phresco.automation.TestCase;
import com.photon.phresco.automation.driver.IDriver;
import com.photon.phresco.automation.modal.TestParameter;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class XmlParser {
	
	private static String hubUrl;
	private static String appUrl;
	private static IDriver browserDriver = null;
	private static String platform;
	private static List<TestCase> testCases = new ArrayList<TestCase>(8);

	public static String getHubUrl() {
		return XmlParser.hubUrl;
	}

	public static void setHubUrl(String hubUrl) {
		XmlParser.hubUrl = hubUrl;
	}

	public static String getAppUrl() {
		return XmlParser.appUrl;
	}

	public static void setAppUrl(String appUrl) {
		XmlParser.appUrl = appUrl;
	}

	public static IDriver getBrowserDriver() {
		return XmlParser.browserDriver;
	}

	public static void setBrowserDriver(IDriver browserDriver) {
		XmlParser.browserDriver = browserDriver;
	}

	public static String getPlatform() {
		return XmlParser.platform;
	}

	public static void setPlatform(String platform) {
		XmlParser.platform = platform;
	}

	public static List<TestCase> getTestCases() {
		return testCases;
	}

	public static void setTestCases(List<TestCase> testCases) {
		XmlParser.testCases = testCases;
	}

	public List<XmlSuite> parseXml(String xmlFile) throws Exception {
		VTDGen vg = new VTDGen();
		if (vg.parseFile(xmlFile, true)) {
			VTDNav vn = vg.getNav();
			if (vn.matchElement("testsuites")) {
				if (vn.toElement(VTDNav.FC,"baseinfo")) {
					if (vn.toElement(VTDNav.FC,"huburl")) {
						int index = vn.getText();
						XmlParser.hubUrl = vn.toString(index);
					}
					if (vn.toElement(VTDNav.NS,"appurl")) {
						int index = vn.getText();
						XmlParser.appUrl = vn.toString(index);
					}
				}
				vn.toElement(VTDNav.ROOT);
				AutoPilot ap = new AutoPilot(vn);
		        ap.selectElement("testsuite");
		        List<XmlSuite> testSuites = new ArrayList<XmlSuite>(8);
		        while (ap.iterate()) {
		        	
		        	XmlSuite suite = new XmlSuite();
					int indexName = vn.getAttrVal("name");
					suite.setName(vn.toString(indexName));
					if (vn.toElement(VTDNav.FC,"browser")) {
						int index = vn.getText();
						String browser = vn.toString(index);
						Class<IDriver> driverCls = (Class<IDriver>) Class.forName("com.photon.phresco.automation.driver."+browser+"Driver");
						XmlParser.browserDriver = driverCls.newInstance();
						/*List<String> browsers = Arrays.asList(vn.toString(index).trim().split("\\s*,\\s*"));
						for (String browser : browsers) {
							Class<IDriver> driverCls = (Class<IDriver>) Class.forName("com.photon.phresco.automation.driver."+browser+"Driver");
							browserDriver = driverCls.newInstance();
						}*/
					}
					if (vn.toElement(VTDNav.NS,"platform")){
						int indexPlatform = vn.getText();
						XmlParser.platform = "MAC"; //vn.toString(indexPlatform).toUpperCase();
						System.out.println("platform..."+XmlParser.platform);
//						platforms = Arrays.asList(vn.toString(index).trim().split("\\s*,\\s*"));
					}
//					driver = browserDriver.launchDriver(new URL(hubUrl), appUrl, platform);
					vn.toElement(VTDNav.ROOT);
					AutoPilot apTestCase = new AutoPilot(vn);
					apTestCase.selectElement("testcase");
					List<TestCase> testCases = new ArrayList<TestCase>(8);
					while (apTestCase.iterate()){
						int nameIndex = vn.getAttrVal("name");
						String testCaseName = vn.toString(nameIndex);
						AutoPilot apStep = new AutoPilot(vn);
						apStep.selectElement("step");
						List<TestParameter> testParams = new ArrayList<TestParameter>(8);
						while (apStep.iterate()){
							TestParameter testParam = new TestParameter();
							int stepNameIndex = vn.getAttrVal("name");
							testParam.setName(vn.toString(stepNameIndex));
							
							int actionIndex = vn.getAttrVal("action");
							testParam.setEvent(vn.toString(actionIndex));
							
							int xpathIndex = vn.getAttrVal("xpath");
							testParam.setXpath( vn.toString(xpathIndex));
							testParams.add(testParam);
						}
						TestCase testCase = new TestCase(testCaseName, testParams);
						XmlParser.testCases.add(testCase);
					}
//					suite.setTestCases(testCases);
					testSuites.add(suite);
				}
		        return testSuites;
			}
		}
		return new ArrayList<XmlSuite>(8);
	}
	
	public static void main(String[] args) {
		XmlParser parser = new XmlParser();
		try {
			parser.parseXml("/Users/bharatkumarradha/WorkOffice/work/Automation/SeleniumGridTestNg/src/test/resources/automation.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
