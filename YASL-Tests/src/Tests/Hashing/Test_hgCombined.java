package Tests.Hashing;

import org.junit.Assert;
import org.junit.Test;

import YASL.Hashing.CIntegerHashing;
import YASL.Hashing.ChgCombined;
import YASL.Hashing.IHasher;

public class Test_hgCombined {

	@Test
	public void returnHashesFirst() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    new CIntegerHashing<>(x -> 1), //
		    new CIntegerHashing<>(x -> 2) //
		).generate(Integer.MAX_VALUE, 2);

		Assert.assertArrayEquals( //
		    new int[] { 1, 2 }, gen.apply(1) //
		);
	}

	@Test
	public void respectRange() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    (x, r) -> x, // salt
		    (x, r) -> r.put((byte) 21), //
		    (x, r) -> r.put((byte) -24) //
		).generate(5, 2);

		Assert.assertArrayEquals( //
		    new int[] { 1, 4 }, //
		    gen.apply(1) //
		);
	}

	@Test
	public void useOnlyWhatNeed() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    new CIntegerHashing<>(x -> 1), //
		    (x, r) -> {
			    throw new RuntimeException("Don't touch me!");
		    } //
		).generate(Integer.MAX_VALUE, 1);

		Assert.assertArrayEquals(new int[] { 1 }, gen.apply(2));
	}

	@Test
	public void applySalting() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    (x, r) -> x * r * 4, //
		    new CIntegerHashing<>(x -> x), //
		    new CIntegerHashing<>(x -> x + 1) //
		).generate(Integer.MAX_VALUE, 5);

		Assert.assertArrayEquals( //
		    new int[] { 1, 2, 4, 5, 8 }, gen.apply(1) //
		);
	}

	@Test
	public void sizeOfRange() {
		ChgCombined<Integer> gen = new ChgCombined<Integer>(null);
		Assert.assertEquals(1, gen.bytesIn(3));

		Assert.assertEquals(2, gen.bytesIn(256));
		Assert.assertEquals(2, gen.bytesIn(0xFFFF));

		Assert.assertEquals(4, gen.bytesIn(0xFFFF00));
		Assert.assertEquals(4, gen.bytesIn(0x7FFFFF00));
	}

	@Test
	public void use4bytes() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    new CIntegerHashing<>(x -> x) //
		).generate(Integer.MAX_VALUE, 1);

		Assert.assertArrayEquals( //
		    new int[] { 1 }, gen.apply(1) //
		);
	}

	@Test
	public void use2bytes() {
		IHasher<Integer> gen = new ChgCombined<Integer>( //
		    new CIntegerHashing<>(x -> x), //
		    new CIntegerHashing<>(x -> {
			    throw new RuntimeException("Don't touch me.");
		    }) //
		).generate(0xFFFF, 2);

		Assert.assertArrayEquals( //
		    new int[] { 1, 2 }, gen.apply(0x00010002) //
		);
	}
}
