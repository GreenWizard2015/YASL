package Tests;

import org.junit.Assert;
import org.junit.Test;

import YASL.CEstimator;
import YASL.CExactCollector;
import YASL.CExactCounter;
import YASL.IEstimator;

public class Test_Estimator {
	@Test
	public void simplest() {
		IEstimator<Integer> est = new CEstimator<Integer>( //
		    new CExactCounter<>(), //
		    new CExactCollector<>() //
		);
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < i; j++) {
				est.add(i);
			}
		}

		Assert.assertEquals( //
		    "4 -> 4.0, 3 -> 3.0, 2 -> 2.0, 1 -> 1.0", //
		    est.estimate().toString() //
		);
	}
}
