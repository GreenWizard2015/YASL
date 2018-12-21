package YASL.Collectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import YASL.CEstimatedItems;
import YASL.CEstimationFor;
import YASL.IEstimationCollector;
import YASL.Streams.TypedOutputStream;

public class KTopCollector<T> implements IEstimationCollector<T> {
	private final int												_K;
	private long														_min	= 0L;
	private final List<CEstimationFor<T>>		_items;
	private final Map<T, CEstimationFor<T>>	_byValue;

	public KTopCollector( //
	    int _K, //
	    List<CEstimationFor<T>> _items, //
	    Map<T, CEstimationFor<T>> _byValue //
	) {
		this._K = _K;
		this._items = _items;
		this._byValue = _byValue;
	}

	public KTopCollector(int K) {
		this(K, new ArrayList<>(), new HashMap<>());
	}

	@Override
	public void put(T item, long cnt) {
		if (cnt < _min)
			return;

		CEstimationFor<T> est = _byValue.get(item);
		if (null != est) {
			_items.remove(Collections.binarySearch(_items, est));
		}

		est = new CEstimationFor<T>(item, cnt);
		_byValue.put(item, est);
		int ind = Collections.binarySearch(_items, est);
		_items.add(-ind - 1, est);

		if ((_K * 1.5) < _items.size())
			fitK();
	}

	private void fitK() {
		while (_K < _items.size()) {
			_byValue.remove(_items.remove(_items.size() - 1).Item);
		}

		if (_items.size() == _K)
			_min = _items.get(_K - 1).Count;
	}

	@Override
	public CEstimatedItems<T> collect() {
		fitK();
		return new CEstimatedItems<>( //
		    _items.stream().collect(Collectors.toList()) //
		);
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		fitK();

		stream.writeInt(_items.size());
		for (CEstimationFor<T> item : _items) {
			stream.writeType(item.Item);
			stream.writeLong(item.Count);
		}
	}
}
