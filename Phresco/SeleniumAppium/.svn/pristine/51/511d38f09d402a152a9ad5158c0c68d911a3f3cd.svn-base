package com.photon.phresco.mydevice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.mydevice.model.TestNgParameters;
import com.photon.phresco.mydevice.screen.ImplementationScreen;

public class MyDeviceTest {

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
	public void testMenuBarVerification() {
		System.out.println("Executing Menubar Verfication Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.menuBarVerification(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindSearchDpci() {
		System.out.println("Executing FindSearchDpci Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchDPCI(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindSearchPlu() {
		System.out.println("Executing FindSearchPlu Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchPlu(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindSearchUpc() {
		System.out.println("Executing FindSearchUpc Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchUpc(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindSearchKeyword() {
		System.out.println("Executing FindSearchKeyword Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchKeyword(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindSearchWomensCategory() {
		System.out.println("Executing FindSearchWomensCategory Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchWomensCategory(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindSearchMensCategory() {
		System.out.println("Executing FindSearchMensCategory Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchMensCategory(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindSearchElectronicsCategory() {
		System.out.println("Executing FindSearchElectronicsCategory Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchElectronicsCategory(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindSearchShoesCategory() {
		System.out.println("Executing FindSearchShoesCategory Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.searchShoesCategory(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLabelPrint() {
		System.out.println("Executing labelPrint Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.labelPrint(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testSingleSignItem() {
		System.out.println("Executing SingleSign Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.singleSignPrint(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testMultiSignItem() {
		System.out.println("Executing MultiSign Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.multiSignPrint(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testPlanoGram() {
		System.out.println("Executing Planogram Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.PlanogramSignPrint(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testReprintExistingSign() {
		System.out.println("Executing RepringExistingSign Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.rePrintExistingSign(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfill() {
		System.out.println("Executing fill Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.fill(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLogout() {
		System.out.println("Executing Logout Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.logout(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@AfterClass
	public void tearDown() {
		impScreen.driverQuit();
	}

}
