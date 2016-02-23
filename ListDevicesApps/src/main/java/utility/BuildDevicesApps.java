/*
 * 
 */

package utility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.perfectomobile.httpclient.Credentials;
import com.perfectomobile.httpclient.HttpClientException;
import com.perfectomobile.httpclient.ParameterValue;
import com.perfectomobile.httpclient.device.DeviceResult;
import com.perfectomobile.httpclient.device.DevicesHttpClient;


// TODO: Auto-generated Javadoc
/**
 * The Class BuildDevicesApps.
 */
public final class BuildDevicesApps {
    
    /** The devices list. */
    public static List<DeviceApps> devicesList;
    
    /**  the Devices Result List. */
    public static List<DeviceResult> deviceResultList;
    
    /** The excel utils. */
    public ExcelUtils excelUtils;
    
    /** The host. */
    private String host;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The properties. */
    SystemProperties properties;
    
  
   
    
    /**
     * Instantiates a new builds the devices apps.
     *
     * @throws FileNotFoundException the file not found exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public BuildDevicesApps() throws FileNotFoundException, IOException  {
    this.properties = new SystemProperties();
    
    this.host = System.getenv("HOST");
	this.username = System.getenv("USERNAME");
	this.password = System.getenv("PASSWORD");
	
	//define if we got environment variable, then work with them
	if (host!=null && username!=null && password!=null){
		SystemProperties.setHost(host);
		SystemProperties.setUsername(username);
		SystemProperties.setPassword(password);
	} else {//work only with config file parameters
		this.host = SystemProperties.getHost();
    	this.username = SystemProperties.getUsername();
    	this.password = SystemProperties.getPassword();
	}
    	
	ExcelUtils excelUtils =  new ExcelUtils(properties);
	
	 //build list of devices
	buildListOfDevices();
	//build list of applications for each device
	buildListOfApps();
     //write results to Excel:
	excelUtils.writeToExcel(devicesList);
	
    	
	}
    
	/**
	 * Builds the list of apps.
	 */
	@SuppressWarnings("unchecked")
	private void buildListOfApps() {
		
		ExecutorService pool = Executors.newFixedThreadPool(SystemProperties.getNumberofthreads());
	    Set<Future<DeviceApps>> setDevice = new HashSet<Future<DeviceApps>>();
	    
	    try {
			
		    for (DeviceApps device : devicesList) { 
		    	
		      Callable<DeviceApps> callable = new DeviceAppsUtil(device);
		      Future<DeviceApps> future = pool.submit(callable);
		      setDevice.add(future);
		      
		     /* if (++count==50)
		    	  break;*/
		    }
		    
		    devicesList.clear();
		    for (Future<DeviceApps> future : setDevice) {
		    	devicesList.add(future.get());
	        
		    }
		} catch (Exception e) {
			System.out.println("Failed to build list of applications " + e.getMessage());
		}
		
	}
	
	
	/**
	 * Builds list of devices.
	 */
	public void buildListOfDevices() {
		
		devicesList = new LinkedList<DeviceApps>();
		 Credentials credentials = new Credentials(username,password);
		 DevicesHttpClient client = new DevicesHttpClient(host, credentials);
        List inputParameters = new LinkedList<>();
        ParameterValue param = new ParameterValue("availableTo", username);
        inputParameters.add(param);
       
        deviceResultList = null;
        try {
        	deviceResultList = client.listDevices(inputParameters, false);
        	for (DeviceResult device : deviceResultList) {
        		devicesList.add(new DeviceApps(device));
                System.out.println(device);
          }
        } catch (HttpClientException e) {
        	e.printStackTrace();
        }
        
    }
	
    
    /**
     * The Class DeviceAppsUtil.
     */
    //inner class:
    @SuppressWarnings("rawtypes")
	public class DeviceAppsUtil implements Callable {
        
        /** The driver. */
        private RemoteWebDriver driver;
        
        /** The device. */
        private DeviceApps device;
       
        /**
         * Gets the device.
         *
         * @return the device
         */
        public DeviceApps getDevice(){
        	return this.device;
        }
       
        /**
         * Instantiates a new device apps util.
         *
         * @param device the device
         */
        public DeviceAppsUtil(DeviceApps device) {
           this.device = device;
           
        }
        
        /* (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @SuppressWarnings("finally")
		public DeviceApps call() {  
        	Map<String, Object> params;
        	String apps = null;
               try {
            	   if (this.device.getStatus().toLowerCase().equals("connected")){
            		try {
						this.driver = new RemoteWebDriver(getURL(), getCapabilities(this.device.getDeviceId()));
						params = new HashMap<>();
       					params.put("format", "name");
       					try {
       						apps =  (String) driver.executeScript("mobile:application:find", params);
       						this.device.addApps(apps);
       						this.device.setAppsNum(this.device.getAppsAsList().size());
       						
    					} catch (Exception e) {
    						//if (apps==null){
    							this.device.setApps(e.getMessage());
    						//}
    						//else {
    						//	this.device.setApps(apps);
    						//}
    						this.device.setAppsNum(0);
    						this.device.setErrorFlag(true);
    					}
       					
       					this.driver.close();
						
					} catch (Exception e) {
						this.device.setApps(e.getMessage());
       					this.device.setErrorFlag(true);
					}
       				
       				
       				
       				
       				
       					
       					
       					
 	
       					
       				
       			}
            	   
               }      
               catch (Exception e) {
                      e.printStackTrace();
               } finally {
            	   return device;
                      //this.driver.quit();
               }
        }
        
       
        /**
         * Gets the URL.
         *
         * @return the URL
         */
        private URL getURL(){
        	try {
        		
    	       
    	        return new URL("https://" + host + "/nexperience/perfectomobile/wd/hub");
    		} catch (Exception e) {
    			return null;
    		}
            
        }

        /**
         * Gets the capabilities.
         *
         * @param deviceId the device id
         * @return the capabilities
         */
        private DesiredCapabilities getCapabilities(String deviceId){
        	try {
        		
    	        String browserName = "";
    	        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
    	        capabilities.setCapability("user", username);
    	        capabilities.setCapability("password", password);
    	        capabilities.setCapability("deviceName", deviceId);
    	
            	return capabilities;
    		} catch (Exception e) {
    			return null;
    		}
            
        }
       
    }  
   
}