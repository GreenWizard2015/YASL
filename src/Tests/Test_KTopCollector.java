package Tests;

import org.junit.Assert;
import org.junit.Test;

import YASL.IEstimationCollector;
import YASL.Collectors.KTopCollector;

public class Test_KTopCollector {

	@Test
	public void simplest() {
		IEstimationCollector<Integer> collector = new KTopCollector<>(2);
		for (int i = 1; i <= 4; i++) {
			collector.put(i, i);
		}

		Assert.assertEquals( //
		    "4 -> 4, 3 -> 3", //
		    collector.collect().toString() //
		);
	}

	@Test
	public void sameCounts() {
		IEstimationCollector<Integer> collector = new KTopCollector<>(3);
		collector.put(2, 3);
		collector.put(3, 4);
		collector.put(4, 4);
		collector.put(5, 5);

		Assert.assertEquals( //
		    "5 -> 5, 4 -> 4, 3 -> 4", //
		    collector.collect().toString() //
		);
	}
}