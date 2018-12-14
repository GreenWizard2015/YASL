package Tests;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import YASL.CEstimationFor;
import YASL.CEstimator;
import YASL.CExactCounter;
import YASL.IEstimator;
import YASL.IItemsCounter;
import YASL.Collectors.KTopCollector;
import YASL.Counters.CCMCounter;
import YASL.Counters.CCountMinParams;
import YASL.Hashing.ChgBasic;

public class Test_CountingStrings {
	private static String[] generateTestset(int N) {
		final String[] res = new String[N];
		for (int i = 0; i < N; i++) {
			res[i] = "String #" + i;
		}
		return res;
	}

	private IEstimator<String> makeEstimator(IItemsCounter<String> counter) {
		return new CEstimator<>(counter, new KTopCollector<>(Long.MAX_VALUE));
	}

	private Collection<CMergedEstimations> merge( //
	    IEstimator<String> first, //
	    IEstimator<String> second //
	) {
		HashMap<String, CMergedEstimations> res = new HashMap<>();
		Function<String, CMergedEstimations> For = x -> {
			CMergedEstimations me = res.get(x);
			if (null == me) {
				me = new CMergedEstimations(x);
				res.put(x, me);
			}
			return me;
		};

		for (CEstimationFor<String> item : first.estimate()) {
			For.apply(item.Item).first = item.Count;
		}
		for (CEstimationFor<String> item : second.estimate()) {
			For.apply(item.Item).second = item.Count;
		}
		return res.values();
	}

	@Test
	public void estimatedErrorInNormalRange() {
		IEstimator<String> exact = makeEstimator(new CExactCounter<>());
		IEstimator<String> CM = makeEstimator(new CCMCounter<String>( //
		    new CCountMinParams(0.01, 0.9), //
		    new ChgBasic<String>() //
		));

		int total = 0;
		String[] testset = generateTestset(1_000);
		for (int ittr = 0; ittr < 1; ittr++) {
			for (int i = 0; i < testset.length; i++) {
				exact.add(testset[i], 1 + i);
				CM.add(testset[i], 1 + i);
				total += 1 + i;
			}
		}

		int percent = total / 100;
		Collection<CMergedEstimations> estimations = merge(exact, CM);
		Assert.assertEquals(testset.length, estimations.size());
		for (CMergedEstimations res : estimations) {
			Assert.assertTrue( //
			    "Estimated value below exact.", //
			    res.first <= res.second //
			);
			Assert.assertTrue( //
			    "Estimated error more than 1%.", //
			    res.second <= res.first + percent //
			);
		}
	}
}

class CMergedEstimations {
	public long		first		= -1;
	public long		second	= -1;
	public String	Item;

	public CMergedEstimations(String item) {
		this.Item = item;
	}

}