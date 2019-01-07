package YASL.Collectors.Heap;

public interface IPrioritizedItem<T> {
	public int Priority();
	public T Value();
}