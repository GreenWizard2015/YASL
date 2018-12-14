package YASL;

import java.util.Iterator;
import java.util.List;

public class CEstimatedItems<T> implements Iterable<CEstimationFor<T>> {
	private final List<CEstimationFor<T>> _estimation;

	public CEstimatedItems(List<CEstimationFor<T>> estimation) {
		this._estimation = estimation;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		String del = "";
		for (CEstimationFor<T> item : _estimation) {
			res.append(del);
			res.append(item.toString());
			del = ", ";
		}
		return res.toString();
	}

	public CEstimationFor<T> get(int i) {
		return _estimation.get(i);
	}

	@Override
	public Iterator<CEstimationFor<T>> iterator() {
		return _estimation.iterator();
	}
}
