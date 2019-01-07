package YASL.Counters.Tables;

import java.io.DataOutput;
import java.io.IOException;

public abstract class CcsTable {
	public abstract long read(int pos, int posID);
	public abstract void put(int pos, int posID, long value);
	public abstract void store(DataOutput stream) throws IOException;
}
