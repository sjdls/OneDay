package oneday.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import oneday.mapper.UserMapper;
import oneday.pojo.Pet;
import oneday.pojo.Setting;
import oneday.pojo.User;
import oneday.service.OnedayService;

@Controller
public class UserController {
	@Resource
	private OnedayService onedayServiceImpl;
	@Value("${ducument.base}")
	private String ducumentBase;
	@Value("${mapping.path}")
	private String mappingPath;

	@RequestMapping(value = "user/login")
	@ResponseBody
	public User getUser(@RequestBody User user, HttpSession session) {
		User userResult = onedayServiceImpl.getUser(user.getPhone(), user.getPassword());
		if (userResult != null) {
			session.setAttribute("user", userResult);
		}
		return userResult;
	}

	@RequestMapping(value = "user/login/{phone}/{password}")
	@ResponseBody
	public User getUserTem(@PathVariable Long phone, @PathVariable String password, HttpSession session) {
		User userResult = onedayServiceImpl.getUser(phone, password);
		if (userResult != null) {
			session.setAttribute("user", userResult);
		}
		return userResult;
	}

	@RequestMapping(value = "user/setting")
	@ResponseBody
	public Setting getSetting(@RequestBody User user) {
		return onedayServiceImpl.getSetting(user.getPhone());
	}

	@RequestMapping(value = "user/updSetting")
	@ResponseBody
	public boolean setSetting(@RequestBody Setting setting) {
		System.out.println(setting);
		System.out.println("begin-set-setting");
		return onedayServiceImpl.updSetting(setting);
	}

	@RequestMapping(value = "user/password")
	@ResponseBody
	public boolean setPassword(@RequestBody User user, HttpSession session) {
		return onedayServiceImpl.updPassword(user.getPassword(), user.getPhone());
	}

	@RequestMapping(value = "user/email")
	@ResponseBody
	public boolean setEmail(@RequestBody User user, HttpSession session) {
		return onedayServiceImpl.updEmail(user.getEmail(), user.getPhone());
	}
	
	@RequestMapping(value = "user/username")
	@ResponseBody
	public boolean setUseName(@RequestBody User user, HttpSession session) {
		return onedayServiceImpl.updUsername(user.getUsername(), user.getPhone());
	}

	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	@ResponseBody
	public User addUser(@RequestBody User user, HttpSession session, HttpServletRequest request) {
		User userResult = onedayServiceImpl.getUser(user.getPhone(), user.getPassword());
		boolean result;
		if (userResult != null) {
			System.out.println("用户已存在");
			return userResult;
		} else {
			String name = "用户" + user.getPhone();
			System.out.println(name);
			user.setUsername(name);
			user.setEmail("email@qq.com");
			result = onedayServiceImpl.insUser(user);
			
			Setting setting = new Setting();
			setting.setPhone(user.getPhone());
			setting.setLocked(false);
			// setting.setUpload(upload);
			Timestamp t = new Timestamp(new Date().getTime());
			System.out.println(t);
			setting.setUpload(t);
			result = onedayServiceImpl.insSetting(setting);
			Pet pet = new Pet();
			pet.setPhone(user.getPhone());
			pet.setPetname(user.getPhone()+"的宠物");
			pet.setColor("orange");
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			System.out.println(currentDate);
			pet.setBirthday(currentDate);
			pet.setWeight(100);
			pet.setIntimacy(6);
			result = onedayServiceImpl.insPet(pet);
			return user;
		}
	}

	@RequestMapping(value = "user/profilePhoto", produces = "application/json; charset=utf-8")
	@ResponseBody
	public boolean imageUphold(@RequestParam("photo") MultipartFile file, Long phone) throws IOException {
		// Long phone=13225942005L;
		// Long phone=user.getPhone();
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
		boolean result=onedayServiceImpl.updProfilePhoto(allPath, phone);
		return result;
	}

	@RequestMapping(value = "imageAndJson/uphold", produces = "application/json; charset=utf-8")
	@ResponseBody
	public boolean pictureUphold(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "phone") Long phone,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "weather", required = false) String weather,
			@RequestParam(value = "mood", required = false) String mood,
			@RequestParam(value = "event", required = false) String event,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "draft", required = false) boolean draft) throws IOException {
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
		boolean result=onedayServiceImpl.updProfilePhoto(allPath, phone);
		return result;
	}

	@RequestMapping(value = "user/nullUser", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getUser() {
		return "用户未登录";
	}
}
