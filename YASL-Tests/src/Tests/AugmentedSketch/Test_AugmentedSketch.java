package Tests.AugmentedSketch;

import org.junit.Assert;
import org.junit.Test;

import Tests.TestHelper.CtlBasic;
import Tests.TestHelper.ITestLogger;
import YASL.IEstimator;
import YASL.IItemsCounter;
import YASL.AugmentedSketch.CAugmentedSketch;
import YASL.AugmentedSketch.IAugmentedSketch;

public class Test_AugmentedSketch {
	@Test
	public void putItemFirstlyIntoHeap() {
		final ITestLogger log = new CtlBasic();
		final IEstimator<Integer> est = new CAugmentedSketch<Integer>( //
		    new CasTestHeap(log, 2, "add"), //
		    null //
		);

		est.add(123);
		est.add(321);

		Assert.assertEquals( //
		    "" + //
		        "heap.add(CasItem(p:1, d:1, v:123)) | " + //
		        "heap.add(CasItem(p:1, d:1, v:321))", //
		    log.log() //
		);
	}

	@Test
	public void putItemIntoCounter() {
		final ITestLogger log = new CtlBasic();
		final IEstimator<Integer> est = new CAugmentedSketch<Integer>( //
		    new CasTestHeap(log, 1, "add"), //
		    new CasTestCounter(log) //
		);

		est.add(123);
		est.add(321);

		Assert.assertEquals( //
		    "heap.add(CasItem(p:1, d:1, v:123)) | cnt.put(321, 1, 1)", //
		    log.log() //
		);
	}

	@Test
	public void updateHeap() {
		final ITestLogger log = new CtlBasic();
		final IEstimator<Integer> est = new CAugmentedSketch<Integer>( //
		    new CasTestHeap(log, 1, "add", "update"), //
		    new CasTestCounter(log) //
		);

		est.add(123);
		est.add(321);
		est.add(123);

		Assert.assertEquals( //
		    "" + //
		        "heap.add(CasItem(p:1, d:1, v:123)) | " + //
		        "cnt.put(321, 1, 1) | " + //
		        "heap.update(CasItem(p:2, d:2, v:123))", //
		    log.log() //
		);
	}

	@Test
	public void updateCounterByDelta() {
		final ITestLogger log = new CtlBasic();
		final IEstimator<Integer> est = new CAugmentedSketch<Integer>( //
		    new CasTestHeap(log, 1, "add", "update"), //
		    new CasTestCounter(log) //
		);

		est.add(123);
		est.add(321);
		est.add(123, 5);
		est.add(321, 8);

		Assert.assertEquals( //
		    "" + //
		        "heap.add(CasItem(p:1, d:1, v:123)) | " + //
		        "cnt.put(321, 1, 1) | " + //
		        "heap.update(CasItem(p:6, d:6, v:123)) | " + //
		        "cnt.put(321, 8, 9) | cnt.put(123, 6, 6) | " + //
		        "heap.add(CasItem(p:9, d:0, v:321))", //
		    log.log() //
		);
	}

	@Test
	public void counting() {
		final IItemsCounter<Integer> est = new CAugmentedSketch<Integer>( //
		    new CasTestHeap(1), //
		    new CasTestCounter() //
		);

		est.put(123, 1);
		est.put(321, 1);

		Assert.assertEquals(1, est.count(123));
		Assert.assertEquals(1, est.count(321));

		est.put(123, 5);
		est.put(321, 8);

		Assert.assertEquals(1 + 5, est.count(123));
		Assert.assertEquals(1 + 8, est.count(321));
	}

	@Test
	public void PIF_IfHeapNotFull() {
		final IAugmentedSketch<Integer> est = //
		    new CAugmentedSketch<Integer>( //
		        new CasTestHeap(1), //
		        new CasTestCounter() //
				);

		est.putIfFrequent(123, 1);
		Assert.assertEquals(1, est.count(123));
	}
}