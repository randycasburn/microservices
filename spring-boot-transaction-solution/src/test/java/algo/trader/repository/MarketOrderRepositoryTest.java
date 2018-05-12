package algo.trader.repository;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static algo.trader.domain.Currency.*;
import static algo.trader.domain.Side.*;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import algo.trader.Application;
import algo.trader.domain.Currency;
import algo.trader.domain.MarketOrder;
import algo.trader.domain.Side;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class})
public class MarketOrderRepositoryTest {

	@Autowired
	private MarketOrderRepository marketOrderRepository;
	
	@Before
	public void setupDataBase(){
		MarketOrder marketOrder = new MarketOrder();
		marketOrder.setCurrency(Currency.EUR);
		marketOrder.setAmount(999.9);
		marketOrder.setSide(Side.SELL);
		marketOrderRepository.saveOrder(marketOrder);
	}
	
	@Test
	public void insertSingleMarketOrderCompletesSuccessfully() {
	
		List<MarketOrder> marketOrders = marketOrderRepository.findAllOrders();
		assertThat("market order size should be 1", 1, equalTo(marketOrders.size()));
		MarketOrder marketOrder = marketOrders.get(0);
		assertThat("currency should be EUR", EUR, equalTo(marketOrder.getCurrency()));
		assertThat("amount should be 999.9", 999.9, equalTo(marketOrder.getAmount()));
		assertThat("side should be SELL", SELL, equalTo(marketOrder.getSide()));
	}

}
