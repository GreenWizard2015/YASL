package Tests.TestHelper;

public class CtlBasic implements ITestLogger {
	private final StringBuffer _log = new StringBuffer();

	@Override
	public void logIt(String method, Object[] extras) {
		if (0 < _log.length())
			_log.append(" | ");

		_log.append(method);

		if (0 < extras.length) {
			_log.append("(");
			String del = "";
			for (Object o : extras) {
				final String s = (null == o) ? "null" : o.toString();
				_log.append(del).append(s);
				del = ", ";
			}
			_log.append(")");
		}
	}

	@Override
	public String log() {
		return _log.toString();
	}

}
