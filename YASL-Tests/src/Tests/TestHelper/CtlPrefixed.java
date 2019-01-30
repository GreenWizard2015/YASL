package Tests.TestHelper;

public class CtlPrefixed implements ITestLogger {
	private final ITestLogger	_log;
	private final String			_prefix;

	public CtlPrefixed(ITestLogger log, String prefix) {
		this._log = log;
		this._prefix = prefix;
	}

	@Override
	public void logIt(String method, Object[] extras) {
		_log.logIt(_prefix + method, extras);
	}

	@Override
	public String log() {
		return _log.log();
	}
}
