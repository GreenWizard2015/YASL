package YASL.Collectors;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import YASL.CEstimatedItems;
import YASL.CEstimationFor;
import YASL.IEstimationCollector;

public class KTopCollector<T> implements IEstimationCollector<T> {
	private final long															_K;
	private final PriorityQueue<CEstimationFor<T>>	_queue;
	private long																		_min	= 0L;

	public KTopCollector(long K) {
		this._K = K;
		_queue = new PriorityQueue<>(new Comparator<CEstimationFor<T>>() {

			@Override
			public int compare(CEstimationFor<T> o1, CEstimationFor<T> o2) {
				return o2.compareTo(o1);
			}
		});
	}

	@Override
	public void put(T item, long cnt) {
		if (cnt < _min)
			return;

		CEstimationFor<T> est = new CEstimationFor<>(item, cnt);
		if (_queue.contains(est)) {
			_queue.remove(est);
		}
		_queue.offer(est);

		while (_K < _queue.size())
			_queue.poll();

		if (_queue.size() == _K)
			_min = _queue.peek().Count;
	}

	@Override
	public CEstimatedItems<T> collect() {
		return new CEstimatedItems<>( //
		    _queue.stream() //
		        .map(e -> new CEstimationFor<>(e.Item, e.Count)) //
		        .sorted() //
		        .collect(Collectors.toList()) //
		);
	}
}
