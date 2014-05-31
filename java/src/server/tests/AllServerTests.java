package server.tests;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class AllServerTests {

	@Test
	public void test() {
		
	}
	
	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
				"server.tests.EndpointTests"
		
		};
		
		JUnitCore.main(testClasses);
	}

}
