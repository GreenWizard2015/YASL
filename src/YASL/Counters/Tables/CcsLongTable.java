package YASL.Counters.Tables;

import java.io.DataOutput;
import java.io.IOException;

public class CcsLongTable extends CcsTable {
	private final long[][] _sketch;

	public CcsLongTable(int width, int depth) {
		this._sketch = new long[depth][width];
	}

	@Override
	public long read(int pos, int posID) {
		return _sketch[posID][pos];
	}

	@Override
	public void put(int pos, int posID, long value) {
		_sketch[posID][pos] = value;
	}

	@Override
	public void store(DataOutput stream) throws IOException {
		for (long[] row : _sketch)
			for (long cell : row)
				stream.writeLong(cell);
	}
}
