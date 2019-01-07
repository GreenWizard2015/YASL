package YASL.Collectors;

import java.io.IOException;

import YASL.Streams.TypedInputStream;

public class StoredKTop<T> {
	private final int _size;

	public StoredKTop(int _size) {
		this._size = _size;
	}

	public KTopCollector<T> load(TypedInputStream<T> stream) throws IOException {
		final int cnt = stream.readInt();
		final KTopCollector<T> res = new KTopCollector<>(_size);

		for (int i = 0; i < cnt; i++) {
			final T item = stream.readType();
			final long itemCount = stream.readLong();
			res.put(item, itemCount);
		}
		return res;
	}
}
