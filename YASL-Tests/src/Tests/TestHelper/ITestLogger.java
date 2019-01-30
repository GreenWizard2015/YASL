package Tests.TestHelper;

public interface ITestLogger {
	public void logIt(String method, Object[] extras);

	public default void log(String method, Object... extras) {
		logIt(method, extras);
	}

	public String log();
}
