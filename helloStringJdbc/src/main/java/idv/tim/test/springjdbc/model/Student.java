package idv.tim.test.springjdbc.model;

public class Student {
	
	private int id;
	private String name;
	private int age;
	private String info;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String toString() {
		 return "Student [id= "+ this.id + ", name= "+ this.name + 
				 ", age= "+ this.age + ", info=" + this.info + "]";

	}
}
