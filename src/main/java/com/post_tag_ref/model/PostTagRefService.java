package com.post_tag_ref.model;

import java.util.List;

public class PostTagRefService {
	
	private PostTagRefDAO dao = new PostTagRefDAOImpl();
	
//	public PostTagRefVO addPostTagRef (Integer ptr_post_id,Integer ptr_tag_id) {
//		PostTagRefVO postTagRefVO = new PostTagRefVO();
//		postTagRefVO.setPtr_post_id(ptr_post_id);
//		postTagRefVO.setPtr_tag_id(ptr_tag_id);
//		dao.insert(postTagRefVO);
//		return postTagRefVO;
//	}

	
	//由PK 文章標籤明細找
	public PostTagRefVO find_One_By_PTRId(Integer ptr_id) {
		return dao.find_One_By_PTRId(ptr_id);
	}
	
	
	//由FK 文章ID找PK
	public List<PostTagRefVO> findBy_PTR_Post_Id(Integer ptr_post_id) {
		return dao.findBy_PTR_Post_Id(ptr_post_id);
	}
	
	
}
