package com.post_tag_ref.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.post.model.PostVO;

public class PostTagRefDAOImpl implements PostTagRefDAO{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO POST_TAG_REF (PTR_POST_ID, PTR_TAG_ID) VALUES(?, ?)";
	private static final String FIND_ONE_BY_PTRID = "SELECT * FROM POST_TAG_REF WHERE PTR_ID = ?";
	private static final String FINDBY_PTR_POST_ID = "SELECT * FROM POST_TAG_REF WHERE PTR_POST_ID = ?";

	
	@Override
	public void insert(PostTagRefVO postTagRefVO, Connection con) {
		
		PreparedStatement pstmt = null;
		try {
			System.out.println(con);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, postTagRefVO.getPtr_post_id());
			pstmt.setInt(2, postTagRefVO.getPtr_tag_id());

			pstmt.executeUpdate();
			System.out.println("新增成功");
			

		} catch (Exception se) {
			se.printStackTrace();
			if(con!= null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
	
		}

	}
		


	@Override
	//由PK找
	public PostTagRefVO find_One_By_PTRId(Integer ptr_id) {
		PostTagRefVO postTagRefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    //	FIND_ONE_BY_PTRID = "SELECT * FROM POST_TAG_REF WHERE PTR_ID = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_BY_PTRID);

			pstmt.setInt(1, ptr_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postTagRefVO = new PostTagRefVO();
				postTagRefVO.setPtr_id(rs.getInt("ptr_id"));
				postTagRefVO.setPtr_post_id(rs.getInt("ptr_post_id"));
				postTagRefVO.setPtr_tag_id(rs.getInt("ptr_tag_id"));
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
		return postTagRefVO;
	}
	
	

	@Override
	//由FK找PK
	public List<PostTagRefVO> findBy_PTR_Post_Id(Integer ptr_post_id) {
		PostTagRefVO postTagRefVO = null;
		List<PostTagRefVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    //	FINDBY_PTR_POST_ID = "SELECT * FROM POST_TAG_REF WHERE PTR_POST_ID = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBY_PTR_POST_ID);

			pstmt.setInt(1, ptr_post_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postTagRefVO = new PostTagRefVO();
				postTagRefVO.setPtr_id(rs.getInt("ptr_id"));
				postTagRefVO.setPtr_post_id(rs.getInt("ptr_post_id"));
				postTagRefVO.setPtr_tag_id(rs.getInt("ptr_tag_id"));
				list.add(postTagRefVO);
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

}
