package YASL.Hashing;

public class ChgString implements IHashingGenerator<String> {
	private final IHashingGenerator<byte[]> _gen;

	@SafeVarargs
	public ChgString(IHashingAlgorithm<byte[]>... hashes) {
		this._gen = new ChgCombined<byte[]>( //
		    ChgString::rotateBytes, //
		    hashes //
		);
	}

	private static byte[] rotateBytes(byte[] src, int round) {
		byte[] res = new byte[src.length];
		round %= src.length;
		System.arraycopy(src, 0, res, round, src.length - round);
		System.arraycopy(src, src.length - round, res, 0, round);
		return res;
	}

	@Override
	public IHasher<String> generate(int range, int levels) {
		IHasher<byte[]> hashing = _gen.generate(range, levels);
		return x -> {
			return hashing.apply(x.getBytes());
		};
	}

}
