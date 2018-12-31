package YASL.Collectors.Heap;

public class CprSimple<T> implements IPrioritizedItem<T> {
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
}