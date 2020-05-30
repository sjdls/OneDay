package oneday.pojo;

public class SearchItems {
	private String title;
	private String weather;
	private String mood;
	private String event;
	private Boolean draft;
	private Long phone;

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
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

	@Override
	public String toString() {
		return "SearchItems [title=" + title + ", weather=" + weather + ", mood=" + mood + ", event=" + event
				+ ", draft=" + draft + ", phone=" + phone + "]";
	}

}
