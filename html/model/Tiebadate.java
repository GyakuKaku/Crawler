package html.model;

import java.util.Date;

public class Tiebadate {
	private int sid = -1;
	private String title = "";
	private String creater = "";
	private String text = "";
	private String theme = "";
	private double tendency = 0;
	private Date dateshow = new Date();
	private String time = "";
	public Tiebadate(int sid, String title, String creater, String text, String theme, double tendency, Date dateshow) {
		this.sid = sid;
		this.title = title;
		this.creater = creater;
		this.text = text;
		this.theme = theme;
		this.tendency = tendency;
		this.dateshow = dateshow;
	}
	public Tiebadate() {
		
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public double getTendency() {
		return tendency;
	}
	public void setTendency(double tendency) {
		this.tendency = tendency;
	}
	public Date getDateshow() {
		return dateshow;
	}
	public void setDateshow(Date dateshow) {
		this.dateshow = dateshow;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String tostring(){
		return "贴吧:"+this.title+" 标题:"+this.theme+" 用户:"+this.creater+" 内容:"+this.text+" 情感值:"+this.tendency+ " 时间:"+this.dateshow.toString()+ " 发帖时间:"+this.time;
	}
}
