package YASL.AugmentedSketch;

import YASL.IEstimator;
import YASL.IItemsCounter;

/**
 * Based on
 * <a href="http://www.ntu.edu.sg/home/arijit.khan/Papers/asketch.pdf">this
 * article</a>. This is a hybrid of items counter and estimator. High-frequency
 * items are put into separated, more precised, counters, which is, mainly,
 * reduce overestimation of low-frequency items.
 */
public interface IAugmentedSketch<T> extends IEstimator<T>, IItemsCounter<T> {
	/**
	 * Workaround for increasing only high-frequency items.
	 * 
	 * @return new estimation for item. Return 0, if item aren't frequent.
	 */
	public long putIfFrequent(T item, long count);
}