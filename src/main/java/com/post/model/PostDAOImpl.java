package com.post.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;

public class PostDAOImpl implements PostDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT = "INSERT INTO POST (POST_TITLE, POST_CONTENT, POST_TIME, POST_CAT_ID, POST_MEM_ID) VALUES(?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE POST set POST_TITLE=?, POST_CONTENT=?, POST_TIME=?, POST_CAT_ID=?, POST_MEM_ID=?, POST_STATUS=?  where post_id = ?";
	private static final String DELETE = "DELETE FROM POST where POST_ID = ?";
	private static final String GET_ONE_POSTTITLE = "SELECT POST_TITLE FROM POST where POST_ID = ?";
	private static final String GET_ALL = "SELECT POST_TITLE, POST_CONTENT, POST_TIME, POST_CAT_ID, POST_MEM_ID, POST_STATUS, FROM POST where POST_ID = ?";
	private static final String UPDATE_POST_STATUS = "UPDATE POST set POST_STATUS=0 where POST_ID= ?"; // 0隱藏

	private static final String GET_POST = "select p.POST_ID,p.POST_TITLE,p.POST_CONTENT,p.POST_TIME, c.CAT_NAME,m.MEM_NAME,m.MEM_HEADSHOT,p.POST_STATUS from MEMBER m join POST p on m.MEM_ID = p.POST_MEM_ID join CATEGORY c on p.POST_CAT_ID = c.CAT_ID";

	public void insert(PostVO post) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, post.getPost_title());
			pstmt.setString(2, post.getPost_content());
			pstmt.setDate(3, post.getPost_time());
			pstmt.setInt(4, post.getPost_cat_id());
			pstmt.setInt(5, post.getPost_mem_id());
//			pstmt.setInt(6, post.getPost_status());

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
	public void update(PostVO post) {

		Connection con = null;
		PreparedStatement pstmt = null;

		// "UPDATE POST set POST_TITLE=?, POST_CONTENT=?,
		// POST_TIME=?, POST_CAT_ID=?, POST_MEM_ID=?, POST_STATUS=? where post_id = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, post.getPost_title());
			pstmt.setString(2, post.getPost_content());
			pstmt.setDate(3, post.getPost_time());
			pstmt.setInt(4, post.getPost_cat_id());
			pstmt.setInt(5, post.getPost_mem_id());
			pstmt.setInt(6, post.getPost_status());
			pstmt.setInt(7, post.getPost_id());

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

	public void delete(Integer post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// "DELETE FROM POST where POST_ID = ?"

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, post_id);
			pstmt.executeUpdate();

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

	public PostVO findByPostId(Integer post_id) {
		PostVO post = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		 GET_ONE = "SELECT POST_TITLE FROM POST where POST_ID = ?"
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_POSTTITLE);

			pstmt.setInt(1, post_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				post = new PostVO();
				post.setPost_title(rs.getString("setPost_title"));

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
		return post;
	}

	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO post = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
//	GET_ALL = "SELECT POST_TITLE, POST_CONTENT, POST_TIME,"
//	+ " POST_CAT_ID, POST_MEM_ID, POST_STATUS, FROM POST where POST_ID = ?";		
				post = new PostVO();
				post.setPost_title(rs.getString("post_title"));
				post.setPost_content(rs.getString("post_content"));
				post.setPost_time(rs.getDate("post_time"));
				post.setPost_cat_id(rs.getInt("post_cat_id"));
				post.setPost_mem_id(rs.getInt("post_mem_id"));
				post.setPost_status(rs.getInt("post_status"));
				list.add(post);
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
		return list;
	}

	public void updatePostStatus(PostVO post) {

		Connection con = null;
		PreparedStatement pstmt = null;

//UPDATE_POST_STATUS = "UPDATE POST set POST_STATUS=0 where POST_ID"; //0隱藏
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_POST_STATUS);
			pstmt.setInt(1, post.getPost_id());

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

	public List getPost() {

		
		List list = new ArrayList();
		PostVO postAll = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_POST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map map = new HashMap();
				map.put("POST_ID", rs.getInt("post_id"));
				map.put("POST_TITLE", rs.getString("post_title"));
				map.put("POST_CONTENT", rs.getString("post_content"));
				map.put("POST_TIME", rs.getDate("post_time"));
				map.put("CAT_NAME", rs.getString("cat_name"));
				map.put("MEM_NAME", rs.getString("mem_name"));
				map.put("MEM_HEADSHOT", rs.getBytes("mem_headshot"));
				map.put("POST_STATUS", rs.getShort("post_status"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
