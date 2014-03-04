
 
package com.photon.phresco.preconditions;

import java.io.IOException;
import junit.framework.TestCase;
import org.testng.annotations.Test;



public class CleanUp extends TestCase{
	
	String methodName;
    
	@Test
    public void testphpHelloWorldDatabase() throws InterruptedException, IOException, Exception{
    	    		
	   
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
				
    }
    
   @Test
    public void testphpBlogDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }
   @Test
    public void testDrupal6HelloWorldDatabase() throws InterruptedException, IOException, Exception{
			    	    		
	   
   	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }
   
   @Test
   public void testDrupal7HelloWorldDatabase() throws InterruptedException, IOException, Exception{
   	    		
	   
   	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
   }		
   
	
      @Test
    public void testDrupal7EshopDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
      	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("methodName = " + methodName);
			DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }
      @Test
    public void testWordPressDatabase() throws InterruptedException, IOException, Exception{
    	   		
    	  
      	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("methodName = " + methodName);
			DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }
    
      
        
          @Test
    public void testjavaWebServiceHelloWorldDatabase() throws InterruptedException, IOException, Exception{
    	    		
        	  
          	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
  			System.out.println("methodName = " + methodName);
  			DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }
    
    @Test
    public void testjavaWebServiceEshopDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }	
    

	
     
	   @Test
	    public void testNodeJsHelloWorldDatabase() throws InterruptedException, IOException, Exception{
	    	   		
		   
       	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("methodName = " + methodName);
			DeleteDbsql dbsql = new DeleteDbsql(methodName);
	    }
     
    @Test
    public void testNodeJsEshopDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }		
    
    
    @Test
    public void testjavaWebServiceEshopRunDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }	
    

	
     
	   @Test
	    public void testNodeJsHelloworldRunDatabase() throws InterruptedException, IOException, Exception{
	    	   		
		   
       	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("methodName = " + methodName);
			DeleteDbsql dbsql = new DeleteDbsql(methodName);
	    }
     
    @Test
    public void testNodeJsEshopRunDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }		
    
    @Test
    public void testphpHelloWorldCloneDatabase() throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }		
    
    
    @Test
    public void testEnvironmentDatabase()throws InterruptedException, IOException, Exception{
    	    		
    	  
    	methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("methodName = " + methodName);
		DeleteDbsql dbsql = new DeleteDbsql(methodName);
    }		
}





