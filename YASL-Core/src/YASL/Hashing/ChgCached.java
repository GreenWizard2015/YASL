package YASL.Hashing;

import java.util.HashMap;
import java.util.Map;

public class ChgCached<T> implements IHashingGenerator<T> {
	private final IHashingGenerator<T>						_gen;
	private final Map<CHasherParams, IHasher<T>>	_cache	= new HashMap<>();

	public ChgCached(IHashingGenerator<T> gen) {
		this._gen = gen;
	}

	@Override
	public IHasher<T> generate(int range, int levels) {
		final CHasherParams params = new CHasherParams(range, levels);
		IHasher<T> res = _cache.get(params);
		if (null == res) {
			res = _gen.generate(range, levels);
			_cache.put(params, res);
		}
		return res;
	}

	private static class CHasherParams {
		private final int	_range;
		private final int	_levels;

		public CHasherParams(int range, int levels) {
			this._range = range;
			this._levels = levels;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			return prime * _levels + _range;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if ((obj == null) || (getClass() != obj.getClass()))
				return false;

			final CHasherParams other = (CHasherParams) obj;
			return ((_levels == other._levels) && (_range == other._range));
		}
	}
}
