package Tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;

import YASL.IEstimationCollector;
import YASL.Collectors.KTopCollector;
import YASL.Collectors.StoredKTop;
import YASL.Streams.CtisInteger;
import YASL.Streams.CtosInteger;

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

	@Test
	public void loading() throws Exception {
		final IEstimationCollector<Integer> collectorA = new KTopCollector<>(3);
		collectorA.put(3, 4);
		collectorA.put(2, 3);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		collectorA.store(new CtosInteger(stream));

		final IEstimationCollector<Integer> collectorB = (new StoredKTop<Integer>( //
		    3 //
		)).load( //
		    new CtisInteger( //
		        new ByteArrayInputStream(stream.toByteArray()) //
				) //
		);

		Assert.assertEquals( //
		    collectorA.collect().toString(), //
		    collectorB.collect().toString() //
		);
	}
}