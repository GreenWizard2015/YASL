package Tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import YASL.Collectors.Heap.CFixedMinHeap;
import YASL.Collectors.Heap.CprSimple;

public class Test_CFixedMinHeap {

	@Test
	public void addSingleItem() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(3);
		queue.add(new CItem("test", 2));
		Assert.assertEquals( //
		    "test 2", //
		    queue.print() //
		);
	}

	@Test
	public void lowerPriorityBubbleUp() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		queue.add(new CItem("test", 2));
		queue.add(new CItem("lower", 1));
		Assert.assertTrue( //
		    queue.print(), //
		    queue.print().startsWith("lower 1") //
		);
		queue.add(new CItem("not lowest", 2));
		Assert.assertTrue( //
		    queue.print(), //
		    queue.print().startsWith("lower 1") //
		);

		queue.add(new CItem("lowest", 0));
		Assert.assertTrue( //
		    queue.print(), //
		    queue.print().startsWith("lowest 0") //
		);
	}

	@Test
	public void getByKey() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		for (int i = 0; i < 5; i++) {
			queue.add(new CItem("item" + i, i));
		}
		for (int i = 0; i < 5; i++) {
			Assert.assertEquals(i, queue.get("item" + i).Priority());
		}
	}

	@Test
	public void getNotExisted() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		Assert.assertEquals(null, queue.get("item"));
	}

	@Test
	public void returnLowest() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		for (int i = 5; 0 < i; i--) {
			queue.add(new CItem("item" + i, i));
			Assert.assertEquals(i, queue.lowest().Priority());
		}
	}

	@Test
	public void pollLowest() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		for (int i = 0; i < 5; i++)
			queue.add(new CItem("item" + i, i));

		for (int i = 1; i < 5; i++) {
			queue.poll();
			Assert.assertTrue( //
			    queue.print(), //
			    queue.print().startsWith("item" + i) //
			);
		}
	}

	@Test
	public void increasePriority() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		for (int i = 0; i < 5; i++) {
			queue.add(new CItem("item" + i, i));
		}
		final CItem item = queue.get("item3");
		item.setPriority(6); // top
		queue.update(item.value);

		Assert.assertEquals( //
		    "item0 0 | item1 1 | item2 2 | item3 6 | item4 4", //
		    queue.print() //
		);
	}

	@Test
	public void decreasePriority() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(5);
		for (int i = 0; i <= 4; i++) {
			queue.add(new CItem("item" + i, i));
		}
		final CItem item = queue.get("item3");
		item.setPriority(-1);
		queue.update(item.value);

		Assert.assertEquals(item.value, queue.lowest().value);
	}

	@Test
	public void pollMustEraseItem() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(4);
		queue.add(new CItem("item", 0));
		queue.poll();
		Assert.assertEquals(null, queue.get("item"));
	}

	@Test
	public void storeOnlyMaximalItem() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(1);
		for (int i = 0; i <= 5; i++) {
			queue.add(new CItem("item" + i, i));
		}
		Assert.assertEquals( //
		    "item5 5", //
		    queue.print() //
		);
	}

	@Test
	public void rejectItemSmallerThanLowest() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(2);
		queue.add(new CItem("A", 6));
		queue.add(new CItem("B", 7));
		for (int i = 0; i <= 5; i++)
			queue.add(new CItem("item" + i, i));

		Assert.assertEquals( //
		    "A 6 | B 7", //
		    queue.print() //
		);
	}

	@Test
	public void returnOrderedItems() {
		CFixedMinHeap<CItem, String> queue = new CFixedMinHeap<>(3);
		for (int i = 5; 0 < i; i--) {
			queue.add(new CItem("item" + i, i));
		}

		final List<String> items = queue.items().stream() //
		    .map(x -> x.value)//
		    .collect(Collectors.toList()); //

		Assert.assertEquals( //
		    Arrays.asList("item5", "item4", "item3"), //
		    items //
		);
	}
}

class CItem extends CprSimple<String> implements Comparable<CItem> {
	public CItem(String value, int priority) {
		super(value, priority);
	}

	public void setPriority(int i) {
		_priority = i;
	}

	@Override
	public String toString() {
		return value.toString() + " " + Priority();
	}

	@Override
	public int compareTo(CItem o) {
		final int diff = o._priority - _priority;
		if (0 != diff)
			return diff;
		return o.value.compareTo(value);
	}
}