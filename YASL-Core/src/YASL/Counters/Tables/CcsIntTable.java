package YASL.Counters.Tables;

import java.io.DataOutput;
import java.io.IOException;

public class CcsIntTable extends CcsTable {
	private final int[][] _sketch;

	public CcsIntTable(int[][] data) {
		_sketch = data;
	}

	public CcsIntTable(int width, int depth) {
		this(new int[depth][width]);
	}

	@Override
	public long read(int pos, int posID) {
		return _sketch[posID][pos] & 0xFF_FF_FF_FF;
	}

	@Override
	public void put(int pos, int posID, long value) {
		_sketch[posID][pos] = (int) value;
	}

	@Override
	public void store(DataOutput stream) throws IOException {
		for (int[] row : _sketch)
			for (int cell : row)
				stream.writeInt(cell);
	}

}
