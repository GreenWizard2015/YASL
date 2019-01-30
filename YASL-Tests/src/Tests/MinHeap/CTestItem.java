package Tests.MinHeap;

import YASL.MinHeap.CpiSimple;

class CTestItem extends CpiSimple<String> {
	public CTestItem(String value, int priority) {
		super(value, priority);
	}

	public void setPriority(int i) {
		_priority = i;
	}

	@Override
	public String toString() {
		return value.toString() + " " + Priority();
	}
}