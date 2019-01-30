package Tests.TestHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CtlFiltered implements ITestLogger {
	private final ITestLogger	_log;
	private final Set<String>	_targets;

	public CtlFiltered(ITestLogger log, Set<String> targets) {
		this._log = log;
		this._targets = targets;
	}

	public CtlFiltered(ITestLogger log, String... targets) {
		this(log, new HashSet<>(Arrays.asList(targets)));
	}

	@Override
	public void logIt(String method, Object[] extras) {
		if (_targets.isEmpty() || _targets.contains(method))
			_log.logIt(method, extras);
	}

	@Override
	public String log() {
		return _log.log();
	}
}
