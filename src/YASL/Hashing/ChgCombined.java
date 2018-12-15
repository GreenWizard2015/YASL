package YASL.Hashing;

import java.nio.ByteBuffer;

public class ChgCombined<T> implements IHashingGenerator<T> {
	private final IHashingAlgorithm<T>[]	_hashes;
	private final ISalter<T>							_salting;

	@SafeVarargs
	public ChgCombined(ISalter<T> salter, IHashingAlgorithm<T>... hashes) {
		_hashes = hashes;
		_salting = salter;
	}

	@SafeVarargs
	public ChgCombined(IHashingAlgorithm<T>... hashes) {
		this( //
		    (x, r) -> {
			    throw new RuntimeException("No salter.");
		    }, //
		    hashes //
		);
	}

	@Override
	public IHasher<T> generate(int range, int levels) {
		return x -> {
			final int bytesPerItem = bytesIn(range);
			final ByteBuffer res = populate(x, levels * bytesPerItem);
			final int[] resArr = new int[levels];
			for (int i = 0; i < levels; i++) {
				switch (bytesPerItem) {
					case 4:
						resArr[i] = Math.abs(res.getInt() % range);
						break;

					case 2:
						resArr[i] = Math.abs(res.getShort() % range);
						break;

					case 1:
						resArr[i] = Math.abs(res.get() % range);
						break;
				}
			}
			return resArr;
		};
	}

	public int bytesIn(int range) {
		if (range <= 0xFF)
			return 1;
		if (range <= 0xFFFF)
			return 2;
		return 4;
	}

	private void populate(T x, int sz, ByteBuffer res) {
		for (int i = 0; i < _hashes.length; i++) {
			if (sz <= res.position())
				break;
			_hashes[i].hash(x, res);
		}
	}

	private ByteBuffer populate(T x, int sz) {
		final ByteBuffer res = ByteBuffer.allocate(32 + sz);
		populate(x, sz, res);
		for (int round = 1; res.position() < sz; round++)
			populate(_salting.apply(x, round), sz, res);

		res.rewind();
		return res;
	}
}
