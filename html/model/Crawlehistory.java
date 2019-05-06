package html.model;

import java.util.Date;

public class Crawlehistory {

	private int sid;
	private String key_;
	private Date crawledate;
	public Crawlehistory(int sid ,String key_, Date crawledate) {
		this.key_ = key_;
		this.crawledate = crawledate;
	}
	public Crawlehistory() {
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getKey() {
		return key_;
	}
	public void setKey(String key_) {
		this.key_ = key_;
	}
	public Date getCrawledate() {
		return crawledate;
	}
	public void setCrawledate(Date crawledate) {
		this.crawledate = crawledate;
	}
}
