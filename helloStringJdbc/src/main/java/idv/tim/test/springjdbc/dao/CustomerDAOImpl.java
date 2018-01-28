package idv.tim.test.springjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import idv.tim.test.springjdbc.model.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	
	private DataSource dataSource;
	
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(Customer customer) {
		String sql  = "INSERT INTO customers (custid,firstname,lastname) values (?,?,?)";
		Connection conn=null;
		int effectedRows = 0;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, customer.getCustid());
			ps.setString(2, customer.getFirstname());
			ps.setString(3, customer.getLastname());
			effectedRows = ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if (conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
		return effectedRows;
	}

	@Override
	public Customer findById(long id) {
		String sql  = "SELECT custid,firstname,lastname FROM customers WHERE custid = ?";
		Connection conn=null;
		Customer customer = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
						rs.getLong("custid"),
						rs.getString("firstname"),
						rs.getString("lastname"));
			}
			rs.close();
			ps.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if (conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
		return customer;
	}
}
