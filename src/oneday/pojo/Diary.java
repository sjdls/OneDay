package oneday.pojo;

import java.sql.Date;

public class Diary {
	private int id;
	private Long phone;
	private String title;
	private Date date;
	private String weather;
	private String mood;
	private String event;
	private String picture;
	private String content;
	private Boolean draft;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	@Override
	public String toString() {
		return "Diary [id=" + id + ", phone=" + phone + ", title=" + title + ", date=" + date + ", weather=" + weather
				+ ", mood=" + mood + ", event=" + event + ", picture=" + picture + ", content=" + content + ", draft="
				+ draft + "]";
	}

}
