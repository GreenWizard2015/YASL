package YASL.Counters.Tables;

import java.io.DataOutput;
import java.io.IOException;

public class CcsByteTable extends CcsTable {
	private final byte[][] _sketch;

	public CcsByteTable(int width, int depth) {
		this._sketch = new byte[depth][width];
	}

	@Override
	public long read(int pos, int posID) {
		return _sketch[posID][pos] & 0xFF;
	}

	@Override
	public void put(int pos, int posID, long value) {
		_sketch[posID][pos] = (byte) value;
	}

	@Override
	public void store(DataOutput stream) throws IOException {
		for (byte[] row : _sketch)
			for (byte cell : row)
				stream.writeByte(cell);
	}
}
