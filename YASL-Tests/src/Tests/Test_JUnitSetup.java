package Tests;

import org.junit.Assert;
import org.junit.Test;

public class Test_JUnitSetup {

	@Test
	public void develOn1_8() {
		final String vers = System.getProperty("java.version");
		Assert.assertTrue(vers.startsWith("1.8"));
	}

}
