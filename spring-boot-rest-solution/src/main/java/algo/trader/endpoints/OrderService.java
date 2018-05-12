package algo.trader.endpoints;

import static algo.trader.domain.Currency.EUR;
import static algo.trader.domain.Side.SELL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import algo.trader.domain.MarketOrder;



@RestController
public class OrderService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/order", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
	public MarketOrder getOrder(){
		return new MarketOrder(EUR, 1000000.0, SELL);
	}

	@RequestMapping(value="/order", method = RequestMethod.POST)
	public void addOrder(@RequestBody MarketOrder order){
		log.info("Order received "+ order);
	}
}
