package com.photon.phresco.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.photon.phresco.Screens.BaseScreen;
import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;


public class HelloWorldTestCase extends BaseScreen {
	
	private WebElement element;
	private PhrescoUiConstants phrsc;
	private UIConstants phr;
	private WelcomeScreen helloworld;
	private int SELENIUM_PORT;
	private String browserAppends;
	
	public static WebDriver driver;
	/*@Before
	public void setUp() {
	driver = new FirefoxDriver();
	}*/

	
	@Given("the user is on the widgets page")
	public void The_user_is_on_the_Widgets_page() {
	//driver.get("https://localhost:8080/yuimobilenone");
		try {

			phrsc = new PhrescoUiConstants();
			phr = new UIConstants();
			String serverURL = phrsc.PROTOCOL + "://"
					+ phrsc.HOST + ":"
					+ phrsc.PORT + "/";
			browserAppends = "*" + phrsc.BROWSER;
		//	assertNotNull("Browser name should not be null",browserAppends);
			SELENIUM_PORT = Integer.parseInt(phrsc.SERVER_PORT);
			//assertNotNull("selenium-port number should not be null",SELENIUM_PORT);
			helloworld=new WelcomeScreen(phrsc.SERVER_HOST, SELENIUM_PORT,
					browserAppends, serverURL, phrsc.SPEED,phrsc.CONTEXT );
		//	assertNotNull(helloworld);
			//MenuScreen menuObj = helloworld.menuScreen(phr);
			//assertNotNull(menuObj);
		} catch (Exception t) {
			t.printStackTrace();
			System.out.println("ScreenCaptured");
			}
			
		//helloworld.closeBrowser();
	}
	
	@Then("^the page title returned should be \"(.*)\"$")
	public void thePageTitleReturned(String expectedResults) {
		System.out.println("**********************"+expectedResults);
		isTextPresent(expectedResults);
		//System.out.println("************Title**********"+driver.getTitle());
	 //assertEquals(expectedResults, driver.getTitle());
	 
	 
	 helloworld.closeBrowser();
	}
	
}


