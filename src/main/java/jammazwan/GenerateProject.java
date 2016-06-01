package jammazwan;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import jammazwan.util.SleepUntilSeemsIdle;

public class GenerateProject {


	static void go(Map<String, String> replacements) throws Exception {
		CamelContext context = new DefaultCamelContext();
		InputRouteBuilder inputRouteBuilder = new InputRouteBuilder(replacements);
		context.addRoutes(inputRouteBuilder);
		/*
		 * sleep of 5 seconds each run, seems to be enough on my mac, may take more or
		 * less on your box. You will be able tell because some of the source
		 * folders will not be created, so when you try a maven build it will
		 * fail. It especially likes to leave out src/test/resources. My guess
		 * is that it must be garbage collecting when it does, because it will
		 * work perfectly about 2/3 of the time with only a second
		 */
		context.start();
//		SleepUntilSeemsIdle.work();
		Thread.sleep(5000);
		context.stop();
		System.gc();
	}
}
