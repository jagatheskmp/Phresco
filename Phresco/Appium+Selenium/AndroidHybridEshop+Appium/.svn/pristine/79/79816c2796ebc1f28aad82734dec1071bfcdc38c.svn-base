package com.photon.phresco.eshop;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.eshop.model.TestNgParameters;
import com.photon.phresco.eshop.screen.ImplementationScreen;

public class RegisterEshopTest {

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
	public void testRegister() {
		System.out.println("Executing Register Scenario");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			impScreen.register(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		@AfterClass
	public void tearDown() {

		impScreen.driverQuit();
	}

}
