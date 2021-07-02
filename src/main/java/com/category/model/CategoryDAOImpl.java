package com.category.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



public  class CategoryDAOImpl implements CategoryDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT = "INSERT INTO category cat_name=? VALUES=?";
	private static final String UPDATE = "UPDATE category set cat_name=? where cat_id = ?";
	private static final String DELETE = "DELETE FROM category where cat_id = ?";
	private static final String GET_ONE = "SELECT cat_name FROM category where cat_id = ?";
	private static final String GET_ALL = "SELECT cat_name FROM category order by cat_id";

	public void insert(CategoryVO category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, category.getCat_name());
//			pstmt.executeUpdate("set auto_increment_increment=1;");
//			pstmt.executeUpdate("set auto_increment_offset=1;");
			pstmt.executeUpdate();
			System.out.println("新增成功");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//新增資料
	public void update(CategoryVO category) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		UPDATE = "UPDATE category set cat_name=? where cat_id = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, category.getCat_name());
			pstmt.setInt(2, category.getCat_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void delete(Integer cat_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

//			DELETE = "DELETE FROM category where cat_id = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cat_id);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public CategoryVO findByCatId(Integer cat_id) {
		CategoryVO category = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
//			GET_ONE = "SELECT cat_name FROM category where cat_id = ?";
			pstmt.setInt(1, cat_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				category = new CategoryVO();
				category.setCat_name(rs.getString("cat_name"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return category;
	}
	
	
	
	public List<CategoryVO> getAll() {
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		CategoryVO category = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
        //GET_ALL = "SELECT cat_name FROM category order by cat_id";
			while (rs.next()) {
				
				category = new CategoryVO();
				category.setCat_name(rs.getString("cat_name"));

				list.add(category); 
			}

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}	
}