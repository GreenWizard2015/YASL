package YASL.Hashing;

import java.nio.ByteBuffer;

public class CIntegerHashing<T> implements IHashingAlgorithm<T> {
	public interface IIntegerHashing<T> {
		public int apply(T x);
	}

	private final IIntegerHashing<T> _hashing;

	public CIntegerHashing(IIntegerHashing<T> _hashing) {
		this._hashing = _hashing;
	}

	@Override
	public void hash(T value, ByteBuffer res) {
		res.putInt(_hashing.apply(value));
	}

}
