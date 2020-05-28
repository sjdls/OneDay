package oneday.service;

import java.sql.Date;
import java.util.List;

import oneday.pojo.Diary;
import oneday.pojo.Pet;
import oneday.pojo.SearchItems;
import oneday.pojo.Setting;
import oneday.pojo.User;

public interface OnedayService {
	User getUser(Long phone,String password);
	Setting getSetting(Long phone);
	Pet getPet(Long phone);
	List<Diary> getDiaryByDate(Date date,Long phone);
	List<Date> getDiarySortDates(Long phone);
	boolean updPetName(String petName,Long phone);
	boolean updPetWeight(int weight,Long phone);
	boolean updPetIntimacy(int intimacy,Long phone);
	boolean updProfilePhoto(String photo,Long phone);
	boolean updPassword(String password,Long phone);
	boolean updEmail(String email,Long phone);
	boolean updUsername(String username,Long phone);
	boolean updDiary(Diary diary);
	boolean insUser(User user);
	boolean insSetting(Setting setting);
	boolean insPet(Pet pet);
	boolean insDiary(Diary diary);
	boolean delDiary(int id);
	List<Diary> selDiaryByItems(SearchItems searchItems);
	boolean updSetting(Setting setting);
}
