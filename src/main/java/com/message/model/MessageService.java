package com.message.model;
import java.sql.Date;
import java.util.List;


public class MessageService {
	private MessageDAO dao = new MessageDAOImpl();
	

	//用mes_post_id找出該則文章下的留言
	public List<MessageVO> getBy_mes_post_id(Integer mes_post_id) {
		return dao.getBy_mes_post_id(mes_post_id);
		
	}

}
