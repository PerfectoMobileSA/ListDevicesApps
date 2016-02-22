package example;


import org.testng.annotations.Test;



import utility.BuildDevicesApps;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ExampleTest {
  
	@Test
  public void testDevicesApps() {
		
	   try {		
			new BuildDevicesApps();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
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
