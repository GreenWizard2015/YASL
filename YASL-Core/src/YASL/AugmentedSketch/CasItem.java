package YASL.AugmentedSketch;

import YASL.MinHeap.CpiSimple;

public class CasItem<T> extends CpiSimple<T> {
	public final long startPriority;

	public CasItem(T value, long priority, long startPriority) {
		super(value, priority);
		this.startPriority = startPriority;
	}

	public CasItem(T item, long count) {
		this(item, count, count);
	}

	public void update(long cnt) {
		_priority = cnt;
	}

	public long delta() {
		return _priority - startPriority;
	}

	public void inc(long count) {
		_priority += count;
	}

	@Override
	public String toString() {
		return String.format( //
		    "CasItem(p:%d, d:%d, v:%s)", //
		    Priority(), delta(), String.valueOf(Value()) //
		);
	}
}