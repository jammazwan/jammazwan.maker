package jammazwan.entity;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * This is written as a test but is used as a way to generate json
 */
public class XmplProjectTest extends CamelTestSupport {

	@Test
	public void test() throws Exception {
		context.addRoutes(new SampleProjectsRoutes());
		String xyzCode = "xaa";
		String name = "ObjectToJsonFile";
		String desc = "Create an object and save it as json in a file";
		String version = "0.1.0-SNAPSHOT";
		String technology = "jackson, file";
		String specialInstructions = "be careful not to ever do the wrong thing, it could be incorrect. Do the right thing instead";
		String pckg = "jammazwan";
		String include = "processor";
		XmplProject xmplProject = new XmplProject(xyzCode, name, desc, version, technology, specialInstructions,
				include);
		template.requestBody("direct:start", xmplProject);
	}

	public class SampleProjectsRoutes extends RouteBuilder {

		@Override
		public void configure() throws Exception {
			from("direct:start").process(new Processor() {
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setHeader("CamelFileName", "sampleproject.json");
				}
			}).marshal().json(JsonLibrary.Jackson, true).to("file:.");
		}

	}

}
