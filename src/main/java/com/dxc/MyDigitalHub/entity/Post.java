package com.dxc.MyDigitalHub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//mapping entity to table called as 'Post'
@Entity
@Table(name="Post")
public class Post extends UserObj<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
    private int postid;
	
	@Column(name="poster_id")
    private int posterId;

	@Column(name="view_count")
    private int viewcount;
    
	@Column(name="post_caption")
    private String postcaption;
	
	@Column(name="filelocation")
    private String filelocation;
	
	@Column(name="post_type")
    private String postType;

	public Post() {

	}

	public Post(int posterId, int viewcount, String postcaption, String filelocation, String postType) {
		super();
		this.posterId = posterId;
		this.viewcount = viewcount;
		this.postcaption = postcaption;
		this.filelocation = filelocation;
		this.postType = postType;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}
	
	public int getPosterId() {
		return posterId;
	}

	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public String getPostcaption() {
		return postcaption;
	}

	public void setPostcaption(String postcaption) {
		this.postcaption = postcaption;
	}
	
	public String getFilelocation() {
		return filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}
	
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	@Override
	public String toString() {
		return "Post [postid=" + postid + ", posterId=" + posterId + ", viewcount=" + viewcount + ", postcaption="
				+ postcaption + ", filelocation=" + filelocation + ", postType=" + postType + "]";
	}
	
}
