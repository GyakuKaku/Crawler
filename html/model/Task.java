package html.model;

public class Task {

	private int sid; 
	private String key_;
	private int live;
	
	public Task(int sid, String key_, int live) {
		if(sid != -1)
			this.sid = sid;
		this.key_ = key_;
		this.live = live;
	}
	
	public Task() {
		
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getKey_() {
		return key_;
	}

	public void setKey_(String key_) {
		this.key_ = key_;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}
}
