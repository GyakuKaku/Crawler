package html.model;

public class Records {

	private int sid;
	private String key_;
	private String text;
	private String crawledate;
	public Records(int sid, String key_, String text, String crawledate) {
		if(sid != -1)
			this.sid = sid;
		this.key_ = key_;
		this.text = text;
		this.crawledate = crawledate;
	}
	public Records() {
		
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCrawledate() {
		return crawledate;
	}
	public void setCrawledate(String crawledate) {
		this.crawledate = crawledate;
	}
}
