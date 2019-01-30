package YASL.MinHeap;

class CbhItem<T> {
	public final T	Item;
	public int			Index;

	public CbhItem(T item, int index) {
		Item = item;
		Index = index;
	}
}