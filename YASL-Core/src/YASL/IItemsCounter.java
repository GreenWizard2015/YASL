package YASL;

import java.io.IOException;

import YASL.Streams.TypedOutputStream;

public interface IItemsCounter<T> {
	public long put(T item, long count);
	public long count(T item);
	public void store(TypedOutputStream<T> stream) throws IOException;
}
