package com.photon.phresco.eshop;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.eshop.model.TestNgParameters;
import com.photon.phresco.eshop.screen.ImplementationScreen;

public class EshopTest {

	protected ImplementationScreen impScreen;
	protected String methodName;

	@BeforeClass(alwaysRun = true)
	@Parameters(value = { "device", "version", "platform", "package",
			"activity", "app", "serverip" })
	public void setup(String device, String version, String platform,
			String pckage, String activity, String app, String serverip) {
		TestNgParameters tngparam = new TestNgParameters();
		tngparam.getDevice().put("device", device);
		tngparam.getVersion().put("version", version);
		tngparam.getPlatform().put("platform", platform);
		tngparam.getPckage().put("pckage", pckage);
		tngparam.getActivity().put("activity", activity);
		tngparam.getApp().put("app", app);
		tngparam.getServerIp().put("serverIp", serverip);
		impScreen = new ImplementationScreen(tngparam);

	}

	@Test
	public void testLogin() {
		System.out.println("Executing Login Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.login(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegister() {
		System.out.println("Executing Register Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.register(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseTelevision() {
		System.out.println("Executing BrowseTelevision Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseTelevision(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseComputer() {
		System.out.println("Executing BrowseComputer Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseComputer(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseMobilePhone() {
		System.out.println("Executing BrowseMobilePhone Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseMobilePhone(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseAudioDevice() {
		System.out.println("Executing BrowseAudioDevice Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseAudioDevice(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseCamera() {
		System.out.println("Executing BrowseCamera Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseCamera(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseTablet() {
		System.out.println("Executing BrowseTablet Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseTablet(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseMovieandMusic() {
		System.out.println("Executing BrowseMovieandMusic Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseMovieAndMusic(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseVideoGames() {
		System.out.println("Executing BrowseVideoGames Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseVideoGames(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseMp3Players() {
		System.out.println("Executing BrowseMp3Players Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseMp3Players(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBrowseAccessories() {
		System.out.println("Executing BrowseAccessories Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.browseAccessories(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckout() {
		System.out.println("Executing Checkout Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.checkout(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void tearDown() {
		impScreen.driverQuit();
	}

}
