package com.photon.phresco.automation.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.photon.phresco.automation.TestCase;
import com.photon.phresco.automation.model.GridInfo;
import com.photon.phresco.automation.model.Param;
import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

public class XmlParser {

	private VTDNav vn;

	public boolean parseXml(String xmlFile) throws Exception {
		VTDGen vg = new VTDGen();
		if (vg.parseFile(xmlFile, true)) {
			vn = vg.getNav();
//			vn.toElement(VTDNav.FC, "automation");
			return true;
		}
		return false;

	}

	public List<XmlSuite> getSuites() throws NavException, XPathParseException, XPathEvalException {
		vn.toElement(VTDNav.ROOT);
		AutoPilot ap = getAutoPilot(vn, "/automation/testsuites");
		ap.selectElement("testsuite");
		List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>(8);
		while (ap.iterate()) {
			XmlSuite xmlSuite = new XmlSuite();
			xmlSuite.setParallel("tests");
			int index = vn.getAttrVal("name");
			xmlSuite.setName(vn.toString(index));
			setTestsInSuites(xmlSuite);
			xmlSuites.add(xmlSuite);
		}

		return xmlSuites;
	}

	private void setTestsInSuites(XmlSuite xmlSuite) throws NavException, XPathParseException, XPathEvalException {
//		VTDNav cloneNav = vn.cloneNav();
		int nodeinfoIds = vn.getAttrVal("nodeinfos");
//		cloneNav.toElement(VTDNav.ROOT);
//		int i = cloneNav.getText();
//		AutoPilot ap = new AutoPilot(vn);
//		ap.selectElement("testexecutioninfos");
//		int index = cloneNav.getAttrVal("ids");
		List<String> ids = Arrays.asList(vn.toString(nodeinfoIds).trim().split("\\s*,\\s*"));
		VTDNav cloneNav = vn.cloneNav();
		for (String id : ids) {
			HashMap<String, String> nodeInfo = new HashMap<String, String>(8); //getGridInfo().getNodeinfo().get(id);
			AutoPilot ap = getAutoPilot(cloneNav, "/automation/gridinfo/nodeinfos/nodeinfo[@id='"+ id +"']");

			if (ap.evalXPath() != -1) {
				nodeInfo.put("appurl", getAppUrl(cloneNav));
				nodeInfo.put("suite", xmlSuite.getName());

				if (cloneNav.toElement(VTDNav.FC, "nodeurl")) {
					int index = cloneNav.getText();
					nodeInfo.put("nodeurl", cloneNav.toString(index));
				}
				if (cloneNav.toElement(VTDNav.NS, "platform")) {
					int index = cloneNav.getText();
					nodeInfo.put("platform", vn.toString(index));
				}
				if (cloneNav.toElement(VTDNav.NS, "browser")) {
					int index = cloneNav.getText();
					List<String> browsers = Arrays.asList(vn.toString(index).trim().split("\\s*,\\s*"));
					for (String browser : browsers) {
						nodeInfo.put("browser", browser);
						XmlTest xmlTest = new XmlTest(xmlSuite);
						xmlTest.setParameters((Map<String, String>) nodeInfo.clone());
						List<XmlClass> classes = new ArrayList<XmlClass>();
						XmlClass xmlClass = new XmlClass("com.photon.phresco.automation.AutomationFactory");
						classes.add(xmlClass);
						xmlTest.setXmlClasses(classes) ;
					}
				}
			}
		}
	}
	

	/*private List<XmlClass> getClasses() throws NavException {
		AutoPilot ap = new AutoPilot(vn);
		ap.selectElement("testcase");
		int index = vn.getAttrVal("name");
		String testCaseName = vn.toString(index);
		AutoPilot apSteps = new AutoPilot(vn);
		ap.selectElement("step");
		while (ap.iterate()) {
		}
	}*/

	public List<TestCase> getTestCases(String suite) throws NavException, XPathParseException, XPathEvalException {
		
		vn.toElement(VTDNav.ROOT);
		AutoPilot ap = getAutoPilot(vn, "/automation/testsuites/testsuite[@name='"+suite +"']");
		ap.selectElement("testcase");
		List<TestCase> testCases = new ArrayList<TestCase>(8);
		while (ap.iterate()) {
			int indexId = vn.getAttrVal("name");
			String testCaseName = vn.toString(indexId);
			AutoPilot apSteps = new AutoPilot(vn);
			apSteps.selectElement("step");
			List<Step> steps = new ArrayList<Step>(8);
			while (apSteps.iterate()) {
				int index = vn.getText();
				String stepId = vn.toString(index);
				Step step = getTestStep(stepId);
				steps.add(step);
			}
			TestCase testCase = new TestCase(testCaseName, steps);
			testCases.add(testCase);
		}
		return testCases;
	}

	public String getAppUrl(VTDNav vn) throws NavException, XPathParseException, XPathEvalException {
		VTDNav cloneNav = vn.cloneNav();
		cloneNav.toElement(VTDNav.ROOT);
		AutoPilot ap = getAutoPilot(cloneNav, "/automation/appurl");
		if (ap.evalXPath() != -1) {
			int index = cloneNav.getText();
			return cloneNav.toString(index);
		}
		return null;
	}

