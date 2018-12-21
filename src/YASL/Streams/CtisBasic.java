package YASL.Streams;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class CtisBasic<T> implements TypedInputStream<T> {
	private final DataInput _stream;

	public CtisBasic(DataInput _stream) {
		this._stream = _stream;
	}

	public CtisBasic(InputStream stream) {
		_stream = new DataInputStream(stream);
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		_stream.readFully(b);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		_stream.readFully(b, off, len);
	}

	@Override
	public int skipBytes(int n) throws IOException {
		return _stream.skipBytes(n);
	}

	@Override
	public boolean readBoolean() throws IOException {
		return _stream.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return _stream.readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return _stream.readUnsignedByte();
	}

	@Override
	public short readShort() throws IOException {
		return _stream.readShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return _stream.readUnsignedShort();
	}

	@Override
	public char readChar() throws IOException {
		return _stream.readChar();
	}

	@Override
	public int readInt() throws IOException {
		return _stream.readInt();
	}

	@Override
	public long readLong() throws IOException {
		return _stream.readLong();
	}

	@Override
	public float readFloat() throws IOException {
		return _stream.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return _stream.readDouble();
	}

	@Override
	public String readLine() throws IOException {
		return _stream.readLine();
	}

	@Override
	public String readUTF() throws IOException {
		return _stream.readUTF();
	}
}