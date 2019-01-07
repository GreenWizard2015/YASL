package YASL.Streams;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;

public class CtisString extends CtisBasic<String> {

	public CtisString(DataInput stream) {
		super(stream);
	}

	public CtisString(InputStream stream) {
		super(stream);
	}

	@Override
	public String readType() throws IOException {
		return readUTF();
	}
}