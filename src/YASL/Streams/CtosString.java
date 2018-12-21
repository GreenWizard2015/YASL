package YASL.Streams;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class CtosString extends CtosBasic<String> {

	public CtosString(DataOutput _stream) {
		super(_stream);
	}

	public CtosString(OutputStream _stream) {
		super(_stream);
	}

	@Override
	public void writeType(String value) throws IOException {
		writeUTF(value);
	}

}