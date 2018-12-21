package YASL.Streams;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;

public class CtisInteger extends CtisBasic<Integer> {

	public CtisInteger(DataInput stream) {
		super(stream);
	}

	public CtisInteger(InputStream stream) {
		super(stream);
	}

	@Override
	public Integer readType() throws IOException {
		return readInt();
	}
}