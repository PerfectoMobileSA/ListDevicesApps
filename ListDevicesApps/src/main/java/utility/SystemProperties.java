
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
      
      /** The prop. */
      private static Properties prop;
      
      /**
       * Instantiates a new system properties.
       *
       * @throws FileNotFoundException the file not found exception
       * @throws IOException Signals that an I/O exception has occurred.
       */
      public SystemProperties() throws FileNotFoundException, IOException{
    	  prop = new Properties();
      	  prop.load(new FileInputStream("src/main/resources/config.properties"));
      	 
      }

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public static String getHost() {
		return prop.getProperty("host");
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public static String getUsername() {
		return prop.getProperty("username");
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public static String getPassword() {
		return prop.getProperty("password");
	}

	/**
	 * Gets the outputworkbook.
	 *
	 * @return the outputworkbook
	 */
	public static String getOutputworkbook() {
		return (prop.getProperty("outputWorkbook")).toString();
	}

	/**
	 * Gets the outputsheet.
	 *
	 * @return the outputsheet
	 */
	public static String getOutputsheet() {
		return prop.getProperty("outputSheet");
		
	}

	/**
	 * Gets the numberofthreads.
	 *
	 * @return the numberofthreads
	 */
	public static int getNumberofthreads() {
		return Integer.parseInt(prop.getProperty("numberOfThreads"));
		
	}

	/**
	 * Gets the devicemaxapps.
	 *
	 * @return the devicemaxapps
	 */
	public static int getDevicemaxapps() {
		return Integer.parseInt(prop.getProperty("deviceMaxApps"));
		
	}
	
	/**
	 * Checks if is output with timestamp.
	 *
	 * @return true, if is output with timestamp
	 */
	public static boolean isOutputWithTimestamp(){
		return Boolean.parseBoolean(prop.getProperty("outputWithTimestamp"));
	}
	
	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public static void setHost(String host) {
		prop.setProperty("host",host);
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public static void setUsername(String username) {
		prop.setProperty("username",username);
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public static void setPassword(String password) {
		prop.setProperty("password",password);
	}
	
      
      

   }