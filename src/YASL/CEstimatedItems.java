package YASL;

import java.util.Collection;

public class CEstimatedItems<T> {
	private final Collection<CEstimationFor<T>> _estimation;

	public CEstimatedItems(Collection<CEstimationFor<T>> estimation) {
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
}
