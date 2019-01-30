package YASL.AugmentedSketch;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import YASL.CEstimatedItems;
import YASL.CEstimationFor;
import YASL.IItemsCounter;
import YASL.MinHeap.CBasicMinHeap;
import YASL.MinHeap.IMinHeap;
import YASL.Streams.TypedOutputStream;

public class CAugmentedSketch<T> implements IAugmentedSketch<T> {
	private final IMinHeap<CasItem<T>, T>	_heap;
	private final IItemsCounter<T>				_counter;

	public CAugmentedSketch(IMinHeap<CasItem<T>, T> heap, IItemsCounter<T> counter) {
		this._heap = heap;
		this._counter = counter;
	}

	/**
	 * @param sz
	 *          count of estimated items.
	 * @param counter
	 *          counter for low-frequency items.
	 */
	public CAugmentedSketch(int sz, IItemsCounter<T> counter) {
		this( //
		    new CBasicMinHeap<>(sz), //
		    counter //
		);
	}

	private long putToCounter(T item, long count) {
		if (!_heap.isFull()) {
			_heap.add(new CasItem<T>(item, count, 0));
			return count;
		}

		final long cnt = _counter.put(item, count);
		if (_heap.lowest().Priority() < cnt) {
			final CasItem<T> oldMin = _heap.poll();
			if (0 < oldMin.delta())
				_counter.put(oldMin.Value(), oldMin.delta());
			_heap.add(new CasItem<T>(item, cnt));
		}
		return cnt;
	}

	@Override
	public long putIfFrequent(T item, long count) {
		final CasItem<T> ratted = _heap.get(item);
		if (null != ratted) {
			ratted.inc(count);
			_heap.update(item);
			return ratted.Priority();
		}
		return 0;
	}

	@Override
	public long put(T item, long count) {
		final long heapCount = putIfFrequent(item, count);
		if (0 < heapCount)
			return heapCount;

		return putToCounter(item, count);
	}

	@Override
	public long count(T item) {
		final CasItem<T> ratted = _heap.get(item);
		if (null == ratted) {
			return _counter.count(item);
		} else {
			return ratted.Priority();
		}
	}

	@Override
	public void add(T item, long count) {
		put(item, count);
	}

	@Override
	public CEstimatedItems<T> estimate() {
		List<CEstimationFor<T>> est = _heap.items() //
		    .stream() //
		    .map(x -> new CEstimationFor<>(x.value, x.Priority())) //
		    .collect(Collectors.toList());
		return new CEstimatedItems<>(est);
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		throw new UnsupportedOperationException();
	}
}
