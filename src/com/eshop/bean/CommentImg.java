package com.eshop.bean;

/**
 * 商品评论添加图片信息
 * @author uname
 *
 */
public class CommentImg {
	int id;
	String commentId;
	String curl;
	

	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public CommentImg() {
	}
	public CommentImg(int id, String commentId, String curl) {
		this.id = id;
		this.commentId = commentId;
		this.curl = curl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
	
}
