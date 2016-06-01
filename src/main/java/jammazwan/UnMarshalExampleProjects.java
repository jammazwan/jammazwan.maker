package jammazwan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.language.JsonPathExpression;

import jammazwan.entity.XmplProject;

public class UnMarshalExampleProjects extends RouteBuilder {
	JsonPathExpression jsonPathExpression = new JsonPathExpression("$[*]");
	List<Map<String, String>> exampleProjects = new ArrayList<Map<String, String>>();

	@Override
	public void configure() throws Exception {
		from("file://src/main/resources?noop=true&fileName=xyzprojects.json").split(jsonPathExpression)
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Map<String, String> contents = exchange.getIn().getBody(Map.class);
						exampleProjects.add(contents);
					}
				});
	}

	public List<Map<String, String>> getExampleProjects() {
		return exampleProjects;
	}

}
