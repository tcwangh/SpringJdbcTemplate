package idv.tim.test.springjdbc.dao;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import idv.tim.test.springjdbc.model.Student;

public class StudentDAOJdbcImpl implements StudentDAO{
	
	static final Logger logger = Logger.getLogger(StudentDAOJdbcImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
		
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(Student student) {
		
		byte[] bmemo = null;
		String sql  = "INSERT INTO STUDENT (id,name,age,memo) values (:id,:name,:age,:memo)";
		NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("id", student.getId());
		in.addValue("name", student.getName());
		in.addValue("age", student.getAge());
		try {
			bmemo = student.getInfo().getBytes(StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("[Convert Student Info into byteArray by UTF-8 Fail]-" + e.toString());
		}
		in.addValue("memo",  new SqlLobValue(new ByteArrayInputStream(bmemo), 
				bmemo.length, new DefaultLobHandler()), Types.BLOB);
		int effectedRows = jdbcTemplateObject.update(sql, in);
		return effectedRows;
	}

	@Override
	public Student readById(int id) {
		
		String sql  = "SELECT id,name,age,memo FROM student WHERE id = :id";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Student student = (Student) jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new StudentRowMapper());
		return student;
	}
	
	private class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setAge(rs.getInt("age"));
			LobHandler lobHandler = new DefaultLobHandler();
			byte[] requestData = lobHandler.getBlobAsBytes(rs,"memo");
			student.setInfo(new String(requestData));
			return student;
		}
		
	}
	
	
	
	
	

}
