package idv.tim.test.springjdbc.dao;

import idv.tim.test.springjdbc.model.Customer;

public interface CustomerDAO {
	
	public int insert(Customer customer);
	public Customer findById(long id);

}
