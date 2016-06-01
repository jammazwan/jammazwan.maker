package jammazwan.xaa;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;


public class XaaRoutes extends RouteBuilder {

    @Autowired
    private XaaBean xaaBean;
    @Autowired
    private XaaProcessor xaaProcessor;

    @Override
    public void configure() throws Exception {
        from("direct:xaa")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    String text = exchange.getIn().getBody(String.class);
                    String newAnswer = xaaBean.answer(text);
                    exchange.getOut().setBody(newAnswer);
                }
            });
    }
}
