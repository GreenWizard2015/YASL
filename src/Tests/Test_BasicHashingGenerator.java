package Tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import YASL.Hashing.ChgCombined;
import YASL.Hashing.IHasher;

public class Test_BasicHashingGenerator {
	@Test
	public void uniqueSequence() {
		ChgCombined<Integer> gen = new ChgCombined<Integer>( //
		    (x, r) -> ~x * 23 * r, //
		    x -> x, x -> x >> 16, x -> ~x //
		);
		final IHasher<Integer> hasher = gen.generate(5000, 3);
		long notUnique = 0;
		for (int i = 0; i < 1_000_000; i++) {
			final int[] res = hasher.apply(i);
			final Set<Integer> values = new HashSet<>();
			for (int j = 0; j < res.length; j++) {
				if (!values.add(res[j])) {
					notUnique++;
					break;
				}
			}
		}
		Assert.assertEquals(1, notUnique);

	}
}
