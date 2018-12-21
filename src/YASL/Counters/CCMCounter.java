package YASL.Counters;

import java.io.IOException;

import YASL.IItemsCounter;
import YASL.Counters.Tables.CcsIntTable;
import YASL.Counters.Tables.CcsTable;
import YASL.Hashing.IHasher;
import YASL.Hashing.IHashingGenerator;
import YASL.Streams.TypedOutputStream;

public class CCMCounter<T> implements IItemsCounter<T> {
	private final IHasher<T>	_bucketsProvider;
	private final CcsTable		_sketch;

	public CCMCounter( //
	    CcsTable sketch, //
	    IHasher<T> bucketsProvider//
	) {
		_bucketsProvider = bucketsProvider;
		_sketch = sketch;
	}

	public CCMCounter( //
	    int width, int buckets, //
	    IHasher<T> bucketsProvider //
	) {
		this(new CcsIntTable(width, buckets), bucketsProvider);
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

	protected void update(int[] positions, long count) {
		for (int i = 0; i < positions.length; i++) {
			final int pos = positions[i];
			if (_sketch.read(pos, i) < count)
				_sketch.put(pos, i, count);
		}
	}

	@Override
	public long count(T item) {
		return count(_bucketsProvider.apply(item));
	}

	private long count(int[] positions) {
		long res = Long.MAX_VALUE;
		for (int i = 0; i < positions.length; i++) {
			final long cnt = _sketch.read(positions[i], i);
			res = (cnt < res) ? cnt : res;
		}
		return res;
	}

	@Override
	public void store(TypedOutputStream<T> stream) throws IOException {
		_sketch.store(stream);
	}
}
