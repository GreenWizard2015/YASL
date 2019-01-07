package YASL;

import java.io.IOException;

import YASL.Streams.TypedOutputStream;

public interface IEstimationCollector<T> {
	public void put(T item, long cnt);

	public CEstimatedItems<T> collect();

	public void store(TypedOutputStream<T> stream) throws IOException;
}
