package oneday.pojo;

import java.sql.Date;

public class Pet {
	private int id;
	private Long phone;
	private String petname;
	private Date birthday;
	private int weight;
	private int intimacy;

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

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getIntimacy() {
		return intimacy;
	}

	public void setIntimacy(int intimacy) {
		this.intimacy = intimacy;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", phone=" + phone + ", petname=" + petname + ", birthday=" + birthday + ", weight="
				+ weight + ", intimacy=" + intimacy + "]";
	}

}
