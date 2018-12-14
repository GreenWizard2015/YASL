package YASL.Collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import YASL.CEstimatedItems;
import YASL.CEstimationFor;
import YASL.IEstimationCollector;

public class KTopCollector<T> implements IEstimationCollector<T> {
	private final int												_K;
	private long														_min	= 0L;
	private final List<CEstimationFor<T>>		_items;
	private final Map<T, CEstimationFor<T>>	_byValue;

	public KTopCollector(int K) {
		this._K = K;
		_items = new ArrayList<>();
		_byValue = new HashMap<>();
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
		_items.add(-(ind + 1), est);

		if ((_K * 1.1) < _items.size())
			fitK();
	}

	private void fitK() {
		while (_K < _items.size()) {
			_byValue.remove(_items.remove(_K).Item);
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
}
