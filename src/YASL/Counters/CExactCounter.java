package YASL.Counters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import YASL.IItemsCounter;
import YASL.Streams.TypedOutputStream;

/*
 * Just for testing.
 * Don't use in real applications, unless you know what you are doing.
 */

public class CExactCounter<T> implements IItemsCounter<T> {

	private final Map<T, Long> _counters = new HashMap<>();

	@Override
	public long put(T item, long count) {
		long cnt = count + count(item);
		_counters.put(item, cnt);
		return cnt;
	}

	@Override
	public long count(T item) {
		Long cnt = _counters.get(item);
		return ((null == cnt) ? 0 : cnt);
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		throw new UnsupportedOperationException();
	}
}
