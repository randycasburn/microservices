package algo.trader.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import algo.trader.domain.Currency;
import algo.trader.domain.MarketOrder;
import algo.trader.domain.Order;
import algo.trader.domain.Side;

@Component
public class MarketOrderRepository {
	
    private final static Logger logger = LoggerFactory.getLogger(MarketOrderRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public MarketOrderRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void saveOrder(MarketOrder order) {
      jdbcTemplate.update("insert into MARKET_ORDERS(CURRENCY, AMOUNT, SIDE) values (?,?,?)", 
    		  	order.getCurrency().name(), order.getAmount(), order.getSide().name());
        
    }

    @Transactional(readOnly=true)
    public List<MarketOrder> findAllOrders() {
        return jdbcTemplate.query("select CURRENCY, AMOUNT, SIDE from MARKET_ORDERS",
                (new RowMapper<MarketOrder>() {

                    @Override
                    public MarketOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
                      MarketOrder order = new MarketOrder();
                      order.setCurrency(Currency.valueOf(rs.getString("CURRENCY")));
                      order.setAmount(rs.getDouble("AMOUNT"));
                      order.setSide(Side.valueOf(rs.getString("SIDE")));
                      return order;
                    }

                }));
    }
	
}
