package com.tag.model;


//??‡ç? æ?™ç±¤TAG
public class TagVO implements java.io.Serializable{

	private Integer tag_id; //æ¨™ç±¤ID
	private String tag_name; //æ¨™ç±¤??ç¨±
	
	public TagVO() {}
	
	public TagVO(Integer tag_id, String tag_name) {
		this.tag_id = tag_id;
		this.tag_name = tag_name;
	}

	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public String toString() {
		return "TagVO [tag_id=" + tag_id + ", tag_name=" + tag_name + "]";
	}

}
