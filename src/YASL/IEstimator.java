package YASL;

import java.io.IOException;

import YASL.Streams.TypedOutputStream;

public interface IEstimator<T> {
	public void add(T item, long count);
	public CEstimatedItems<T> estimate();
	public void store(TypedOutputStream<T> stream) throws IOException;

	public default void add(T item) {
		add(item, 1);
	}
}