	private AutoPilot getAutoPilot(VTDNav vn, String xpath) throws XPathParseException {
		AutoPilot ap = new AutoPilot(vn);
		ap.selectXPath(xpath);
		return ap;
	}

	@SuppressWarnings("unchecked")
	public GridInfo getGridInfo() throws NavException, XPathParseException, XPathEvalException {
		VTDNav cloneNav = vn.cloneNav();
		cloneNav.toElement(VTDNav.ROOT);
		AutoPilot ap = getAutoPilot(cloneNav, "/automation/gridinfo");
		GridInfo gridInfo = new GridInfo();
		if (ap.evalXPath() != -1) {
			if( cloneNav.toElement(VTDNav.FC, "huburl")) {
				int index = cloneNav.getText();
				gridInfo.setHubURL(cloneNav.toString(index));
			}

			cloneNav.toElement(VTDNav.NS, "nodeinfos");
			AutoPilot apNodeInfo = new AutoPilot(cloneNav);
			Map<String, HashMap<String, String>> nodeInfos = new HashMap<String, HashMap<String, String>>(8);
			apNodeInfo.selectElement("nodeinfo");
			while (apNodeInfo.iterate()) {
				String id="";
				HashMap<String, String> params = new HashMap<String, String>(8);
				/*if (cloneNav.toElement(VTDNav.FC, "id")) {
					int index = cloneNav.getText();
					id = cloneNav.toString(index);
				}*/
				if (cloneNav.toElement(VTDNav.NS, "nodeurl")) {
					int index = cloneNav.getText();
					params.put("nodeurl", cloneNav.toString(index));
				}
				if (cloneNav.toElement(VTDNav.NS, "platform")) {
					int index = cloneNav.getText();
					params.put("platform", vn.toString(index));
				}
				if (cloneNav.toElement(VTDNav.NS, "browser")) {
					int index = cloneNav.getText();
					List<String> browsers = Arrays.asList(vn.toString(index).trim().split("\\s*,\\s*"));
					for (String browser : browsers) {
						params.put("browser", browser);
						nodeInfos.put(id+browser, (HashMap<String, String>) params.clone());
					}
				}

			}
			gridInfo.setNodeinfo(nodeInfos);

		}
		return gridInfo;
	}


	public Map<String, Param> getTextExecInfo() throws NavException, XPathParseException, XPathEvalException {
		vn.toElement(VTDNav.ROOT);
		AutoPilot ap = getAutoPilot(vn, "/automation/testexecutioninfos");
		Map<String, Param> params = new HashMap<String, Param>(8);
		if (ap.evalXPath() != -1) {
			AutoPilot apParam = new AutoPilot(vn);
			apParam.selectElement("param");
			while (apParam.iterate()){
				Param nodeInfo = new Param();
				if (vn.toElement(VTDNav.FC, "id")) {
					int index = vn.getText();
					nodeInfo.setId(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS, "platform")) {
					int index = vn.getText();
					nodeInfo.setPlatform(vn.toString(index));
				}
				if (vn.toElement(VTDNav.NS, "browser")) {
					int index = vn.getText();
					nodeInfo.setBrowser(vn.toString(index));
				}
				params.put(nodeInfo.getId(), nodeInfo);
			}

		}
		return params;
	}

	public Step getTestStep(String stepId) throws NavException, XPathParseException, XPathEvalException {
		VTDNav cloneNav = vn.cloneNav();
		cloneNav.toElement(VTDNav.ROOT);
		AutoPilot ap = getAutoPilot(cloneNav, "/automation/teststeps/step[@id='"+ stepId +"']");
		Step step = new Step();
		if (ap.evalXPath() != -1) {
			int index = cloneNav.getAttrVal("id");
			step.setId(cloneNav.toString(index));

			index = cloneNav.getAttrVal("name");
			step.setName(cloneNav.toString(index));

			index = cloneNav.getAttrVal("action");
			step.setAction(cloneNav.toString(index));

			index = cloneNav.getAttrVal("type");
			if (index != -1) {
				step.setType(cloneNav.toString(index));
			}

			index = cloneNav.getAttrVal("value");
			if (index != -1) {
				step.setValue(cloneNav.toString(index));
			}
		}
		return step;
	}

	public static void main(String[] args) {
		XmlParser xmlParser = new XmlParser();
		try {
			boolean parseXml = xmlParser.parseXml("/Users/bharatkumarradha/WorkOffice/work/Automation/svn/SeleniumGrid+TestNg/AutomationFramework/src/test/resources/automation.xml");
			if (parseXml) {
				List<XmlSuite> suites = xmlParser.getSuites();
//				GridInfo gridInfo = xmlParser.getGridInfo();
//				System.out.println(gridInfo);
				/*List<TestCase> testCases = xmlParser.getTestCases("eshop");
				System.out.println(testCases2.size());*/
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
