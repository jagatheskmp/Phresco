
package PackageTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class PackageEshop {

	private PhrescoUiConstants phrescoUiConstants;
	private WelcomeScreen welcomeScreen;
	@SuppressWarnings("unused")
	private String methodName;
	private UIConstants uiConstants;
	private UserInfoConstants userInfoConstants;

	@Parameters(value = { "browser", "platform" })
	@BeforeTest(alwaysRun=true)
	public void setUp(String browser, String platform) throws Exception {
		try {
			phrescoUiConstants = new PhrescoUiConstants();
			userInfoConstants = new UserInfoConstants();
			uiConstants = new UIConstants();
			String selectedBrowser = browser;
			String selectedPlatform = platform;

			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			String applicationURL = phrescoUiConstants.getProtocol() + "://"
			+ phrescoUiConstants.getHost() + ":"
			+ phrescoUiConstants.getPort() + "/";
			welcomeScreen = new WelcomeScreen(selectedBrowser,
					selectedPlatform, applicationURL,
					phrescoUiConstants.getContext(), userInfoConstants,
					uiConstants,  phrescoUiConstants);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void withoutGroupPackage() throws InterruptedException,
	IOException, Exception {
		try {
			System.out.println("*************withoutGroupPackage***********************");
			Assert.assertNotNull(welcomeScreen);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test
	public void verifyAudioDevicesPackage()
	throws InterruptedException, IOException, 
	Exception {
		try {

			System.out
			.println("--------- verifyAudioDevices()Package-------------");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();

			welcomeScreen.clickOnBrowse(methodName);
			welcomeScreen.AudioDevices(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}

	}

	@Test
	public void verifyCamerasPackage()
	throws InterruptedException, IOException, Exception {
		try {

			System.out
			.println("---------verifyCameras()Package-------------");
			welcomeScreen.clickOnBrowseTab(methodName);
			welcomeScreen.Cameras(methodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	@AfterTest(alwaysRun=true)
	public void tearDown() {
		welcomeScreen.closeBrowser();
	}
}
