
package com.photon.phresco.Screens;

import java.io.IOException;

import com.photon.phresco.uiconstants.PhrescoUiConstants;
import com.photon.phresco.uiconstants.UIConstants;
import com.photon.phresco.uiconstants.UserInfoConstants;

public class PhotonAbstractScreen extends BaseScreen {

	public PhotonAbstractScreen() {

	}

	protected PhotonAbstractScreen(String selectedBrowser,String selectedPlatform, String applicationURL
			, String applicationContext,			
			UserInfoConstants userInfoConstants,UIConstants uiConstants, PhrescoUiConstants phrescoUiConstants)
			throws IOException, Exception {
		super(selectedBrowser,selectedPlatform, applicationURL, applicationContext,
				 userInfoConstants, uiConstants, phrescoUiConstants);

	}
}