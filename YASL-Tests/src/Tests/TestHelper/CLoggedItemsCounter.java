package Tests.TestHelper;

import java.io.IOException;

import YASL.IItemsCounter;
import YASL.Streams.TypedOutputStream;

public class CLoggedItemsCounter<T> implements IItemsCounter<T> {
	private final IItemsCounter<T>	_counter;
	private final ITestLogger				_log;

	public CLoggedItemsCounter(IItemsCounter<T> counter, ITestLogger log) {
		this._counter = counter;
		this._log = log;
	}

	@Override
	public long put(T item, long count) {
		final long value = _counter.put(item, count);
		_log.log("put", item, count, value);
		return value;
	}

	@Override
	public long count(T item) {
		final long value = _counter.count(item);
		_log.log("count", item, value);
		return value;
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		throw new UnsupportedOperationException();
	}
}
