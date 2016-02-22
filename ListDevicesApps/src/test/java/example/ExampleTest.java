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
	   try {
			 
		if (host!=null && username!=null && password!=null){
			new BuildDevicesApps(host,username,password);
		} else{
			new BuildDevicesApps();
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
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
