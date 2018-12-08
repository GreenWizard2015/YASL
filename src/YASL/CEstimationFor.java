package YASL;

public class CEstimationFor<T> implements Comparable<CEstimationFor<T>> {
	public final T			Item;
	public final double	Frequence;

	public CEstimationFor(T item, double frequence) {
		Item = item;
		Frequence = frequence;
	}

	@Override
	public String toString() {
		return Item.toString() + " -> " + Frequence;
	}

	@Override
	public int compareTo(CEstimationFor<T> o) {
		final double delta = o.Frequence - Frequence;
		if (0 < delta)
			return 1;
		if (delta < 0)
			return -1;
		return 0;
	}

}
