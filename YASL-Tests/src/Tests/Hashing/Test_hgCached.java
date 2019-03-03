package Tests.Hashing;

import org.junit.Assert;
import org.junit.Test;

import YASL.Hashing.ChgBasic;
import YASL.Hashing.ChgCached;
import YASL.Hashing.IHasher;
import YASL.Hashing.IHashingGenerator;

public class Test_hgCached {

	@Test
	public void sameHasherWhenSameParams() {
		final IHashingGenerator<Integer> gen = new ChgCached<>( //
		    new ChgBasic<>() //
		);

		final IHasher<Integer> first = gen.generate(1, 1);
		Assert.assertEquals(first, gen.generate(1, 1));
	}

	@Test
	public void diffHasherWhenDiffParams() {
		final IHashingGenerator<Integer> gen = new ChgCached<>( //
		    new ChgBasic<>() //
		);

		final IHasher<Integer> first = gen.generate(1, 1);
		Assert.assertNotEquals(first, gen.generate(2, 2));
	}
}
