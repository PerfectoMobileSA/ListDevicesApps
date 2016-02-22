/*
 * 
 */

package utility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.perfectomobile.httpclient.device.DeviceParameter;
import com.perfectomobile.httpclient.device.DeviceResult;

// TODO: Auto-generated Javadoc
/**
 * The Class Devices.
 */
public class DeviceApps {
	
	/** The device result. */
	DeviceResult deviceResult;
	
	/** The device id. */
	private String deviceId;
	
	/** The status. */
	private String status;
	
	/** The apps. */
	private List<String> apps;
	
	/** The apps num. */
	private int appsNum;
	
	/** The error flag. */
	private boolean errorFlag;
	
	/**
	 * Instantiates a new devices.
	 */
	public DeviceApps(){};
	
	/**
	 * Instantiates a new devices.
	 *
	 * @param deviceId the device id
	 * @param status the status
	 */
	public DeviceApps(String deviceId, String status) {
		this.deviceId = deviceId;
		this.status = status;
		this.apps = new LinkedList<>();
		this.appsNum=0;
		this.errorFlag = false;
		this.deviceResult = null;
	}
	
	/**
	 * Instantiates a new device apps.
	 *
	 * @param deviceResult the device result
	 */
	public DeviceApps(DeviceResult deviceResult) {
		this.deviceResult = deviceResult;
		this.deviceId = deviceResult.getResponseValue(DeviceParameter.DEVICE_ID);
		this.status = deviceResult.getResponseValue(DeviceParameter.STATUS);
		this.apps = new LinkedList<>();
		this.appsNum=0;
		this.errorFlag = false;
	}

	/**
	 * Gets the device id.
	 *
	 * @return the device id
	 */
	public String getDeviceId() {
		return this.deviceId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Adds the app.
	 *
	 * @param app the app
	 */
	public void addApp(String app){
		this.apps.add(app);
	}
	
	/**
	 * Gets the apps as list.
	 *
	 * @return the apps as list
	 */
	public List<String> getAppsAsList(){
		return this.apps;
	}
	
	/**
	 * Gets the apps.
	 *
	 * @return the apps
	 */
	public String getApps(){
		return this.apps.toString();
	}
	
	
	/**
	 * Gets the apps num.
	 *
	 * @return the apps num
	 */
	public int getAppsNum() {
		return appsNum;
	}
	
	/**
	 * Sets the apps num.
	 *
	 * @param appsNum the new apps num
	 */
	public void setAppsNum(int appsNum) {
		this.appsNum = appsNum;
	}
	
	/**
	 * Checks if is error flag.
	 *
	 * @return true, if is error flag
	 */
	public boolean isErrorFlag() {
		return errorFlag;
	}
	
	/**
	 * Sets the error flag.
	 *
	 * @param errorFlag the new error flag
	 */
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}
	
	/**
	 * Sets the apps.
	 *
	 * @param apps the new apps
	 */
	public void setApps(String apps){
		this.apps.clear();
		this.apps.add(apps);
	}
	
	/**
	 * Adds the apps.
	 *
	 * @param apps the apps
	 */
	public void addApps(String apps) {
		
		apps = apps.replace("[", "");
		apps = apps.replace("]", "");
		this.apps = Arrays.asList(apps.split(","));
		
		
	}
	
	
	
	
	
}
