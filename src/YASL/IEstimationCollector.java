package YASL;

public interface IEstimationCollector<T> {
	public void put(T item, long cnt);
	public CEstimatedItems<T> collect();
}
