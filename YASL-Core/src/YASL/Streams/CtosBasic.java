package YASL.Streams;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class CtosBasic<T> implements TypedOutputStream<T> {
	private final DataOutput _stream;

	public CtosBasic(DataOutput _stream) {
		this._stream = _stream;
	}

	public CtosBasic(OutputStream stream) {
		_stream = new DataOutputStream(stream);
	}

	@Override
	public void write(int b) throws IOException {
		_stream.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		_stream.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		_stream.write(b, off, len);
	}

	@Override
	public void writeBoolean(boolean v) throws IOException {
		_stream.writeBoolean(v);
	}

	@Override
	public void writeByte(int v) throws IOException {
		_stream.writeByte(v);
	}

	@Override
	public void writeShort(int v) throws IOException {
		_stream.writeShort(v);
	}

	@Override
	public void writeChar(int v) throws IOException {
		_stream.writeChar(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		_stream.writeInt(v);
	}

	@Override
	public void writeLong(long v) throws IOException {
		_stream.writeLong(v);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		_stream.writeFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		_stream.writeDouble(v);
	}

	@Override
	public void writeBytes(String s) throws IOException {
		_stream.writeBytes(s);
	}

	@Override
	public void writeChars(String s) throws IOException {
		_stream.writeChars(s);
	}

	@Override
	public void writeUTF(String s) throws IOException {
		_stream.writeUTF(s);
	}
}
