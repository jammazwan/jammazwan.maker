package jammazwan.util;

import org.apache.camel.CamelContext;

/**
 * 
 * @author petecarapetyan This is an experimental class. May or may not be a
 *         good idea.
 */
public class HoldContextOpenUntilDone {
	public static void go(CamelContext context) {
		try {
			System.err.println("Starting\n");
			for (int i = 0; i < 30; i++) {
				System.err.print(" " + i);
				Thread.sleep(1000);
				if (context.getInflightRepository().size() == 0) {
					System.err.println("\nHoldContextOpenUntilDone is complete\n");
					Thread.sleep(2000);// Tried less, files still not completed
					if (context.getInflightRepository().size() == 0) {
						break;
					}
				}
			}
			System.gc();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
