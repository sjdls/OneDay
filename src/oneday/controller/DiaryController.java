package oneday.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import oneday.pojo.Diary;
import oneday.pojo.Page;
import oneday.pojo.PageAndItems;
import oneday.pojo.Pet;
import oneday.pojo.SearchItems;
import oneday.pojo.Setting;
import oneday.pojo.User;
import oneday.service.OnedayService;

@Controller
public class DiaryController {
	@Resource
	private OnedayService onedayServiceImpl;
	//实际地址
	@Value("${ducument.base}")
	private String ducumentBase;
	//映射的服务器地址
	@Value("${mapping.path}")
	private String mappingPath;

	@RequestMapping(value = "diary/diaryByDate")
	@ResponseBody
	public List<Diary> getDiariesByDate(@RequestBody Diary diary, HttpSession session) {
		return onedayServiceImpl.getDiaryByDate(diary.getDate(), diary.getPhone());
	}

	@RequestMapping(value = "diary/dates")
	@ResponseBody
	public List<Date> getDates(@RequestBody Diary diary,HttpSession session) {
		return onedayServiceImpl.getDiarySortDates(diary.getPhone());
	}

	@RequestMapping(value = "diary/newDiary")
	@ResponseBody
	public boolean newDiary(
			@RequestParam(value = "phone") Long phone,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "weather") String weather,
			@RequestParam(value = "mood") String mood,
			@RequestParam(value = "event") String event,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "draft") boolean draft) throws IOException {
		Diary diary=new Diary();
		diary.setPhone(phone);
		diary.setTitle(title);
		diary.setWeather(weather);
		diary.setMood(mood);
		diary.setEvent(event);
		diary.setDate(new java.sql.Date(System.currentTimeMillis()));
		diary.setDraft(draft);
		if(!file.isEmpty()) {
			String filePath = ducumentBase;// 保存图片的路径
			// String filePath = "/image";//保存图片的路径
			// 获取原始图片的拓展名
			String originalFilename = file.getOriginalFilename();
			System.out.println("originalFilename:  " + originalFilename);
			// 新的文件名字
			String newFileName = UUID.randomUUID() + originalFilename;
			// 封装上传文件位置的全路径
			filePath += "/" + phone;
			System.out.println("filePath:  " + filePath);
			File targetFile = new File(filePath, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 把本地文件上传到封装上传文件位置的全路径
			System.out.println("newFileName:  " + newFileName);

			System.out.println("targetFile:  " + targetFile.getName());
			System.out.println("phone:  " + phone);
			//System.out.println("afterPhone");
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String allPath=mappingPath + "/" + phone+ "/" + newFileName;
			System.out.println("存储路径为"+allPath);
			diary.setPicture(allPath);
		}
		else {
			System.out.println("empty-file");
		}
		if(content!=null) {
			diary.setContent(content);
		}else {
			System.out.println("empty-content");
		}
		boolean result=onedayServiceImpl.insDiary(diary);
		Setting setting = new Setting();
		setting.setPhone(diary.getPhone());
		// setting.setUpload(upload);
		Timestamp t = new Timestamp(new java.util.Date().getTime());
		System.out.println(t);
		setting.setUpload(t);
		result = onedayServiceImpl.updSetting(setting);
		return result;
	}
	
	@RequestMapping(value = "diary/updDiary")
	@ResponseBody
	public boolean updDiary(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "phone") Long phone,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "weather", required = false) String weather,
			@RequestParam(value = "mood", required = false) String mood,
			@RequestParam(value = "event", required = false) String event,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "draft", required = false) Boolean draft) throws IOException {
		Diary diary=new Diary();
		diary.setId(id);
		diary.setPhone(phone);
		if(title!=null) {
			diary.setTitle(title);
		}else {
			System.out.println("empty_title");
		}
		if(weather!=null) {
			diary.setWeather(weather);
		}else {
			System.out.println("empty_weather");
		}
		if(mood!=null) {
			diary.setMood(mood);
		}else {
			System.out.println("empty_mood");
		}
		if(event!=null) {
			diary.setEvent(event);
		}else {
			System.out.println("empty_event");
		}
		if(draft!=null) {
			diary.setDraft(draft);
		}else {
			System.out.println("empty_draft");
		}
		diary.setDate(new java.sql.Date(System.currentTimeMillis()));
		if(!file.isEmpty()) {
			String filePath = ducumentBase;// 保存图片的路径
			// String filePath = "/image";//保存图片的路径
			// 获取原始图片的拓展名
			String originalFilename = file.getOriginalFilename();
			System.out.println("originalFilename:  " + originalFilename);
			// 新的文件名字
			String newFileName = UUID.randomUUID() + originalFilename;
			// 封装上传文件位置的全路径
			filePath += "/" + phone;
			System.out.println("filePath:  " + filePath);
			File targetFile = new File(filePath, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 把本地文件上传到封装上传文件位置的全路径
			System.out.println("newFileName:  " + newFileName);

			System.out.println("targetFile:  " + targetFile.getName());
			System.out.println("phone:  " + phone);
			//System.out.println("afterPhone");
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String allPath=mappingPath + "/" + phone+ "/" + newFileName;
			System.out.println("存储路径为"+allPath);
			diary.setPicture(allPath);
		}
		else {
			System.out.println("empty-file");
		}
		if(content!=null) {
			diary.setContent(content);
		}else {
			System.out.println("empty-content");
		}
		boolean result=onedayServiceImpl.updDiary(diary);
		Setting setting = new Setting();
		setting.setPhone(diary.getPhone());
		// setting.setUpload(upload);
		Timestamp t = new Timestamp(new java.util.Date().getTime());
		System.out.println(t);
		setting.setUpload(t);
		result = onedayServiceImpl.updSetting(setting);
		return result;
	}
	
	@RequestMapping(value = "diary/delDiary")
	@ResponseBody
	public boolean delDiary(@RequestBody Diary diary) {
		boolean result=onedayServiceImpl.delDiary(diary.getId());
		Setting setting = new Setting();
		setting.setPhone(diary.getPhone());
		// setting.setUpload(upload);
		Timestamp t = new Timestamp(new java.util.Date().getTime());
		System.out.println(t);
		setting.setUpload(t);
		result = onedayServiceImpl.updSetting(setting);
		return result;
	}
	
	@RequestMapping(value = "diary/diaryByItems")
	@ResponseBody
	public List<Diary> getDiariesByItems(@RequestBody SearchItems searchItems, HttpSession session) {
		System.out.println(searchItems);
		return onedayServiceImpl.selDiaryByItems(searchItems);
	}
	
	@RequestMapping(value = "diary/weather")
	@ResponseBody
	public HashMap<String, Integer> diaryStatisticsWeather(@RequestBody SearchItems searchItems) {
		HashMap<String , Integer> result = new HashMap<String , Integer>();
		System.out.println(searchItems);
		List<Diary> diaries = onedayServiceImpl.selDiaryByItems(searchItems);
		for (Diary diary : diaries) {
			String weather=diary.getWeather();
			System.out.println(weather);
			if (result.containsKey(weather)) {
				int tem=result.get(weather)+1;
				result.put(weather, tem);
			}else {
				result.put(weather, 1);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "diary/mood")
	@ResponseBody
	public HashMap<String, Integer> diaryStatisticsMood(@RequestBody SearchItems searchItems) {
		HashMap<String , Integer> result = new HashMap<String , Integer>();
		System.out.println(searchItems);
		List<Diary> diaries = onedayServiceImpl.selDiaryByItems(searchItems);
		for (Diary diary : diaries) {
			String mood=diary.getMood();
			System.out.println(mood);
			if (result.containsKey(mood)) {
				int tem=result.get(mood)+1;
				result.put(mood, tem);
			}else {
				result.put(mood, 1);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "diary/event")
	@ResponseBody
	public HashMap<String, Integer> diaryStatisticsEvent(@RequestBody SearchItems searchItems) {
		HashMap<String , Integer> result = new HashMap<String , Integer>();
		System.out.println(searchItems);
		List<Diary> diaries = onedayServiceImpl.selDiaryByItems(searchItems);
		for (Diary diary : diaries) {
			String event=diary.getEvent();
			System.out.println(event);
			if (result.containsKey(event)) {
				int tem=result.get(event)+1;
				result.put(event, tem);
			}else {
				result.put(event, 1);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "diary/diaryByPage")
	@ResponseBody
	public List<Diary> getDiaryByPage(@RequestBody Page page){
		return onedayServiceImpl.getDiaryByPage(page);
	}
	
	@RequestMapping(value = "diary/diaryByItemsAndPage")
	@ResponseBody
	public List<Diary> getDiariesByItemsAndPage(@RequestBody PageAndItems pageAndItems) {
		return onedayServiceImpl.getDiaryByItemsAndPage(pageAndItems);
	}
	
	@RequestMapping(value = "diary/diaryLastet")
	@ResponseBody
	public Date getDiaryLatest(@RequestBody Page page) {
		page.setPageNo(1);
		page.setPageSize(1);
		page.setDesc(true);
		return onedayServiceImpl.getDiaryByPage(page).get(0).getDate();
	}
}
