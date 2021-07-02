package com.post.model;

import java.util.List;
import java.util.Map;

import com.category.model.CategoryVO;

public class PostService {

	private PostDAO dao = new PostDAOImpl();

	
	public void addPost (PostVO post) {
		dao.insert(post);
	}
	
	public void updatePost(PostVO post) {
		dao.update(post);
	}
	
	public void deletePost(Integer post_id) {
		dao.delete(post_id);
	}
	
	public PostVO findByPostId(Integer post_id) {
		return dao.findByPostId(post_id);
	}
	
	public List<PostVO> getAll(){
		return dao.getAll();
	}
	
	public void updatePostStatus(PostVO post) {
		dao.updatePostStatus(post);
	}
	
	public List getPost(){
		return dao.getPost();
	
	}
	
}
