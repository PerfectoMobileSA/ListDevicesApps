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
		String host = System.getenv("HOST");
		String username = System.getenv("USERNAME");
		String password = System.getenv("PASSWORD");
	    
		System.out.println("XXXXXXXXXXXXXXXXXXX"+host );
		System.out.println("XXXXXXXXXXXXXXXXXXX"+username );
		System.out.println("XXXXXXXXXXXXXXXXXXX"+password );
		if (password==null)
			System.out.println("HELLLLLLLOOOOOOOOO");
		
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
