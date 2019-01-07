package YASL.Hashing;

import java.util.function.Function;

public interface IHasher<T> extends Function<T, int[]> {
}
