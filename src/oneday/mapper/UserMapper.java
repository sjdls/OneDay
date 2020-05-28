package oneday.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import oneday.pojo.Pet;
import oneday.pojo.Setting;
import oneday.pojo.User;

public interface UserMapper {
	@Select("select * from user where phone=#{phone} and password=#{password}")
	User selByUser(User user);

	@Select("select * from setting where phone=#{0}")
	Setting selByPhoneSetting(Long phone);
	
	@Update("update user set photo=#{0} where phone=#{1}")
	int updProfilePhoto(String photo,Long phone);

	@Update("update user set password=#{0} where phone=#{1}")
	int updPassword(String password,Long phone);

	@Update("update user set email=#{0} where phone=#{1}")
	int updEmail(String email,Long phone);
	
	@Update("update user set username=#{0} where phone=#{1}")
	int updUsername(String username,Long phone);
	
	@Insert("insert into user values(default,#{phone},#{password},#{username},#{photo},#{email})")
	int insUser(User user);
	
	@Insert("insert into setting values(default,#{phone},#{locked},#{lockkey},#{upload})")
	int insSetting(Setting setting);
	
	int updSetting(Setting setting);
}
