package YASL;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CExactCollector<T> implements IEstimationCollector<T> {

	private Map<T, Long> _storage = new HashMap<>();

	@Override
	public void put(T item, long cnt) {
		_storage.put(item, cnt);
	}

	@Override
	public CEstimatedItems<T> collect() {
		return new CEstimatedItems<>( //
		    _storage.entrySet().stream() //
		        .map(e -> new CEstimationFor<>(e.getKey(), e.getValue())) //
		        .sorted() //
		        .collect(Collectors.toList()) //
		);
	}
}
