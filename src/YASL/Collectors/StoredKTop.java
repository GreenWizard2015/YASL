package YASL.Collectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import YASL.CEstimationFor;
import YASL.Streams.TypedInputStream;

public class StoredKTop<T> {
	private final int _size;

	public StoredKTop(int _size) {
		this._size = _size;
	}

	public KTopCollector<T> load(TypedInputStream<T> stream) throws IOException {
		final int cnt = stream.readInt();
		final List<CEstimationFor<T>> items = new ArrayList<>(cnt);
		final Map<T, CEstimationFor<T>> byValue = new HashMap<>();

		for (int i = 0; i < cnt; i++) {
			final T item = stream.readType();
			final long itemCount = stream.readLong();
			final CEstimationFor<T> est = new CEstimationFor<>(item, itemCount);

			items.add(est);
			byValue.put(item, est);
		}
		return new KTopCollector<>(_size, items, byValue);
	}
}
