package Tests.MinHeap;

import org.junit.Assert;
import org.junit.Test;

import YASL.MinHeap.CFixedMinHeap;
import YASL.MinHeap.IMinHeap;

public class Test_FixedMinHeap extends MinHeapTest {

	@Override
	protected IMinHeap<CTestItem, String> create(int sz) {
		return new CFixedMinHeap<>(sz);
	}

	@Test
	public void storeOnlyMaximalItem() {
		final IMinHeap<CTestItem, String> heap = create(1);
		for (int i = 0; i <= 5; i++) {
			heap.add(new CTestItem("item" + i, i));
		}
		Assert.assertEquals( //
		    "item5 5", //
		    heap.print() //
		);
	}

	@Test
	public void rejectItemSmallerThanLowest() {
		final IMinHeap<CTestItem, String> heap = create(2);
		heap.add(new CTestItem("A", 6));
		heap.add(new CTestItem("B", 7));
		for (int i = 0; i <= 5; i++)
			heap.add(new CTestItem("item" + i, i));

		Assert.assertEquals( //
		    "A 6 | B 7", //
		    heap.print() //
		);
	}
}