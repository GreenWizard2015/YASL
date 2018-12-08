package YASL;

public interface IItemsCounter<T> {
	public long put(T item, long count);
}
