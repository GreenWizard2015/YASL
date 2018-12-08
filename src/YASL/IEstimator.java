package YASL;

public interface IEstimator<T> {
	public void add(T item, long count);
	public CEstimatedItems<T> estimate();

	public default void add(T item) {
		add(item, 1);
	}
}
