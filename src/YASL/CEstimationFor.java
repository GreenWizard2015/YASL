package YASL;

public class CEstimationFor<T> implements Comparable<CEstimationFor<T>> {
	public final T		Item;
	public final long	Count;

	public CEstimationFor(T item, long frequence) {
		Item = item;
		Count = frequence;
	}

	@Override
	public String toString() {
		return Item.toString() + " -> " + Count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(CEstimationFor<T> o) {
		final double delta = o.Count - Count;
		if (0 < delta)
			return 1;
		if (delta < 0)
			return -1;
		return -((Comparable<T>)Item).compareTo(o.Item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return ((CEstimationFor<T>)obj).Item.equals(Item);
	}

}
