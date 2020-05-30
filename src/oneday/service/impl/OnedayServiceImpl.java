package oneday.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oneday.mapper.DiaryMapper;
import oneday.mapper.PetMapper;
import oneday.mapper.UserMapper;
import oneday.pojo.Diary;
import oneday.pojo.Page;
import oneday.pojo.Pet;
import oneday.pojo.SearchItems;
import oneday.pojo.Setting;
import oneday.pojo.User;
import oneday.service.OnedayService;

@Service
public class OnedayServiceImpl implements OnedayService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private DiaryMapper diaryMapper;
	@Resource
	private PetMapper petMapper;

	@Override
	public User getUser(Long phone, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		System.out.println(user);
		User resultUser = userMapper.selByUser(user);
		System.out.println(resultUser);
		return resultUser;
	}

	@Override
	public Setting getSetting(Long phone) {
		// TODO Auto-generated method stub
		return userMapper.selByPhoneSetting(phone);
	}

	@Override
	public Pet getPet(Long phone) {
		// TODO Auto-generated method stub
		return petMapper.selByPhonePet(phone);
	}

	@Override
	public List<Diary> getDiaryByDate(Date date, Long phone) {
		// TODO Auto-generated method stub
		return diaryMapper.selByDateDiaries(date, phone);
	}

	@Override
	public List<Date> getDiarySortDates(Long phone) {
		// TODO Auto-generated method stub
		List<Date> dates = new ArrayList<Date>();
		List<Diary> diaries = diaryMapper.selSortByDateDiaries(phone);
		for (Diary diary : diaries) {
			dates.add(diary.getDate());
		}
		return dates;
	}

	@Override
	public boolean updPetName(String petName, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = petMapper.updPetName(petName, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updPetWeight(int weight, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = petMapper.updPetWeight(weight, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updPetIntimacy(int intimacy, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = petMapper.updPetIntimacy(intimacy, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updProfilePhoto(String photo, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.updProfilePhoto(photo, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updPassword(String password, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.updPassword(password, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updUsername(String username, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.updUsername(username, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updEmail(String email, Long phone) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.updEmail(email, phone);
		boolean result = false;
		System.out.println(tem);
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updDiary(Diary diary) {
		// TODO Auto-generated method stub
		System.out.println("begin-update");
		Integer tem = diaryMapper.updDiary(diary);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		System.out.println("over-update");
		return result;
	}

	@Override
	public boolean insUser(User user) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.insUser(user);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean insSetting(Setting setting) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.insSetting(setting);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean insPet(Pet pet) {
		// TODO Auto-generated method stub
		Integer tem = petMapper.insPet(pet);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean insDiary(Diary diary) {
		// TODO Auto-generated method stub
		Integer tem = diaryMapper.insDiary(diary);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delDiary(int id) {
		// TODO Auto-generated method stub
		Integer tem = diaryMapper.delDiary(id);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<Diary> selDiaryByItems(SearchItems searchItems) {
		// TODO Auto-generated method stub
		return diaryMapper.selDiary(searchItems);
	}

	@Override
	public boolean updSetting(Setting setting) {
		// TODO Auto-generated method stub
		Integer tem = userMapper.updSetting(setting);
		System.out.println(tem);
		boolean result = false;
		if (tem != 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<Diary> getDiaryByPage(Page page) {
		// TODO Auto-generated method stub
		int pageNo = page.getPageNo() - 1;
		int pageSize = page.getPageSize();
		page.setPageNo(pageNo * pageSize);
		if (page.getDesc()) {
			return diaryMapper.selDiaryByPageDesc(page);
		} else {
			return diaryMapper.selDiaryByPageAsc(page);
		}
	}

}
