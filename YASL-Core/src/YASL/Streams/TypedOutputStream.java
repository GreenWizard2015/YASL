package YASL.Streams;

import java.io.DataOutput;
import java.io.IOException;

public interface TypedOutputStream<T> extends DataOutput {
	public void writeType(T value) throws IOException;
}
