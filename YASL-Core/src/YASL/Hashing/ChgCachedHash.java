package YASL.Hashing;

public class ChgCachedHash<T> implements IHashingGenerator<T> {
	private final IHashingGenerator<T> _gen;

	public ChgCachedHash(IHashingGenerator<T> gen) {
		this._gen = gen;
	}

	@Override
	public IHasher<T> generate(int range, int levels) {
		final IHasher<T> gen = _gen.generate(range, levels);
		return new IHasher<T>() {
			private T			_lastRequest	= null;
			private int[]	_lastResult		= null;

			@Override
			public int[] apply(T x) {
				if (!x.equals(_lastRequest)) {
					_lastRequest = x;
					_lastResult = gen.apply(x);
				}
				return _lastResult;
			}
		};
	}
}
