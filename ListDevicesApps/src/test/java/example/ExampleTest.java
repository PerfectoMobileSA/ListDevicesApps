package example;


import org.testng.annotations.Test;
import org.testng.annotations.Test;


import utility.BuildDevicesApps;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ExampleTest {
  //@Test
  /*public void testDevicesApps() {
	  new BuildDevicesApps();
  }*/
	@Test
  public void testDevicesApps() {
	    String remoteHost = System.getProperty("BAR");
	    System.getenv("BAR");
		if (remoteHost == null) remoteHost = "http://localhost:4444/wd/hub";
		
		System.out.println("HEEEELLLLLLLLLLLLLOOOOOOOOOOO"+remoteHost);
		System.out.println("HHHHHHHHHELLLLLLLL: "+System.getenv("BAR"));
		
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
