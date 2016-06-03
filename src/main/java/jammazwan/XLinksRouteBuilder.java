package jammazwan;

import org.apache.camel.builder.RouteBuilder;

public class XLinksRouteBuilder extends RouteBuilder {

	public void configure() {
		from("direct:writer").to("file:src/main/resources/");
		from("direct:writeGitStatus").to("file:src/main/resources/");
	}
}
