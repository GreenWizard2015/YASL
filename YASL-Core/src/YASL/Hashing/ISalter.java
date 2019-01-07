package YASL.Hashing;

public interface ISalter<T> {
	public T apply(T value, int round);
}