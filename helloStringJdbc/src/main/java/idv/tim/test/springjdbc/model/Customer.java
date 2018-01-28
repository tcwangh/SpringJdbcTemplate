package idv.tim.test.springjdbc.model;

public class Customer {
	
	private long custid;
	private String firstname;
	private String lastname;
	
	public Customer() {
		
	}

	public Customer(long custid, String firstname, String lastname) {
		super();
		this.custid = custid;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public long getCustid() {
		return custid;
	}

	public void setCustid(long custid) {
		this.custid = custid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String toString() {
		 return "Customer [id= "+ this.custid + ", first-name= "+ this.firstname + 
				 ", last-name= "+ this.lastname + "]";

	}
}
