package YASL.Collectors;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import YASL.CEstimatedItems;
import YASL.CEstimationFor;
import YASL.IEstimationCollector;
import YASL.Collectors.Heap.CFixedMinHeap;
import YASL.Collectors.Heap.CprSimple;
import YASL.Streams.TypedOutputStream;

public class KTopCollector<T> implements IEstimationCollector<T> {
	private static class CTopItem<T> extends CprSimple<T> {
		public CTopItem(T value, long priority) {
			super(value, (int) priority);
		}

		public void update(long cnt) {
			_priority = (int) cnt;
		}
	}

	private final CFixedMinHeap<CTopItem<T>, T> _heap;

	public KTopCollector(int size) {
		this._heap = new CFixedMinHeap<>(size);
	}

	@Override
	public void put(T item, long cnt) {
		final CTopItem<T> ratted = _heap.get(item);
		if (null == ratted) {
			_heap.add(new CTopItem<T>(item, cnt));
		} else {
			ratted.update(cnt);
			_heap.update(item);
		}
	}

	private List<CEstimationFor<T>> items() {
		return _heap.items() //
		    .stream() //
		    .map(x -> new CEstimationFor<>(x.value, x.Priority())) //
		    .collect(Collectors.toList());
	}

	@Override
	public CEstimatedItems<T> collect() {
		return new CEstimatedItems<>(items());
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		List<CEstimationFor<T>> items = items();
		stream.writeInt(items.size());
		for (CEstimationFor<T> item : items) {
			stream.writeType(item.Item);
			stream.writeLong(item.Count);
		}
	}
}
