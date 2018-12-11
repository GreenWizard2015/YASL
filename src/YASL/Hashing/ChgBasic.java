package YASL.Hashing;

import java.util.Random;

public class ChgBasic<T> implements IHashingGenerator<T> {

	@Override
	public IHasher<T> generate(int range, int levels) {
		return x -> {
			long hash = x.hashCode();
			final Random r = new Random(hash);
			final int[] res = new int[levels];
			for (int i = 0; i < levels; i++) {
				hash *= r.nextInt(range);
				hash += r.nextLong();

				res[i] = (int) (hash % range);
				res[i] = (res[i] < 0) ? -res[i] : res[i];
			}
			return res;
		};
	}
}
