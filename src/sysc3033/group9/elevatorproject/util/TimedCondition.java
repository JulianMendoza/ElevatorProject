package sysc3033.group9.elevatorproject.util;

public class TimedCondition {

	private boolean isConditionSatisfied;

	private long maxTime;

	public TimedCondition(long maxTime) {

		this.maxTime = maxTime;

		isConditionSatisfied = false;

	}

	private void check() {

		long initTime = System.currentTimeMillis();

		while (System.currentTimeMillis() < initTime + maxTime) {

			if (condition()) {

				isConditionSatisfied = true;

				break;

			}

			try {

				Thread.sleep(maxTime / 10);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

	}

	public boolean isConditionSatisfied() {

		check();

		return isConditionSatisfied;

	}

	public boolean condition() {

		return false;

	}

}
