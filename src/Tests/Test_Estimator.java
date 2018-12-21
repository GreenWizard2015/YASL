package Tests;

import org.junit.Assert;
import org.junit.Test;

import YASL.CEstimator;
import YASL.IEstimator;
import YASL.Collectors.CExactCollector;
import YASL.Counters.CExactCounter;

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
		    "4 -> 4, 3 -> 3, 2 -> 2, 1 -> 1", //
		    est.estimate().toString() //
		);
	}
}
