package YASL.MinHeap;

public class CpiSimple<T> implements IPrioritizedItem<T>, Comparable<CpiSimple<T>> {
	public final T	value;
	protected long	_priority;

	public CpiSimple(T value, long priority) {
		this.value = value;
		_priority = priority;
	}

	@Override
	public long Priority() {
		return _priority;
	}

	@Override
	public T Value() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(CpiSimple<T> o) {
		final long diff = o._priority - _priority;
		if (0 != diff)
			return Long.signum(diff);
		return ((Comparable<T>) o.value).compareTo(value);
	}
}