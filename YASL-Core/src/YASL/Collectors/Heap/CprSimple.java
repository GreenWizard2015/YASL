package YASL.Collectors.Heap;

public class CprSimple<T> implements IPrioritizedItem<T>, Comparable<CprSimple<T>> {
	public final T	value;
	protected int		_priority;

	public CprSimple(T value, int priority) {
		this.value = value;
		_priority = priority;
	}

	@Override
	public int Priority() {
		return _priority;
	}

	@Override
	public T Value() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(CprSimple<T> o) {
		final int diff = o._priority - _priority;
		if (0 != diff)
			return diff;
		return ((Comparable<T>) o.value).compareTo(value);
	}
}