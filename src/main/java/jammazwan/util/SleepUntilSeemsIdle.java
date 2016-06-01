package jammazwan.util;

public class SleepUntilSeemsIdle {
	/*
	 * this doesn't work. Probably needs to be wired up to free memory, not max
	 * memory. So much for 30 minutes of work.
	 */
	public static void work() {
		long maxMemory = 10;
		long currentMemory = Runtime.getRuntime().maxMemory();
		while (maxMemory / currentMemory < 4) {
			currentMemory = Runtime.getRuntime().maxMemory();
			maxMemory = getMaxMemory(maxMemory, currentMemory);
			sleep();
		}
		System.out.println(System.currentTimeMillis());
		return;
	}

	private static long getMaxMemory(long previousMaxMemory, long currentMaxMemory) {
		Runtime runtime = Runtime.getRuntime();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		System.out.println(" " + currentMaxMemory + " " + allocatedMemory + " " + freeMemory);
		if (currentMaxMemory > previousMaxMemory) {
			return currentMaxMemory;
		} else {
			return previousMaxMemory;
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
