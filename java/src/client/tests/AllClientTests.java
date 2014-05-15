package client.tests;

import org.junit.Test;
import org.junit.runner.JUnitCore;



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
//				"client.tests.BankTest",
//                "client.tests.MapTest"

				
				
		};
		
		JUnitCore.runClasses(ProxyTest.class);
//		org.junit.runner.JUnitCore.main(testClasses);
	}

}
