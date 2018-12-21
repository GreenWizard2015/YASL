package YASL.Streams;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class CtosInteger extends CtosBasic<Integer> {

	public CtosInteger(DataOutput _stream) {
		super(_stream);
	}

	public CtosInteger(OutputStream _stream) {
		super(_stream);
	}

	@Override
	public void writeType(Integer value) throws IOException {
		writeInt(value);
	}

}