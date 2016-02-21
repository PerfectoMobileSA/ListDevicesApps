package example;


import org.testng.annotations.Test;


import utility.BuildDevicesApps;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ExampleTest {
  @Test
  public void testDevicesApps(String host, String username, String pwd) {
	  new BuildDevicesApps();
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
