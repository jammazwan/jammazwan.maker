package jammazwan.xab;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class XabProcessor  implements Processor {
	
    public void process(Exchange exchange) throws Exception {
		Object foo = exchange.getIn().getBody();
	}

}
