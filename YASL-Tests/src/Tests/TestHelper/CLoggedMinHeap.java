package Tests.TestHelper;

import java.util.List;

import YASL.MinHeap.IMinHeap;
import YASL.MinHeap.IPrioritizedItem;

public class CLoggedMinHeap<T extends IPrioritizedItem<TKey>, TKey> //
    implements IMinHeap<T, TKey> //
{
	private final ITestLogger				_log;
	private final IMinHeap<T, TKey>	_heap;

	public CLoggedMinHeap(IMinHeap<T, TKey> heap, ITestLogger log) {
		this._heap = heap;
		this._log = log;
	}

	private void log(String method, String... extras) {
		_log.logIt(method, extras);
	}

	@Override
	public T poll() {
		final T value = _heap.poll();
		log("poll", value.toString());
		return value;
	}

	@Override
	public T lowest() {
		final T value = _heap.lowest();
		log("lowest", value.toString());
		return value;
	}

	@Override
	public void update(TKey key) {
		_heap.update(key);
		log("update", _heap.get(key).toString());
	}

	@Override
	public void add(T item) {
		log("add", item.toString());
		_heap.add(item);
	}

	@Override
	public T get(TKey key) {
		final T value = _heap.get(key);
		log("get", key.toString(), String.valueOf(value));
		return value;
	}

	@Override
	public String print() {
		return _heap.print();
	}

	@Override
	public List<T> items() {
		return _heap.items();
	}

	@Override
	public boolean isFull() {
		final boolean value = _heap.isFull();
		log("isFull", Boolean.toString(value));
		return value;
	}
}
