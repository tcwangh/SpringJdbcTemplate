package idv.tim.test.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import idv.tim.test.springjdbc.model.Customer;

public class CustomerDAOJdbcImpl implements CustomerDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
		
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int insert(Customer customer) {
		String sql  = "INSERT INTO customers (custid,firstname,lastname) values (?,?,?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		int effectedRows = jdbcTemplate.update(sql, new Object[] { customer.getCustid(),
				customer.getFirstname(), customer.getLastname()});
		return effectedRows;
	}
	@Override
	@SuppressWarnings({ "unchecked" })
	public Customer findById(long id) {
		String sql  = "SELECT custid,firstname,lastname FROM customers WHERE custid = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Customer customer = (Customer) jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new CustomerRowMapper());
		return customer;
	}

	@SuppressWarnings("rawtypes")
	private class CustomerRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer(rs.getLong("custid"),
					rs.getString("firstname"),
					rs.getString("lastname"));
			
			return customer;
		}
	}
}
