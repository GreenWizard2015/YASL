package YASL.Hashing;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CMessageDigestHashing implements IHashingAlgorithm<byte[]> {
	private final MessageDigest _digest;

	public CMessageDigestHashing(MessageDigest _digest) {
		this._digest = _digest;
	}

	public CMessageDigestHashing(String algo) throws NoSuchAlgorithmException {
		this(MessageDigest.getInstance(algo));
	}

	@Override
	public void hash(byte[] value, ByteBuffer res) {
		res.put(_digest.digest(value));
	}
}
