package forum.model;
//import java.sql.Date;

//文章分類CATEGORY
public class CategoryVO implements java.io.Serializable{

	private Integer cat_id; //文章分類ID
	private String cat_name; //文章分類名稱
	
	
	
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	

	}

	


