package YASL.Counters.Tables;

import java.io.DataOutput;
import java.io.IOException;

public class CcsShortTable extends CcsTable {
	private final short[][] _sketch;

	public CcsShortTable(int width, int depth) {
		this._sketch = new short[depth][width];
	}

	@Override
	public long read(int pos, int posID) {
		return _sketch[posID][pos] & 0xFF_FF;
	}

	@Override
	public void put(int pos, int posID, long value) {
		_sketch[posID][pos] = (short) value;
	}

	@Override
	public void store(DataOutput stream) throws IOException {
		for (short[] row : _sketch)
			for (short cell : row)
				stream.writeShort(cell);
	}

}
