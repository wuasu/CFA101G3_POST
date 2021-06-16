package forum.model;
import java.sql.Date;

//文章POST
public class PostVO implements java.io.Serializable{

	private Integer post_id; //文章ID
	private String post_title; //文章標題
	private String post_content; //文章內容
	private Date post_time; //文章發表時間
	private Integer post_cat_id; //文章分類ID
	private Integer post_mem_id; //文章作者會員ID
	private Short post_status; //文章狀態
	
	
	
	
	
	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public Date getPost_time() {
		return post_time;
	}
	public void setPost_time(Date post_time) {
		this.post_time = post_time;
	}
	public Integer getPost_cat_id() {
		return post_cat_id;
	}
	public void setPost_cat_id(Integer post_cat_id) {
		this.post_cat_id = post_cat_id;
	}
	public Integer getPost_mem_id() {
		return post_mem_id;
	}
	public void setPost_mem_id(Integer post_mem_id) {
		this.post_mem_id = post_mem_id;
	}
	public Short getPost_status() {
		return post_status;
	}
	public void setPost_status(Short post_status) {
		this.post_status = post_status;
	}
	


	
	
	

	

}
