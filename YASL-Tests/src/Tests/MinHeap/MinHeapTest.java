package Tests.MinHeap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import YASL.MinHeap.IMinHeap;

public abstract class MinHeapTest {
	protected abstract IMinHeap<CTestItem, String> create(int sz);

	@Test
	public void addSingleItem() {
		final IMinHeap<CTestItem, String> heap = create(3);
		heap.add(new CTestItem("test", 2));
		Assert.assertEquals( //
		    "test 2", //
		    heap.print() //
		);
	}

	@Test
	public void lowerPriorityBubbleUp() {
		final IMinHeap<CTestItem, String> heap = create(5);
		heap.add(new CTestItem("test", 2));
		heap.add(new CTestItem("lower", 1));
		Assert.assertTrue( //
		    heap.print(), //
		    heap.print().startsWith("lower 1") //
		);
		heap.add(new CTestItem("not lowest", 2));
		Assert.assertTrue( //
		    heap.print(), //
		    heap.print().startsWith("lower 1") //
		);

		heap.add(new CTestItem("lowest", 0));
		Assert.assertTrue( //
		    heap.print(), //
		    heap.print().startsWith("lowest 0") //
		);
	}

	@Test
	public void getByKey() {
		final IMinHeap<CTestItem, String> heap = create(5);
		for (int i = 0; i < 5; i++) {
			heap.add(new CTestItem("item" + i, i));
		}
		for (int i = 0; i < 5; i++) {
			Assert.assertEquals(i, heap.get("item" + i).Priority());
		}
	}

	@Test
	public void getNotExisted() {
		final IMinHeap<CTestItem, String> heap = create(5);
		Assert.assertEquals(null, heap.get("item"));
	}

	@Test
	public void returnLowest() {
		final IMinHeap<CTestItem, String> heap = create(5);
		for (int i = 5; 0 < i; i--) {
			heap.add(new CTestItem("item" + i, i));
			Assert.assertEquals(i, heap.lowest().Priority());
		}
	}

	@Test
	public void pollLowest() {
		final IMinHeap<CTestItem, String> heap = create(5);
		for (int i = 0; i < 5; i++)
			heap.add(new CTestItem("item" + i, i));

		for (int i = 1; i < 5; i++) {
			heap.poll();
			Assert.assertTrue( //
			    heap.print(), //
			    heap.print().startsWith("item" + i) //
			);
		}
	}

	@Test
	public void increasePriority() {
		final IMinHeap<CTestItem, String> heap = create(5);
		for (int i = 0; i < 5; i++) {
			heap.add(new CTestItem("item" + i, i));
		}
		final CTestItem item = heap.get("item3");
		item.setPriority(6); // top
		heap.update(item.value);

		Assert.assertEquals( //
		    "item0 0 | item1 1 | item2 2 | item3 6 | item4 4", //
		    heap.print() //
		);
	}

	@Test
	public void decreasePriority() {
		final IMinHeap<CTestItem, String> heap = create(5);
		for (int i = 0; i <= 4; i++) {
			heap.add(new CTestItem("item" + i, i));
		}
		final CTestItem item = heap.get("item3");
		item.setPriority(-1);
		heap.update(item.value);

		Assert.assertEquals(item.value, heap.lowest().value);
	}

	@Test
	public void pollMustEraseItem() {
		final IMinHeap<CTestItem, String> heap = create(4);
		heap.add(new CTestItem("item", 0));
		heap.poll();
		Assert.assertEquals(null, heap.get("item"));
	}

	@Test
	public void returnOrderedItems() {
		final IMinHeap<CTestItem, String> heap = create(5);
		final List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
		Collections.shuffle(values);
		for (Integer i : values) {
			heap.add(new CTestItem("item" + i, i));
		}

		final List<String> items = heap.items().stream() //
		    .map(x -> x.value)//
		    .collect(Collectors.toList()); //

		Assert.assertEquals( //
		    Arrays.asList("item5", "item4", "item3", "item2", "item1"), //
		    items //
		);
	}
}
