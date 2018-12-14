package YASL.Counters;

import YASL.IItemsCounter;
import YASL.Hashing.IHasher;
import YASL.Hashing.IHashingGenerator;

public class CCMCounter<T> implements IItemsCounter<T> {
	private final IHasher<T>	_bucketsProvider;
	protected final long[][]	_sketch;

	public CCMCounter( //
	    int width, int buckets, //
	    IHasher<T> bucketsProvider//
	) {
		_bucketsProvider = bucketsProvider;
		_sketch = new long[width][buckets];
	}

	public CCMCounter( //
	    CCountMinParams params, //
	    IHashingGenerator<T> hGen //
	) {
		this( //
		    params.width, params.depth, //
		    hGen.generate(params.width, params.depth) //
		);
	}

	@Override
	public long put(T item, long count) {
		final int[] positions = _bucketsProvider.apply(item);
		count += count(positions);
		update(positions, count);
		return count;
	}

	public void update(T item, long count) {
		update(_bucketsProvider.apply(item), count);
	}

	protected void update(int[] positions, long count) {
		for (int i = 0; i < positions.length; i++) {
			final int pos = positions[i];
			if (_sketch[pos][i] < count)
				_sketch[pos][i] = count;
		}
	}

	public long count(T item) {
		return count(_bucketsProvider.apply(item));
	}

	private long count(int[] positions) {
		long res = Long.MAX_VALUE;
		for (int i = 0; i < positions.length; i++) {
			final long cnt = _sketch[positions[i]][i];
			res = (cnt < res) ? cnt : res;
		}
		return res;
	}
}
