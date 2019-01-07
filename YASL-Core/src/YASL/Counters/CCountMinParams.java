package YASL.Counters;

public class CCountMinParams {
	public final int	width;
	public final int	depth;

	public CCountMinParams(double err, double confidence) {
		// http://dimacs.rutgers.edu/~graham/pubs/papers/cmencyc.pdf
		width = (int) Math.ceil(Math.E / err);
		depth = (int) Math.ceil(Math.log(1 / (1 - confidence)));
		// alternative
		// width = (int) Math.ceil(2 * Math.E / err);
		// depth = (int) Math.ceil(-Math.log(1 - confidence) / Math.log(2));
	}

	public CCountMinParams(int width, int depth) {
		this.width = width;
		this.depth = depth;
	}
}