package Tests;

import org.junit.Assert;
import org.junit.Test;

import YASL.Hashing.ChgCombined;
import YASL.Hashing.IHasher;

public class Test_hgCombined {
	@Test
	public void returnHashesFirst() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    x -> 1, x -> 2 //
		).generate(Integer.MAX_VALUE, 2);

		Assert.assertArrayEquals( //
		    new int[] { 1, 2 }, //
		    gen.apply(1) //
		);
	}

	@Test
	public void respectRange() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    x -> 21, x -> -24//
		).generate(5, 2);

		Assert.assertArrayEquals( //
		    new int[] { 1, 4 }, //
		    gen.apply(1) //
		);
	}

	@Test
	public void useOnlyWhatNeed() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    x -> 1, //
		    x -> {
			    throw new RuntimeException("Don't touch me!");
		    }//
		).generate(5, 1);

		Assert.assertArrayEquals( //
		    new int[] { 1 }, //
		    gen.apply(1) //
		);
	}

	@Test
	public void applySalting() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    (x, r) -> x * r * 4, //
		    x -> x, x -> x + 1 //
		).generate(Integer.MAX_VALUE, 5);

		Assert.assertArrayEquals( //
		    new int[] { 1, 2, 4, 5, 8 }, //
		    gen.apply(1) //
		);
	}
}
