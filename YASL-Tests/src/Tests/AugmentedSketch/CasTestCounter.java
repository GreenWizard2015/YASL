package Tests.AugmentedSketch;

import Tests.TestHelper.CLoggedItemsCounter;
import Tests.TestHelper.CtlFiltered;
import Tests.TestHelper.CtlPrefixed;
import Tests.TestHelper.CtlVoid;
import Tests.TestHelper.ITestLogger;
import YASL.Counters.CExactCounter;

public class CasTestCounter extends CLoggedItemsCounter<Integer> {

	public CasTestCounter(ITestLogger log, String... methods) {
		super( //
		    new CExactCounter<Integer>(), //
		    new CtlFiltered(new CtlPrefixed(log, "cnt."), methods)//
		);
	}

	public CasTestCounter() {
		super(new CExactCounter<Integer>(), new CtlVoid());
	}
}
