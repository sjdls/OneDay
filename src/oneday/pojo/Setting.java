package oneday.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Setting {
	private int id;
	private Long phone;
	private Boolean locked;
	private String lockkey;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date upload;

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

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getLockkey() {
		return lockkey;
	}

	public void setLockkey(String lockkey) {
		this.lockkey = lockkey;
	}

	public Date getUpload() {
		return upload;
	}

	public void setUpload(Date upload) {
		this.upload = upload;
	}

	@Override
	public String toString() {
		return "Setting [id=" + id + ", phone=" + phone + ", locked=" + locked + ", lockkey=" + lockkey + ", upload="
				+ upload + "]";
	}

}
