package YASL.Hashing;

public interface IHashingGenerator<T> {
	public IHasher<T> generate(int range, int levels);
}
