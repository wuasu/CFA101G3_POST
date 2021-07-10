package com.post.model;

import java.util.*;

import com.tag.model.TagVO;


interface PostDAO {
	void insert(PostVO post,List<TagVO> addTag);
	void update(PostVO post);
	void delete(Integer post_id);
	PostVO findByPostId(Integer post_id); 
	//獲取文章標題.內容.時間.分類.作者.狀態
	List<PostVO> getAll();
	//更新文章狀態
	void updatePostStatus(PostVO post);
	
	
	
	//獲取首頁文章區塊資訊
	List getPost();

}
