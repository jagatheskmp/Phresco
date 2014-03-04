package com.photon.phresco.testcases;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;



public class ProgramTest extends TestListenerAdapter {

	
	public void onFinish(ITestContext arg0) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { ProgramaticalTest.class });
		testng.addListener(tla);
		testng.run(); 
		
	}
	public void onStart(ITestContext arg0) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void onTestStart(ITestResult arg0) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { ProgramaticalTest.class });
		testng.addListener(tla);
		testng.run();
		
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { ProgramaticalTest.class ,Pg.class});
		testng.addListener(tla);
		testng.run();
		
	}

}
