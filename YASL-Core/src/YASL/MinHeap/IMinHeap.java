package YASL.MinHeap;

import java.util.List;

public interface IMinHeap<T extends IPrioritizedItem<TKey>, TKey> {
	public T poll();

	public T lowest();

	public void update(TKey key);

	public void add(T item);

	public T get(TKey key);

	public String print();

	public List<T> items();

	public boolean isFull();

}