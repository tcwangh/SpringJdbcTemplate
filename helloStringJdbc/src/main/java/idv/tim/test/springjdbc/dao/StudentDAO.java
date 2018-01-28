package idv.tim.test.springjdbc.dao;

import idv.tim.test.springjdbc.model.Student;

public interface StudentDAO {
	
	public int insert(Student student);
	public Student readById(int id);

}
