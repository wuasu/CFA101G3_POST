package category.model;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class CategoryImpl implements CategoryDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	JdbcTemplate template = new JdbcTemplate(ds);

//	private static final String INSERT_STMT = "INSERT INTO category (cat_id, cat_name) VALUES (?, ?)";
//	private static final String UPDATE = "UPDATE category set cat_name=? where cat_id = ?";
//	private static final String DELETE = "DELETE FROM category where cat_id = ?";
//
//	private static final String GET_ALL_STMT = "SELECT cat_name FROM category order by cat_id";
//	private static final String GET_ONE_STMT = "SELECT cat_name FROM category where cat_id = ?";

//新增文章分類
	public int insert(CategoryVO category) {
		String sql = "INSERT INTO category(cat_name) VALUES (?)";
		int count = template.update(sql, category.getCat_name());
		System.out.println(count);
		return count;	
		}
	
	
	public void update(CategoryVO category) {
		String sql = "update category set cat_name where cat_id = ?";
		template.update(sql,category.getCat_id());
	}
	
	public int delete(Integer cat_id) {
		String sql = "DELETE  FROM category where cat_id = ?";
		int count = template.update(sql, cat_id);
		System.out.println(count);
		return count;	
		}


	public CategoryVO findByCatId(Integer cat_id) {
		String sql = "SELECT * FROM category where cat_id = ?";
		try {
			CategoryVO categoryVO = template.queryForObject(sql,
					new BeanPropertyRowMapper<CategoryVO>(CategoryVO.class), cat_id);
			return categoryVO;
		} catch (Exception e) {
			return null;
		}

	}

	public List<CategoryVO> getAll() {
		String sql = "SELECT * FROM category";
		List<CategoryVO> list = template.query(sql, new BeanPropertyRowMapper<CategoryVO>(CategoryVO.class));
		return list;
	}
	
}
