package YASL.Collectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import YASL.CEstimationFor;
import YASL.IEstimationCollector;
import YASL.Streams.TypedInputStream;

public class StoredKTop<T> {
	private final TypedInputStream<T>	_stream;
	private final int									_size;

	public StoredKTop(TypedInputStream<T> _stream, int _size) {
		this._stream = _stream;
		this._size = _size;
	}

	public IEstimationCollector<T> load() throws IOException {
		final int cnt = _stream.readInt();
		final List<CEstimationFor<T>> items = new ArrayList<>(cnt);
		final Map<T, CEstimationFor<T>> byValue = new HashMap<>();

		for (int i = 0; i < cnt; i++) {
			final T item = _stream.readType();
			final long itemCount = _stream.readLong();
			final CEstimationFor<T> est= new CEstimationFor<>(item, itemCount);

			items.add(est);
			byValue.put(item, est);
		}
		return new KTopCollector<>(_size, items, byValue);
	}
}
