package YASL;

public class CEstimationFor<T> implements Comparable<CEstimationFor<T>> {
	public final T		Item;
	public final long	Count;

	public CEstimationFor(T item, long count) {
		Item = item;
		Count = count;
	}

	@Override
	public String toString() {
		return Item.toString() + " -> " + Count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(CEstimationFor<T> o) {
		final long delta = o.Count - Count;
		if (0 != delta)
			return (int) delta;
		return -((Comparable<T>) Item).compareTo(o.Item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return ((CEstimationFor<T>) obj).Item.equals(Item);
	}

}
