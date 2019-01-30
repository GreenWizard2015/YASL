package YASL.MinHeap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CBasicMinHeap<T extends IPrioritizedItem<TKey>, TKey> implements IMinHeap<T, TKey> {
	private final CbhItem<T>[]					_items;
	private final Map<TKey, CbhItem<T>>	_byValue;
	private int													_count	= 0;

	@SuppressWarnings("unchecked")
	public CBasicMinHeap(int size) {
		_items = (CbhItem[]) Array.newInstance(CbhItem.class, size);
		_byValue = new HashMap<>();
	}

	@Override
	public T poll() {
		final T res = lowest();
		_count--;
		swap(_items[_count], _items[0]);
		_items[_count] = null;
		_byValue.remove(res.Value());
		if (0 < _count)
			bubbleDown(_items[0]);
		return res;
	}

	@Override
	public T lowest() {
		return _items[0].Item;
	}

	@Override
	public void update(TKey key) {
		final CbhItem<T> item = _byValue.get(key);
		bubbleUp(item);
		bubbleDown(item);
	}

	@Override
	public void add(T item) {
		final CbhItem<T> node = new CbhItem<T>(item, _count);
		_items[_count] = node;
		_byValue.put(item.Value(), node);
		bubbleUp(node);
		_count++;
	}

	@Override
	public T get(TKey key) {
		final CbhItem<T> item = _byValue.get(key);
		return (null == item) ? null : item.Item;
	}

	@Override
	public String print() {
		final StringBuilder res = new StringBuilder();
		String sepp = "";
		for (int i = 0; i < _count; i++) {
			final CbhItem<T> item = _items[i];
			res.append(sepp);
			res.append(item.Item.toString());
			sepp = " | ";
		}
		return res.toString();
	}

	private void swap(CbhItem<T> first, CbhItem<T> second) {
		final int oldIndex = second.Index;
		second.Index = first.Index;
		first.Index = oldIndex;

		_items[first.Index] = first;
		_items[second.Index] = second;
	}

	private void bubbleDown(CbhItem<T> node) {
		while (true) {
			CbhItem<T> best = node;

			for (int childID = 0; childID < 2; childID++) {
				final int ind = (node.Index << 1) + 1 + childID;
				if (ind < _count) {
					final CbhItem<T> child = _items[ind];
					if (isBigger(best, child)) {
						best = child;
					}
				}
			}

			if (best.Index == node.Index)
				break;
			swap(best, node);
		}
	}

	private void bubbleUp(CbhItem<T> node) {
		while (0 < node.Index) {
			final CbhItem<T> parent = _items[(node.Index - 1) >> 1];
			if (isBigger(node, parent))
				break;
			swap(parent, node);
		}
	}

	private boolean isBigger(CbhItem<T> first, CbhItem<T> second) {
		final long diff = first.Item.Priority() - second.Item.Priority();
		return 0 < diff;
	}

	@Override
	public List<T> items() {
		List<T> res = new ArrayList<>(_count);
		for (int i = 0; i < _count; i++) {
			res.add(_items[i].Item);
		}
		res.sort(null);
		return res;
	}

	@Override
	public boolean isFull() {
		return _count == _items.length;
	}
}
