package com.photon.phresco.testcases;

import org.testng.annotations.Test;

public class WebTest {
	  private int m_numberOfTimes;
	  public WebTest(int numberOfTimes) {
	    m_numberOfTimes = numberOfTimes;
	    System.out.println("m_numberOfTimes:**********"+m_numberOfTimes);
	  }
	 
	  @Test
	  public void testServer() {
		  System.out.println("inside testServer()......");
	   for (int i = 0; i < m_numberOfTimes; i++) {
		   System.out.println("m_numberOfTimes::::::::::::::::::::"+i);
	    }
	  }
	}