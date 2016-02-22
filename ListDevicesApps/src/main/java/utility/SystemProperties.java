
package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
    * The Class SystemProperties.
    */
   public class SystemProperties {
      
      private static Properties prop;
      
      public SystemProperties() throws FileNotFoundException, IOException{
    	  prop = new Properties();
      	  prop.load(new FileInputStream("src/main/resources/config.properties"));
      	 
      }

	public static String getHost() {
		return prop.getProperty("host");
	}

	public static String getUsername() {
		return prop.getProperty("username");
	}

	public static String getPassword() {
		return prop.getProperty("password");
	}

	public static String getOutputworkbook() {
		return (prop.getProperty("outputWorkbook")).toString();
	}

	public static String getOutputsheet() {
		return prop.getProperty("outputSheet");
		
	}

	public static int getNumberofthreads() {
		return Integer.parseInt(prop.getProperty("numberOfThreads"));
		
	}

	public static int getDevicemaxapps() {
		return Integer.parseInt(prop.getProperty("deviceMaxApps"));
		
	}
	
	public static boolean isOutputWithTimestamp(){
		return Boolean.parseBoolean(prop.getProperty("outputWithTimestamp"));
	}
	
	public static void setHost(String host) {
		prop.setProperty("host",host);
	}

	public static void setUsername(String username) {
		prop.setProperty("username",username);
	}

	public static void setPassword(String password) {
		prop.setProperty("password",password);
	}
	
      
      

   }