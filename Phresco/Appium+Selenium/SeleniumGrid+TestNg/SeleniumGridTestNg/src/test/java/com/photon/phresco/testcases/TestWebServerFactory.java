package com.photon.phresco.testcases;

import org.testng.annotations.Test;

public class TestWebServerFactory {
	@Test(parameters = { "number-of-times" })
	  public void accessPage(int numberOfTimes) {
	    while (numberOfTimes-- > 0) {
	     System.out.println("TestWebServerFactory");
	    }
	  }
}
