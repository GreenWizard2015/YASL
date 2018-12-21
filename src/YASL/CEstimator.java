package YASL;

import java.io.IOException;

import YASL.Streams.TypedOutputStream;

public class CEstimator<T> implements IEstimator<T> {
	private final IItemsCounter<T> _counter;
	private final IEstimationCollector<T> _collector;

	public CEstimator( //
	    IItemsCounter<T> counter, //
	    IEstimationCollector<T> est //
	) {
		this._counter = counter;
		this._collector = est;
	}

	@Override
	public void add(T item, long count) {
		final long cnt = _counter.put(item, count);
		_collector.put(item, cnt);
	}

	@Override
	public CEstimatedItems<T> estimate() {
		return _collector.collect();
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		_counter.store(stream);
		_collector.store(stream);
	}
}
