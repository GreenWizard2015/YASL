package YASL.Hashing;

public class ChgCombined<T> implements IHashingGenerator<T> {
	public interface ISalter<T> {
		public T apply(T value, int round);
	}

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
			final int[] res = new int[levels];
			int pos = populate(x, res, 0);

			int round = 0;
			while (pos < levels) {
				round++;
				pos = populate(_salting.apply(x, round), res, pos);
			}

			for (int i = 0; i < res.length; i++)
				res[i] = Math.abs(res[i] % range);
			return res;
		};
	}

	private int populate(T x, int[] res, int pos) {
		final int N = Math.min(_hashes.length, res.length - pos);
		for (int i = 0; i < N; i++) {
			res[pos] = _hashes[i].hash(x);
			pos++;
		}
		return pos;
	}
}
