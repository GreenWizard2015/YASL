package YASL.Streams;

import java.io.DataInput;
import java.io.IOException;

public interface TypedInputStream<T> extends DataInput {
	public T readType() throws IOException;
}
