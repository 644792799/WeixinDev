package net.sensof.weixin.po;

import java.io.Serializable;

public class AccessToken implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	private int expriesIn;
	private long createTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpriesIn() {
		return expriesIn;
	}
	public void setExpriesIn(int expriesIn) {
		this.expriesIn = expriesIn;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
