package YASL.Hashing;

import java.nio.ByteBuffer;

public interface IHashingAlgorithm<T> {
	public void hash(T value, ByteBuffer res);
}
