package client.tests;

import org.junit.Test;



/**
 * This JUnit class implements all tests in one run.
 * @author scottdaly
 *
 */
public class AllClientTests {

	@Test
	public void test() {
		
	}
	
	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
				"client.tests.ProxyTest"
				
				
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}
