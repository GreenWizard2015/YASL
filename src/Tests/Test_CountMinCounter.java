package Tests;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import YASL.CExactCounter;
import YASL.IItemsCounter;
import YASL.Counters.CCMCounter;
import YASL.Counters.CCountMinParams;
import YASL.Hashing.ChgBasic;

public class Test_CountMinCounter {
	@Test
	public void notLessThanExact() {
		final int variations = 10000;
		final Random r = new Random(0);
		final IItemsCounter<Long> exact = new CExactCounter<>();
		final IItemsCounter<Long> MC = new CCMCounter<Long>( //
		    new CCountMinParams(0.01, 0.01), //
		    new ChgBasic<>() //
		);

		for (long i = 0; i < 100_000; i++) {
			final long value = r.nextInt(variations);
			final int cnt = 1 + r.nextInt(10);
			final long exactCount = exact.put(value, cnt);
			final long minCount = MC.put(value, cnt);
			Assert.assertTrue(exactCount <= minCount);
		}
	}
}