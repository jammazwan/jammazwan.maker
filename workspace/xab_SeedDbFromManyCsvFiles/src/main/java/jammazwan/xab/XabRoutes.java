package jammazwan.xab;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;


public class XabRoutes extends RouteBuilder {

    @Autowired
    private XabBean xabBean;
    @Autowired
    private XabProcessor xabProcessor;

    @Override
    public void configure() throws Exception {
        from("direct:xab")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    String text = exchange.getIn().getBody(String.class);
                    String newAnswer = xabBean.answer(text);
                    exchange.getOut().setBody(newAnswer);
                }
            });
    }
}
