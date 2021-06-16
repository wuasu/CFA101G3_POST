package forum.model;
import java.sql.Date;

//留言MESSAGE
public class MessageVO implements java.io.Serializable{

	private Integer mes_id; //留言ID
	private Integer mes_post_id; //文章ID
	private Integer mes_mem_id; //會員ID
	private String mes_content; //留言內容
	private Date mes_time; //留言時間
	private Short mes_status; //留言狀態
	
	
	
	
	public Integer getMes_id() {
		return mes_id;
	}
	public void setMes_id(Integer mes_id) {
		this.mes_id = mes_id;
	}
	public Integer getMes_post_id() {
		return mes_post_id;
	}
	public void setMes_post_id(Integer mes_post_id) {
		this.mes_post_id = mes_post_id;
	}
	public Integer getMes_mem_id() {
		return mes_mem_id;
	}
	public void setMes_mem_id(Integer mes_mem_id) {
		this.mes_mem_id = mes_mem_id;
	}
	public String getMes_content() {
		return mes_content;
	}
	public void setMes_content(String mes_content) {
		this.mes_content = mes_content;
	}
	public Date getMes_time() {
		return mes_time;
	}
	public void setMes_time(Date mes_time) {
		this.mes_time = mes_time;
	}
	public Short getMes_status() {
		return mes_status;
	}
	public void setMes_status(Short mes_status) {
		this.mes_status = mes_status;
	}


}
