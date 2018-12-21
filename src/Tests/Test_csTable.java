package Tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import YASL.Counters.Tables.CcsIntTable;
import YASL.Counters.Tables.CcsStoredTable;
import YASL.Counters.Tables.CcsTable;

public class Test_csTable {

	@Test
	public void loadIntTable() throws IOException {
		CcsTable tableA = new CcsIntTable(2, 1);
		tableA.put(0, 0, 5);
		tableA.put(1, 0, 7);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		tableA.store(new DataOutputStream(stream));

		CcsTable tableB = (new CcsStoredTable( //
		    new DataInputStream( //
		        new ByteArrayInputStream(stream.toByteArray()) //
				), //
		    2, 1 //
		)).asInt();

		Assert.assertEquals(tableA.read(0, 0), tableB.read(0, 0));
		Assert.assertEquals(tableA.read(1, 0), tableB.read(1, 0));
	}
}
