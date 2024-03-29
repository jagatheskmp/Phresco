package com.photon.phresco.Listener;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class MyListener implements ITestListener, IInvokedMethodListener,IReporter {

	public void onFinish(ITestContext arg0) {
		//Reporter.log("Completed executing test " + arg0.getName(), true);
	}

	public void onStart(ITestContext arg0) {
		//Reporter.log("About to begin executing test First " + arg0.getName(), true);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
	}

	private void printTestResults(ITestResult result) {
		Reporter.log("NodeDetails: " + result.getTestName(), true);
		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
	}

	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestStart(ITestResult arg0) {
	}

	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		//String textMsg = "Completed executing " + returnMethodName(arg0.getTestMethod());
		//Reporter.log(textMsg, true);

	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		//String textMsg = "About to begin executing " + returnMethodName(arg0.getTestMethod());
		//Reporter.log(textMsg, true);
	}

	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		// TODO Auto-generated method stub
		
	}

}
