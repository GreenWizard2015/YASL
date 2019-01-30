package YASL.MinHeap;

public class CFixedMinHeap<T extends IPrioritizedItem<TKey>, TKey> //
    extends CBasicMinHeap<T, TKey> //
{
	public CFixedMinHeap(int size) {
		super(size);
	}

	@Override
	public void add(T item) {
		if (isFull()) {
			if (item.Priority() < lowest().Priority())
				return;
			poll();
		}
		super.add(item);
	}
}
