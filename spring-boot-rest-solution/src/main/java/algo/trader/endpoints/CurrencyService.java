package algo.trader.endpoints;

import static algo.trader.domain.Currency.EUR;
import static algo.trader.domain.Side.SELL;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import algo.trader.domain.Currency;
import algo.trader.domain.MarketOrder;

@RestController
public class CurrencyService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/currencies", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
	public List<Currency> getCurrencies(){
		return Arrays.asList(Currency.values());
	}
}
