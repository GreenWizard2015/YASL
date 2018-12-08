package YASL;

import java.util.HashMap;
import java.util.Map;

/*
 * Just for testing.
 * Don't use in real applications, unless you know what you are doing.
 */

public class CExactCounter<T> implements IItemsCounter<T> {

	private final Map<T, Long> _counters = new HashMap<>();

	@Override
	public long put(T item, long count) {
		Long cnt = _counters.get(item);
		cnt = count + ((null == cnt) ? 0 : cnt);
		_counters.put(item, cnt);
		return cnt;
	}
}
