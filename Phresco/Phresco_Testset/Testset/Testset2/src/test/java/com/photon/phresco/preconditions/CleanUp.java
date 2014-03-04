
 
package com.photon.phresco.preconditions;

import java.io.IOException;

import junit.framework.TestCase;

import org.testng.annotations.Test;



public class CleanUp extends TestCase{
	
	String methodName;
    
    @Test
    public void testNodeJsEshopRunDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }		
    
    @Test
    public void testphpHelloWorldDatabaseClone() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }		
    
   
}





