package Tests.AugmentedSketch;

import Tests.TestHelper.CLoggedMinHeap;
import Tests.TestHelper.CtlFiltered;
import Tests.TestHelper.CtlPrefixed;
import Tests.TestHelper.CtlVoid;
import Tests.TestHelper.ITestLogger;
import YASL.AugmentedSketch.CasItem;
import YASL.MinHeap.CBasicMinHeap;

public class CasTestHeap //
    extends CLoggedMinHeap<CasItem<Integer>, Integer> //
{

	protected CasTestHeap(int sz, ITestLogger log) {
		super( //
		    new CBasicMinHeap<CasItem<Integer>, Integer>(sz), //
		    log //
		);
	}

	public CasTestHeap(ITestLogger log, int sz, String... methods) {
		this( //
		    sz, //
		    new CtlFiltered(new CtlPrefixed(log, "heap."), methods) //
		);
	}

	public CasTestHeap(int sz) {
		this(sz, new CtlVoid());
	}
}
