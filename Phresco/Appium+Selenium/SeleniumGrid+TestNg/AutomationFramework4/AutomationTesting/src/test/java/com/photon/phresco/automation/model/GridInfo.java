/**
 * 
 */
package com.photon.phresco.automation.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bharatkumarradha
 *
 */
public class GridInfo {

	String hubURL;
	Map<String, HashMap<String, String>> nodeinfo;

	public String getHubURL() {
		return hubURL;
	}
	
	public void setHubURL(String hubURL) {
		this.hubURL = hubURL;
	}
	
	public Map<String, HashMap<String, String>> getNodeinfo() {
		return nodeinfo;
	}
	
	public void setNodeinfo(Map<String, HashMap<String, String>> nodeInfos) {
		this.nodeinfo = nodeInfos;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("hubURL:");
		sb.append(hubURL);
		sb.append(",NodeInfo:");
		sb.append(nodeinfo);
		return sb.toString();
	}
}
