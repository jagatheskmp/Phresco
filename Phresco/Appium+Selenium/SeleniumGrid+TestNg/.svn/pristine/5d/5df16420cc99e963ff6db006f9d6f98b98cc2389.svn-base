package com.photon.phresco.testcases;

import org.testng.annotations.Factory;

public class WebTestFactory {
	  @Factory
	  public Object[] createInstances() {
	   Object[] result = new Object[2]; 
	   for (int i = 0; i < 2; i++) {
	      result[i] = new WebTest(i * 2);

//		   System.out.println("++++   "+result[i].toString());
	    }
	    return result;
	  }
	}