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
		final int bytesPerItem = bytesIn(range);
		return x -> {
			final ByteBuffer res = populate(x, levels * bytesPerItem);
			final int[] resArr = new int[levels];
			for (int i = 0; i < levels; i++) {
				switch (bytesPerItem) {
					case 4:
						final long value = res.getInt() & 0xFFFFFFFFl;
						final long lRange = range & 0xFFFFFFFFl;
						resArr[i] = (int) (value % lRange);
						break;

					case 2:
						resArr[i] = (res.getShort() & 0xFFFF) % range;
						break;

					case 1:
						resArr[i] = (res.get() & 0xFF) % range;
						break;

					default:
						throw new RuntimeException("Unsupported hash size");
				}
			}
			return resArr;
		};
	}

	public int bytesIn(int range) {
		if (range < 0) // unsigned
			return 4;

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
		final ByteBuffer res = ByteBuffer.allocate(256 + sz);
		populate(x, sz, res);
		for (int round = 1; res.position() < sz; round++)
			populate(_salting.apply(x, round), sz, res);

		res.rewind();
		return res;
	}
}
