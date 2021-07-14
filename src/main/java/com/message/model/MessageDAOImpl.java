package com.message.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.category.model.CategoryVO;
import com.post.model.PostVO;
import com.post_tag_ref.model.PostTagRefDAOImpl;
import com.post_tag_ref.model.PostTagRefVO;
import com.tag.model.TagVO;

public class MessageDAOImpl implements MessageDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO MESSAGE (MES_POST_ID, MES_MEM_ID, MES_CONTENT, MES_TIME) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_MES_STATUS = "UPDATE MESSAGE SET MES_STATUS=0 where MES_ID= ?"; // 改成0=隱藏
	private static final String getBy_mes_post_id = "select s.MES_ID,s.MES_POST_ID,s.MES_MEM_ID,m.MEM_NAME,s.MES_TIME,s.MES_CONTENT,m.MEM_HEADSHOT,\n"
			+ "s.MES_STATUS from MEMBER m join MESSAGE s on m.MEM_ID = s.MES_MEM_ID where MES_POST_ID=? order by MES_ID desc";
	private static final String FIND_ONE_BY_MES_ID = "SELECT * FROM MESSAGE where MES_ID = ?";
	
	@Override
	public void insert(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, messageVO.getMes_post_id());
			pstmt.setInt(2, messageVO.getMes_mem_id());
			pstmt.setString(3,messageVO.getMes_content());
			pstmt.setDate(4,messageVO.getMes_time());
			pstmt.executeUpdate();
			System.out.println("新增留言成功");
			
		} catch (Exception se) {
			se.printStackTrace();
			if(con!=null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

	
	
	
	@Override
	public void updateMesStatus(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

//UPDATE_MES_STATUS = "UPDATE MESSAGE SET MES_STATUS=0 where MES_ID= ?"; // 改成0=隱藏
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MES_STATUS);
			pstmt.setInt(1, messageVO.getMes_id());
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
			
		
	

	@Override
	public MessageVO findOneByMesID(Integer mes_id) {
		
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_BY_MES_ID);
		// FIND_ONE_BY_MES_ID = "SELECT * FROM MESSAGE where MES_ID = ?";
			pstmt.setInt(1, mes_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageVO.setMes_id(rs.getInt("mes_id"));
				messageVO.setMes_post_id(rs.getInt("mes_post_id"));
				messageVO.setMes_mem_id(rs.getInt("mes_mem_id"));
				messageVO.setMes_content(rs.getString("mes_content"));
				messageVO.setMes_time(rs.getDate("mes_time"));
				messageVO.setMes_status(rs.getInt("mes_status"));
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
		return messageVO;
	}
	
	
	
	

	@Override
	public List<MessageVO> getBy_mes_post_id(Integer mes_post_id) {
		List list = new ArrayList();
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getBy_mes_post_id);
			pstmt.setInt(1, mes_post_id);
			rs = pstmt.executeQuery();
//getBy_mes_post_id = 
//"select s.MES_ID,s.MES_POST_ID,s.MES_MEM_ID,m.MEM_NAME,s.MES_TIME,s.MES_CONTENT,m.MEM_HEADSHOT,\n"
//+ "s.MES_STATUS from MEMBER m join MESSAGE s on m.MEM_ID = s.MES_MEM_ID where MES_POST_ID=? order by MES_ID desc";
			while (rs.next()) {
				Map map = new HashMap();
				map.put("MES_ID", rs.getInt("mes_id"));
				map.put("MES_POST_ID", rs.getInt("mes_post_id"));
				map.put("MES_MEM_ID", rs.getInt("post_content"));
				map.put("MEM_NAME", rs.getString("post_content"));
				map.put("MES_TIME", rs.getDate("post_time"));
				map.put("MES_CONTENT", rs.getString("cat_name"));
				map.put("MEM_HEADSHOT", rs.getBytes("mem_name"));
				map.put("MES_STATUS", rs.getInt("post_status"));
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
