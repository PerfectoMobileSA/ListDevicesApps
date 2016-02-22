package example;


import org.testng.annotations.Test;



import utility.BuildDevicesApps;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

// TODO: Auto-generated Javadoc
/**
 * The Class ExampleTest.
 */
public class ExampleTest {
  
	/**
	 * Test devices apps.
	 */
	@Test
  public void testDevicesApps() {
		
	   try {		
			new BuildDevicesApps();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		}
		
		
  }
  
  /**
   * Before test.
   */
  @BeforeTest
  public void beforeTest() {
	 System.out.println( "Collecting devices apps on cloud");
  }

  /**
   * After test.
   */
  @AfterTest
  public void afterTest() {
	  System.out.println("End of Execution");
  }

}
