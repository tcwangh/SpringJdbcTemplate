package idv.tim.test.springjdbc.controller;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import idv.tim.test.springjdbc.dao.CustomerDAO;
import idv.tim.test.springjdbc.dao.StudentDAO;
import idv.tim.test.springjdbc.model.Customer;
import idv.tim.test.springjdbc.model.Student;


@RestController
@RequestMapping("/helloSpringJdbc")
public class HelloSpringJdbcRestController {
	
	static final Logger logger = Logger.getLogger(HelloSpringJdbcRestController.class);
	
	private void init() {
		try{
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL url = loader.getResource("log4j_helloSpringJdbc.properties");
			PropertyConfigurator.configure(url);
		}catch(Exception e) {
			System.out.println("Exception happen while loading log4j configuration file:" + e.toString());
		}
	}
	
	@RequestMapping(value={"/customer/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public Customer CustomerInfo(@PathVariable(value="id") int custId)
    {
		init();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerDAO customerDaoImpl = (CustomerDAO) ctx.getBean("customerJdbcDAO");
		Customer customer = customerDaoImpl.findById(custId);
		logger.info(customer);
		return customer;
    }
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public Customer update(@RequestBody Customer customer) {
		init();
		if (customer != null) {
			logger.info("Begin to add customer:" + customer.toString());
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			CustomerDAO customerDaoImpl = (CustomerDAO) ctx.getBean("customerJdbcDAO");
			int rows = customerDaoImpl.insert(customer);
			logger.info("Insert Rows:" + rows);
	    }
		return customer;
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Student addStudent (@RequestBody Student student) {
		init();
		if (student != null) {
			logger.info("Begin to add student:" + student.toString());
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			StudentDAO studentDaoImpl = (StudentDAO) ctx.getBean("studentJdbcDAO");
			int rows = studentDaoImpl.insert(student);
			logger.info("Insert Rows:" + rows);
		}
		return student;
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable(value="id") int studentId){
		init();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentDAO studentDaoImpl = (StudentDAO) ctx.getBean("studentJdbcDAO");
		Student theStudent = studentDaoImpl.readById(studentId);
		logger.info(theStudent);
		return theStudent;
	}
	
	

}
