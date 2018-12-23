package YASL.Counters.Tables;

import java.io.DataInput;
import java.io.IOException;

public class CcsStoredTable {
	private final int				_width;
	private final int				_depth;

	public CcsStoredTable(int _width, int _depth) {
		this._width = _width;
		this._depth = _depth;
	}

	public CcsIntTable asInt(DataInput stream) throws IOException {
		final int[][] data = new int[_depth][_width];
		for (int depth = 0; depth < _depth; depth++) {
			for (int cell = 0; cell < _width; cell++) {
				data[depth][cell] = stream.readInt();
			}
		}

		return new CcsIntTable(data);
	}
}
