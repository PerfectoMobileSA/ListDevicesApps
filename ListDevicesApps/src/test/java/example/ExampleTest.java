package example;


import org.testng.annotations.Test;


import utility.BuildDevicesApps;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class ExampleTest {
   @Test
  // @Parameters({"host","username","password"})	
  public void testDevicesApps() {
	   System.out.println(System.getProperty("host"));

	  new BuildDevicesApps(System.getProperty("host"),System.getProperty("username"),System.getProperty("password"));
  }
  @BeforeTest
  public void beforeTest() {
	 System.out.println( "Collecting devices apps on cloud");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("End of Execution");
  }

}
