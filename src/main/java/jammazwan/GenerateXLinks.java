package jammazwan;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import jammazwan.util.HoldContextOpenUntilDone;

public class GenerateXLinks {

	static void go(String linksContent, String xgitstatusContent) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new XLinksRouteBuilder());
		context.start();
		context.createProducerTemplate().requestBodyAndHeader("direct:writer", linksContent, "CamelFileName",
				"xlinks.txt");
		context.createProducerTemplate().requestBodyAndHeader("direct:writeGitStatus", xgitstatusContent, "CamelFileName",
				"xgitstatus.txt");
		HoldContextOpenUntilDone.go(context);
		context.stop();
	}
}
